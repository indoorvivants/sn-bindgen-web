package bindgen.web.internal.jobs

import java.util.UUID
import opaque_newtypes.TotalWrapper
import cats.effect.std.*
import cats.effect.*
import cats.syntax.all.*
import bindgen.web.domain.JobId
import concurrent.duration.*
import bindgen.web.domain.*
import fs2.io.file.Files
import bindgen.rendering.RenderedOutput

opaque type WorkerId = UUID
object WorkerId extends TotalWrapper[WorkerId, UUID]

class Worker private (id: WorkerId, store: Store):
  def process =
    Queue.bounded[IO, JobId](100).toResource.flatMap { q =>
      val normalProcess =
        fs2.Stream
          .repeatEval(q.tryTake)
          .meteredStartImmediately(500.millis)
          .flatMap {
            case None =>
              val unprocessed = store
                .createLeases(id, 5)
                .evalTap(jobId => Log.info(s"Worker $id is leasing job $jobId"))

              val stolen = store
                .workSteal(id)
                .evalTap(jobId =>
                  Log.info(s"Worker $id is stealing old job $jobId")
                )

              (unprocessed ++ stolen).attempt
                .collect { case Right(jid) => jid }
                .evalMap(q.offer)

            case Some(jobId) =>
              fs2.Stream.eval {
                Log.info(s"Processing $jobId") *>
                  handle(jobId).handleErrorWith(exc =>
                    Log.error(s"Failed during handling of job $jobId", exc)
                  ) *>
                  store
                    .removeLease(id, jobId)
                    .handleErrorWith(exc =>
                      Log.error(
                        s"Failed to remove lease for job $jobId, held by worker $id",
                        exc
                      )
                    )
              }

          }

      normalProcess.compile.drain.background
    }

  def handle(jobId: JobId) =
    store.getNoncompleteSpec(jobId).flatMap {
      case None =>
        Log.error(
          s"Job $jobId was scheduled but is not found in the database in incomplete state"
        )
      case Some(spec) =>
        IO.sleep(2.seconds) *>
          Log.warn("The sleep delay is still in the code!") *>
          generate(spec.packageName, spec.headerCode).flatMap { gc =>
            store
              .complete(
                jobId,
                Left(
                  gc
                )
              )
          }

    }

  def generate(packageName: PackageName, code: HeaderCode) =
    import bindgen.*
    ZoneResource.useI {
      Files[IO].tempFile(None, "", ".h", None).use { path =>
        val wr =
          fs2.Stream
            .emit(code.value)
            .covary[IO]
            .through(Files[IO].writeUtf8(path))
            .compile
            .drain

        val parsed = CLI.command.parse(
          Seq("--package", packageName.value, "--header", path.toString)
        )

        parsed match
          case Left(help) =>
            val (modified, code) =
              if help.errors.nonEmpty then help.copy(body = Nil) -> -1
              else help                                          -> 0
            IO.raiseError(new RuntimeException(modified.toString))
          case Right(config) =>
            given Config = config.copy(minLogPriority =
              MinLogPriority(LogLevel.priority(LogLevel.warning))
            )

            def run(lang: Lang) =
              IO(
                bindgen.rendering.binding(
                  analyse(path.toString),
                  lang,
                  OutputMode.StdOut
                )
              )

            val scalaResult =
              run(Lang.Scala).map { case RenderedOutput.Single(lb) =>
                ScalaCode(lb.result)
              }

            val cResult =
              run(Lang.C).map { case RenderedOutput.Single(lb) =>
                val res = lb.result
                Option.when(res.nonEmpty)(GlueCode(res))
              }

            val result = (scalaResult, cResult).mapN(GeneratedCode.apply)

            wr *> result
        end match
      }
    }
  end generate

end Worker

object Worker:
  def create(store: Store): IO[Worker] =
    UUIDGen[IO].randomUUID.map(WorkerId(_)).map(Worker(_, store))
