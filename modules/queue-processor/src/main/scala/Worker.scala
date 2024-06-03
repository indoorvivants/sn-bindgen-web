package bindgen.web.internal.jobs

import bindgen.*
import bindgen.rendering.RenderedOutput
import bindgen.web.domain.*
import cats.effect.*
import cats.effect.std.*
import cats.syntax.all.*
import fs2.io.file.Files
import fs2.io.file.Path
import opaque_newtypes.TotalWrapper

import java.io.File
import java.util.UUID

opaque type WorkerId = UUID
object WorkerId extends TotalWrapper[WorkerId, UUID]

class Worker private (
    id: WorkerId,
    store: Store,
    tempFolder: Option[Path],
    config: RuntimeConfig,
    driver: InteractiveDriver
):
  def process: Resource[IO, IO[OutcomeIO[Unit]]] =
    Queue.bounded[IO, JobId](config.workerQueueSize).toResource.flatMap { q =>
      val normalProcess =
        fs2.Stream
          .repeatEval(IO.cede *> q.tryTake)
          .meteredStartImmediately(config.workerPulse)
          .flatMap {
            case None =>
              val unprocessed = store
                .createLeases(id, config.leaseLimit)
                .evalTap(jobId => Log.info(s"Worker $id is leasing job $jobId"))

              val stolen = fs2.Stream.evalSeq(
                store
                  .workSteal(id, config.workStealLimit, config.jobStaleness)
                  .flatTap(jobId =>
                    Log.info(s"Worker $id is stealing jobs $jobId")
                  )
              )

              (unprocessed /*++ stolen*/ ).attempt
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
                BindgenResult.Error(exc.getMessage())
              )
          case Right(generatedCode) =>
            Log.info(s"Job $jobId finished successfully") *>
              store
                .complete(
                  jobId,
                  generatedCode
                )
        }

    }

  def generate(packageName: bindgen.web.domain.PackageName, code: HeaderCode) =
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

          def run(lang: Lang) =
            val context =
              bindgen.Context(
                packageName = bindgen.PackageName(packageName.value),
                HeaderFile(path.toNioPath.toString),
                lang = lang
              )

            IO(
              driver
                .createBinding(
                  context,
                  OutputMode.StdOut
                )
            )
          end run

          val scalaResult =
            run(Lang.Scala).map {
              _.map { case RenderedOutput.Single(lb) =>
                ScalaCode(lb.result)
              }
            }

          val cResult =
            run(Lang.C).map {
              _.map { case RenderedOutput.Single(lb) =>
                val res = lb.result
                Option.when(res.nonEmpty)(GlueCode(res))
              }
            }

          val result = (scalaResult, cResult).mapN:
            case (Left(err), _) => BindgenResult.adapt(err)
            case (_, Left(err)) => BindgenResult.adapt(err)
            case (Right(scala), Right(glue)) =>
              BindgenResult.Success(GeneratedCode(scala, glue))

          writeFile *> result
        }
    }
  end generate

end Worker

object Worker:
  def create(
      store: Store,
      tempFolder: Option[Path],
      config: RuntimeConfig
  ): Resource[IO, Worker] =
    val workerId = UUIDGen[IO].randomUUID
      .map(WorkerId(_))
      .toResource

    (workerId, driverInit(tempFolder))
      .mapN(Worker(_, store, tempFolder, config, _))
  end create

  private def driverInit(tempFolder: Option[Path]) =
    val config =
      val initial = Config.withDefaults()

      Env[IO]
        .get("LLVM_BIN")
        .flatTap(bin => Log.info(s"LLVM_BIN env is set to $bin"))
        .map: llvmBin =>
          initial.copy(
            systemPathDetection = llvmBin
              .map(bin => SystemPathDetection.FromLLVM(LLVMBin(bin)))
              .getOrElse(initial.systemPathDetection),
            tempDir = tempFolder
              .map(path => TempPath(path.toString))
              .getOrElse(initial.tempDir)
          )
    end config

    ZoneResource.flatMapI:
      config.toResource.flatMap: config =>
        IO.fromEither(
          InteractiveDriver
            .init(using config)
            .leftMap(err =>
              new RuntimeException(s"Bindgen init error: [${err.render}]")
            )
        ).toResource
  end driverInit

end Worker

