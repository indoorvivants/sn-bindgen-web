package bindgen.web.internal.jobs

import bindgen.rendering.RenderedOutput
import bindgen.web.domain.JobId
import bindgen.web.domain.*
import cats.effect.*
import cats.effect.std.*
import cats.syntax.all.*
import fs2.io.file.Files
import fs2.io.file.Path
import opaque_newtypes.TotalWrapper

import java.io.File
import java.util.UUID

import concurrent.duration.*

opaque type WorkerId = UUID
object WorkerId extends TotalWrapper[WorkerId, UUID]

class Worker private (id: WorkerId, store: Store, tempFolder: Option[Path]):
  def process: Resource[IO, IO[OutcomeIO[Unit]]] =
    Queue.bounded[IO, JobId](100).toResource.flatMap { q =>
      val normalProcess =
        fs2.Stream
          .repeatEval(q.tryTake)
          .meteredStartImmediately(1.second)
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
        generate(spec.packageName, spec.headerCode).attempt.flatMap {
          case Left(exc) =>
            Log.info(s"Job $jobId finished processing with clang errors") *>
              store.complete(
                jobId,
                Right(
                  ClangErrors(
                    List(
                      ClangError(severity = "error", message = exc.getMessage)
                    )
                  )
                )
              )
          case Right(generatedCode) =>
            Log.info(s"Job $jobId finished successfully") *>
              store
                .complete(
                  jobId,
                  Left(
                    generatedCode
                  )
                )
        }

    }

  def generate(packageName: PackageName, code: HeaderCode) =
    import bindgen.*
    ZoneResource.useI {
      Log.info(s"temp folder: $tempFolder") *>
        Files[IO].tempFile(tempFolder, "", ".h", None).use { path =>
          Log.infoUnsafe(s"In path $path")
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

          val bindgenArgs = Seq(
            "--package",
            packageName.value,
            "--header",
            path.toString,
            "--render.no-location"
          ) ++ llvmBin

          val parsed = CLI.command.parse(
            bindgenArgs
          )

          parsed match
            case Left(help) =>
              val (modified, code) =
                if help.errors.nonEmpty then help.copy(body = Nil) -> -1
                else help                                          -> 0

              Log.error(
                s"Failed to create bindgen config invocation failed with $modified"
              ) *>
                IO.raiseError(new RuntimeException(modified.toString))
            case Right(config) =>
              given Config = config.copy(minLogPriority =
                MinLogPriority(LogLevel.priority(LogLevel.trace))
              )

              Log
                .infoUnsafe(
                  s"Invoking bindgen with `${bindgenArgs.mkString(" ")}`"
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
