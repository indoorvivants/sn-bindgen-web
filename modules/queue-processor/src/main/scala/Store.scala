package bindgen.web.internal.jobs

import bindgen.web.domain.*
import cats.effect.*
import cats.effect.std.UUIDGen
import cats.syntax.all.*
import porcupine.*
import scodec.bits.ByteVector

import java.util.UUID
import fs2.io.file.Files
import fs2.io.file.Path
import scala.concurrent.duration.FiniteDuration

enum State:
  case Added
  case Completed
  case Failed

trait Store:
  def complete(id: JobId, code: Either[GeneratedCode, ClangErrors]): IO[Unit]
  def removeLease(workerId: WorkerId, jid: JobId): IO[Unit]
  def getState(jid: JobId): IO[Option[State]]
  def workSteal(
      workerId: WorkerId,
      limit: Int,
      staleness: FiniteDuration
  ): fs2.Stream[IO, JobId]
  def createLeases(workerId: WorkerId, limit: Int): fs2.Stream[IO, JobId]
  def getOrdering(): IO[Map[JobId.Type, Int]]
  def record(spec: BindingSpec): IO[JobId]
  def getBinding(id: JobId): IO[GeneratedBinding]
  def getNoncompleteSpec(id: JobId): IO[Option[BindingSpec]]
end Store

case class Job(id: JobId, spec: BindingSpec)

class StoreImpl(db: Database[IO]) extends Store:
  import Codec.*

  override def record(spec: BindingSpec): IO[JobId] =
    IO.realTimeInstant.flatMap { instant =>
      UUIDGen.randomUUID[IO].map(JobId(_)).flatMap { id =>
        db.execute(
          sql"""
          |insert into bindings (id, state, state_date, header_code, package_name) 
          | values (${C.jobId}, ${C.state}, $integer, ${C.headerCode}, ${C.packageName});""".stripMargin.command,
          (
            id,
            State.Added,
            instant.getEpochSecond(),
            spec.headerCode,
            spec.packageName
          )
        ).as(id)
      }
    }

  override def complete(
      id: JobId,
      code: Either[GeneratedCode, ClangErrors]
  ): IO[Unit] =
    IO.realTimeInstant.map(_.getEpochSecond()).flatMap { instant =>
      code match
        case Left(code) =>
          val complete =
            db.execute(
              sql"update bindings set state = 'completed', state_date = $integer where id = ${C.jobId};".command,
              (instant, id)
            )
          val setCode = db.execute(
            sql"insert into generated_code (binding_id, scala_code, glue_code) values (${C.jobId}, ${C.scalaCode}, ${C.glueCode.opt});".command,
            (id, code.scalaCode, code.glueCode)
          )

          setCode *> complete // note, this is not atomic

        case Right(value) =>
          db.execute(
            sql"update bindings set state = 'failed', state_date = $integer where id = ${C.jobId};".command,
            (instant, id)
          )
      end match

    }

  override def removeLease(workerId: WorkerId, jid: JobId): IO[Unit] =
    db.execute(
      sql"delete from leases where binding_id = ${C.jobId} and worker_id = ${C.workerId};".command,
      (jid, workerId)
    )

  override def getState(jobId: JobId): IO[Option[State]] =
    db.option(
      sql"select state from bindings where id = ${C.jobId}"
        .query(C.state),
      jobId
    )

  val timeStream = fs2.Stream.eval(IO.realTimeInstant).map(_.getEpochSecond())

  override def workSteal(
      workerId: WorkerId,
      limit: Int,
      staleness: FiniteDuration
  ): fs2.Stream[IO, JobId] =
    timeStream.flatMap { instant =>
      db.stream(
        sql"""
        | update leases set worker_id = ${C.workerId} where binding_id 
        |  in (select binding_id from leases where ($integer - checked_in_at > $integer) limit $integer)
        |  returning binding_id
        """.stripMargin.query(C.jobId),
        (workerId, instant, staleness.toSeconds, limit),
        limit
      )
    }
  end workSteal

  override def createLeases(
      workerId: WorkerId,
      limit: Int
  ): fs2.Stream[IO, JobId] =
    fs2.Stream.eval(IO.realTimeInstant.map(_.getEpochSecond())).flatMap {
      inst =>
        db.stream(
          sql"""
            |insert into leases 
            |  select 
            |    b.id, ${C.workerId}, ${integer} 
            |  from 
            |    bindings b left join leases l on l.binding_id = b.id 
            |  where 
            |    l.worker_id is null and
            |    state in ('added')
            |  limit ${integer} 
            |returning binding_id;
          """.stripMargin.query(C.jobId),
          (workerId, inst, limit),
          limit.min(100)
        )
    }

  override def getOrdering(): IO[Map[JobId.Type, Int]] =
    db.stream(
      sql"""
         |select 
         |  id, row_number() over (order by state_date) 
         |from 
         |  bindings 
         |where 
         |  state in ('added');
         """.stripMargin.query(
        (C.jobId, integer.asDecoder.map(_.toInt)).tupled
      ),
      (),
      1024
    ).compile
      .toVector
      .map(_.toMap)

  override def getNoncompleteSpec(id: JobId): IO[Option[BindingSpec]] =
    db.option(
      sql"""select header_code, package_name, null from bindings where id = ${C.jobId} and state in ('added')"""
        .query(C.bindingSpec),
      id
    )

  override def getBinding(id: JobId): IO[GeneratedBinding] =
    val getCode = db.option(
      sql"select scala_code, glue_code from generated_code where binding_id = ${C.jobId}"
        .query(
          C.generatedCode
        ),
      id
    )

    val getSpec = db.option(
      sql"select header_code, package_name, null, state_date from bindings where id = ${C.jobId}"
        .query(C.bindingSpec.product(integer.opt)),
      id
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

    import io.circe.{Decoder, Encoder}
    import io.circe.parser.decode
    import io.circe.syntax.*
    import io.circe.*
    import porcupine.Codec.*

    inline def jsonLike[T: Decoder: Encoder] =
      text.imap[T](str => decode[T](str).right.get)(_.asJson.noSpacesSortKeys)

    lazy val job =
      (jobId, bindingSpec).mapN(Job.apply)

    lazy val bindingSpec =
      (
        headerCode,
        packageName,
        clangFlags.opt
      ).tupled.asDecoder.map(BindingSpec.apply)

    lazy val generatedCode =
      (scalaCode.asDecoder, glueCode.opt).mapN(GeneratedCode.apply)

    lazy val clangFlags = jsonLike[List[String]]

    lazy val packageName = text.imap[PackageName](PackageName.apply)(_.value)

    lazy val workerId =
      text.imap[WorkerId](t => WorkerId(UUID.fromString(t)))(_.value.toString)

    lazy val compressedText =
      blob.imap[String](bv => new String(Compression.decompressByteVector(bv)))(
        str => ByteVector(Compression.compressString(str))
      )

    lazy val headerCode =
      compressedText.imap[HeaderCode](HeaderCode.apply)(_.value)
    lazy val glueCode  = compressedText.imap(GlueCode.apply)(_.value)
    lazy val scalaCode = compressedText.imap(ScalaCode.apply)(_.value)

    lazy val jobId =
      text.imap[JobId](t => JobId(UUID.fromString(t)))(_.value.toString)

    lazy val state =
      text.imap[State] {
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
  def open(filename: String): Resource[cats.effect.IO, Store] =
    val path = Path(filename)
    Files[IO]
      .exists(path)
      .flatMap {
        case true =>
          Log.info(
            s"`$filename` file exists, proceeding to just open the database"
          )
        case false =>
          Log.info(
            s"`$filename` file doesn't exist, creating folder structure for it"
          ) *>
            path.parent.traverse(Files[IO].createDirectories)

      }
      .toResource *>
      Database
        .open[IO](filename)
        .evalTap { db =>
          val migrations = List(
            sql"""
           |create table if not exists bindings(
           |  id text primary key not null,
           |  state text not null,
           |  state_date int not null,
           |  header_code blob not null,
           |  package_name text not null
           |);""".stripMargin,
            sql"""
           |create table if not exists generated_code(
           |  binding_id text primary key not null,
           |  scala_code blob not null,
           |  glue_code blob 
           |);""".stripMargin,
            sql"""
           |create table if not exists leases(
           |  binding_id text not null,
           |  worker_id text not null,
           |  checked_in_at int not null
           |);
         """.stripMargin
          )
          migrations.map(_.command).traverse(db.execute(_))
        }
        .map(StoreImpl(_))
  end open

end Store
