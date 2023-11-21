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
import fs2.io.file.Path
import java.io.FileWriter
import java.io.File

opaque type WorkerId = UUID
object WorkerId extends TotalWrapper[WorkerId, UUID]

class Worker private (id: WorkerId, store: Store, tempFolder: Option[Path]):
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
        generate(spec.packageName, spec.headerCode)
          // .onError(err => Log.error(s"Failed to procoess job $jobId", err))
          .flatMap { gc =>
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
      Log.info(s"temp folder: $tempFolder") *>
        Files[IO].tempFile(tempFolder, "", ".h", None).use { path =>
          scribe.info(s"In path $path")
          val writeFile =
            fs2.Stream
              .emit(code.value)
              .covary[IO]
              .through(Files[IO].writeUtf8(path))
              .compile
              .drain

          val llvmBin =
            sys.env.get("LLVM_BIN").toSeq.flatMap { path =>
              Seq("--llvm-bin", path)
            }

          val parsed = CLI.command.parse(
            Seq(
              "--package",
              packageName.value,
              "--header",
              path.toString,
              "--render.no-location"
            ) ++ llvmBin
          )

          parsed match
            case Left(help) =>
              val (modified, code) =
                if help.errors.nonEmpty then help.copy(body = Nil) -> -1
                else help                                          -> 0
              IO.raiseError(new RuntimeException(modified.toString))
            case Right(config) =>
              given Config = config.copy(minLogPriority =
                MinLogPriority(LogLevel.priority(LogLevel.trace))
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

              writeFile *> result
          end match
        }
    }
  end generate

end Worker

object Worker:
  def create(store: Store, tempFolder: Option[Path]): IO[Worker] =
    UUIDGen[IO].randomUUID.map(WorkerId(_)).map(Worker(_, store, tempFolder))
