package bindgen.web.internal.jobs

import bindgen.web.domain.*
import cats.effect.*
import cats.effect.std.Env
import cats.effect.std.UUIDGen
import cats.syntax.all.*
import dumbo.*
import org.typelevel.otel4s.trace.Tracer
import skunk.*
import skunk.codec.all.*
import skunk.implicits.*

import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.UUID
import scala.concurrent.duration.FiniteDuration

enum State:
  case Added
  case Completed
  case Failed

trait Store:
  def complete(id: JobId, result: BindgenResult): IO[Unit]
  def removeLease(workerId: WorkerId, jid: JobId): IO[Unit]
  def getState(jid: JobId): IO[Option[State]]
  def workSteal(
      workerId: WorkerId,
      limit: Int,
      staleness: FiniteDuration
  ): IO[List[JobId]]
  def createLeases(workerId: WorkerId, limit: Int): fs2.Stream[IO, JobId]
  def getOrdering(): IO[Map[JobId.Type, Int]]
  def record(spec: BindingSpec): IO[JobId]
  def getBinding(id: JobId): IO[GeneratedBinding]
  def getFailure(id: JobId): IO[Option[FailureDTO]]
  def getNoncompleteSpec(id: JobId): IO[Option[BindingSpec]]
end Store

case class FailureDTO(
    message: String,
    diags: List[ResolvedClangDiagnostic]
)

case class Job(id: JobId, spec: BindingSpec)

class StoreImpl(db: Resource[IO, Session[IO]]) extends Store:
  import Codec.*

  override def record(spec: BindingSpec): IO[JobId] =
    IO.realTimeInstant.flatMap { instant =>
      UUIDGen.randomUUID[IO].map(JobId(_)).flatMap { id =>
        db.use(
          _.execute(
            sql"""
                |insert into bindings (id, state, state_date, header_code, package_name)
                | values (${C.jobId}, ${C.state}, $timestamptz, ${C.headerCode}, ${C.packageName});""".stripMargin.command
          )(
            id,
            State.Added,
            OffsetDateTime.ofInstant(instant, ZoneId.of("UTC")),
            spec.headerCode,
            spec.packageName
          )
        ).as(id)
      }
    }

  override def complete(
      id: JobId,
      code: BindgenResult
  ): IO[Unit] =
    instant.flatMap: inst =>
      code match
        case BindgenResult.Success(code) =>
          db.use: sess =>

            val setCompleted =
              sess.execute(
                sql"""update bindings set state = ${C.state}, state_date = $timestamptz where id = ${C.jobId}""".stripMargin.command
              )(State.Completed, inst, id)

            val setCode = sess.execute(
              sql"insert into generated_code (binding_id, scala_code, glue_code) values (${C.jobId}, ${C.scalaCode}, ${C.glueCode.opt});".command
            )(id, code.scalaCode, code.glueCode)

            (setCode *> setCompleted).void

        case BindgenResult.Error(msg) =>
          val setFailed = db
            .use(
              _.execute(
                sql"update bindings set state = ${C.state}, state_date = $timestamptz where id = ${C.jobId};".command
              )(State.Failed, inst, id)
            )
            .void

          val writeErrors = db
            .use(
              _.execute(
                sql"insert into errors (binding_id, message) values (${C.jobId}, $text)".command
              )(id, msg)
            )
            .void

          setFailed *> writeErrors
        case BindgenResult.Failure(diagnostics) =>
          val msg = "Clang compilation errors"
          val setFailed = db
            .use(
              _.execute(
                sql"update bindings set state = ${C.state}, state_date = $timestamptz where id = ${C.jobId};".command
              )(State.Failed, inst, id)
            )
            .void

          val writeErrors = db
            .use(
              _.execute(
                sql"insert into errors (binding_id, message, diagnostics) values (${C.jobId}, $text, ${C.diagnostics})".command
              )(id, msg, diagnostics)
            )
            .void

          setFailed *> writeErrors
      end match

  override def removeLease(workerId: WorkerId, jid: JobId): IO[Unit] =
    db.use(
      _.execute(
        sql"delete from leases where binding_id = ${C.jobId} and worker_id = ${C.workerId};".command
      )(jid, workerId)
    ).void

  override def getState(jobId: JobId): IO[Option[State]] =
    db.use(
      _.option(
        sql"select state from bindings where id = ${C.jobId}"
          .query(C.state)
      )(jobId)
    )

  val instant =
    IO.realTimeInstant
      .map(OffsetDateTime.ofInstant(_, ZoneId.of("UTC")))

  val timeStream = fs2.Stream.repeatEval(instant)

  override def workSteal(
      workerId: WorkerId,
      limit: Int,
      staleness: FiniteDuration
  ): IO[List[JobId]] =
    instant.flatMap: inst =>
      db.use(
        _.stream(
          sql"""
        | update leases set worker_id = ${C.workerId} where binding_id
        |  in (select binding_id from leases where ($timestamptz - checked_in_at > interval '$int4' seconds) limit $int4)
        |  returning binding_id
        """.stripMargin.query(C.jobId),
          (workerId, inst, staleness.toSeconds.toInt, limit),
          limit
        ).compile.toList
      )

  end workSteal

  override def createLeases(
      workerId: WorkerId,
      limit: Int
  ): fs2.Stream[IO, JobId] =
    fs2.Stream
      .eval(
        IO.realTimeInstant.map(OffsetDateTime.ofInstant(_, ZoneId.of("UTC")))
      )
      .flatMap { inst =>
        fs2.Stream.evalSeq(
          db.use(
            _.stream(
              sql"""
            |insert into leases
            |  select
            |    b.id, ${C.workerId}, ${timestamptz}
            |  from
            |    bindings b left join leases l on l.binding_id = b.id
            |  where
            |    l.worker_id is null and
            |    b.state in (${C.state})
            |  limit $int4
            |returning binding_id;
          """.stripMargin.query(C.jobId),
              (workerId, inst, State.Added, limit),
              limit.min(100)
            ).compile.toList
          )
        )
      }

  override def getOrdering() =
    db.use(
      _.stream(
        sql"""
         |select
         |  id, (row_number() over (order by state_date))::int4
         |from
         |  bindings
         |where
         |  state in ('added');
         """.stripMargin.query(
          C.jobId.product(int4)
        ),
        skunk.Void,
        1024
      ).compile.toVector
        .map(_.toMap)
    )
  override def getNoncompleteSpec(id: JobId): IO[Option[BindingSpec]] =
    db.use(
      _.option(
        sql"""select header_code, package_name, clang_flags from bindings where id = ${C.jobId} and state in (${C.state})"""
          .query(C.bindingSpec),
        (id, State.Added)
      )
    )

  override def getFailure(id: JobId): IO[Option[FailureDTO]] =
    db.use(
      _.option(
        sql"""select message, diagnostics from errors where binding_id = ${C.jobId}"""
          .query(C.failureDTO),
        (id)
      )
    )

  override def getBinding(id: JobId): IO[GeneratedBinding] =
    val getCode = db.use(
      _.option(
        sql"select scala_code, glue_code from generated_code where binding_id = ${C.jobId}"
          .query(C.generatedCode),
        id
      )
    )
    val getSpec = db.use(
      _.option(
        sql"select header_code, package_name, clang_flags, state_date from bindings where id = ${C.jobId}"
          .query(C.bindingSpec.product(timestamptz.opt)),
        id
      )
    )

    for
      specMaybe <- getSpec
      check     <- IO.fromOption(specMaybe)(BindingNotFound())
      (spec, completed) = check
      _         <- IO.raiseError(BindingNotReady()).whenA(completed.isEmpty)
      codeMaybe <- getCode
      code      <- IO.fromOption(codeMaybe)(BindingCodeNotFound())
    yield GeneratedBinding(spec, Some(code))
    end for

  end getBinding

  object C:

    import io.circe.parser.decode
    import io.circe.syntax.*
    import io.circe.*

    def jsonLike[T: Decoder: Encoder]: skunk.Codec[T] =
      compressedText
        .imap[T](str => decode[T](str).right.get)(_.asJson.noSpacesSortKeys)

    lazy val job =
      (jobId, bindingSpec).mapN(Job.apply)

    lazy val diagnostics = jsonLike[List[ResolvedClangDiagnostic]].opt
      .imap[List[ResolvedClangDiagnostic]](_.getOrElse(Nil))(l => Some(l))

    lazy val failureDTO =
      (
        text,
        diagnostics
      ).tupled.map(FailureDTO.apply)

    lazy val bindingSpec =
      (
        headerCode,
        packageName,
        clangFlags.opt
      ).tupled.asDecoder.map(BindingSpec.apply)

    lazy val generatedCode =
      (scalaCode.asDecoder, glueCode.opt).mapN(GeneratedCode.apply)

    lazy val clangFlags =
      jsonLike[List[String]]

    lazy val packageName =
      varchar(256).imap[PackageName](PackageName.apply)(_.value)

    lazy val workerId =
      uuid
        .imap[WorkerId](t => WorkerId(t))(_.value)

    lazy val compressedText =
      bytea.imap[String](bv => new String(Compression.decompressArray(bv)))(
        str => Compression.compressString(str)
      )

    lazy val headerCode =
      compressedText.imap[HeaderCode](HeaderCode.apply)(_.value)
    lazy val glueCode  = compressedText.imap(GlueCode.apply)(_.value)
    lazy val scalaCode = compressedText.imap(ScalaCode.apply)(_.value)

    lazy val jobId =
      uuid.imap[JobId](JobId.apply)(_.value)

    lazy val state =
      varchar(20).imap[State] {
        case "completed" => State.Completed
        case "failed"    => State.Failed
        case "added"     => State.Added
      } {
        case State.Added     => "added"
        case State.Completed => "completed"
        case State.Failed    => "failed"
      }
  end C

end StoreImpl

object Store:
  def open(): Resource[cats.effect.IO, Store] =
    val creds =
      Env[IO].entries
        .flatTap(entries => Log.info(s"Env: ${entries.toMap.keySet}"))
        .map: env =>
          val flyio = FlyIOLoader(env.toMap)

          flyio.loadPgCredentials.getOrElse(PgCredentials.defaults(env.toMap))

    given Tracer[IO] = Tracer.Implicits.noop[IO]

    creds
      .flatTap(cr => Log.info(s"Credentials: $cr"))
      .toResource
      .evalTap(migrate(_))
      .flatMap(
        open(_, SkunkConfig)
      )
  end open

  def migrate(postgres: PgCredentials)(using Tracer[IO]) =

    given dumbo.logging.Logger[IO] =
      case (dumbo.logging.LogLevel.Info, message) => Log.info(message)
      case (dumbo.logging.LogLevel.Warn, message) => Log.warn(message)

    Dumbo
      .withResourcesIn[IO]("db/migration")
      .apply(
        connection = ConnectionConfig(
          host = postgres.host,
          port = postgres.port,
          user = postgres.user,
          database = postgres.database,
          password = postgres.password,
          ssl =
            if postgres.ssl then ConnectionConfig.SSL.Trusted
            else ConnectionConfig.SSL.None
        ),
        defaultSchema = "public"
      )
      .runMigration
  end migrate

  def open(postgres: PgCredentials, skunkConfig: SkunkConfig)(using
      Tracer[IO]
  ): Resource[IO, Store] =
    Session
      .pooled[IO](
        host = postgres.host,
        port = postgres.port,
        user = postgres.user,
        database = postgres.database,
        password = postgres.password,
        strategy = skunkConfig.strategy,
        max = skunkConfig.maxSessions,
        debug = skunkConfig.debug,
        ssl = if postgres.ssl then skunk.SSL.Trusted else skunk.SSL.None
      )
      .map(StoreImpl(_))

end Store
