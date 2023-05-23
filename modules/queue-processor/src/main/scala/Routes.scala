package bindgen.web.internal.jobs

import cats.effect.*
import org.http4s.*
import org.http4s.dsl.io.*
import smithy4s.http4s.SimpleRestJsonBuilder
import bindgen.web.domain.GeneratedBinding
import bindgen.web.domain.JobId
import bindgen.web.domain.HeaderCode
import bindgen.web.domain.PackageName
import cats.effect.std.UUIDGen
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import java.util.UUID
import cats.effect.std.Queue
import bindgen.web.domain.BindingSpec
import scala.scalanative.runtime.libc
import cats.syntax.all.*
import scala.concurrent.duration.*
import cats.effect.std.Env

object app extends snunit.Http4sApp:
  scribe.Logger.root
    .clearHandlers()
    .withHandler(
      writer = scribe.writer.SystemErrWriter,
      outputFormat = scribe.output.format.ANSIOutputFormat
    )
    .withMinimumLevel(scribe.Level.Debug)
    .replace()

  def routes =
    Queue
      .bounded[IO, (BindingSpec, Deferred[IO, JobId])](1024)
      .toResource
      .flatMap { queue =>
        val store = Env[IO].get("DB_PATH").toResource.flatMap {
          case None =>
            Store
              .open(":memory:")
              .evalTap(_ =>
                scribe.cats.io.warn(
                  "environment variable DB_PATH is not set, Sqlite database will be in-memory only"
                )
              )
          case Some(value) =>
            Store
              .open(value)
              .evalTap(_ => scribe.cats.io.info(s"Opened database in $value"))
        }
        store.flatMap { store =>
          submitter(queue, store).compile.drain.background *>
            SimpleRestJsonBuilder
              .routes(JobServiceImpl(queue))
              .resource
              .map(_.orNotFound)
        }
      }

  def submitter(
      submissionQueue: Queue[IO, (BindingSpec, Deferred[IO, JobId])],
      store: Store
  ) =
    fs2.Stream
      .fromQueueUnterminated(submissionQueue)
      .evalMap { (spec, latch) =>
        store.record(spec).flatMap(latch.complete)
      }
end app

class JobServiceImpl(
    submissionQueue: Queue[IO, (BindingSpec, Deferred[IO, JobId])]
) extends JobService[IO]:
  override def getStatus(id: JobId): IO[GetStatusOutput] = IO {
    GetStatusOutput(
      bindgen.web.internal.jobs.Status.FailedCase(Failed(Some("whaaaat")))
    )
  }

  override def submit(spec: BindingSpec): IO[SubmitOutput] =
    IO.deferred[JobId].flatMap { latch =>
      (submissionQueue
        .offer(spec -> latch) *>
        latch.get.timeout(500.millis))
        .map(SubmitOutput.apply)
    }

  end submit
end JobServiceImpl
