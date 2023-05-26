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
                Log.warn(
                  "environment variable DB_PATH is not set, Sqlite database will be in-memory only"
                )
              )
          case Some(value) =>
            Store
              .open(value)
              .evalTap(_ => Log.info(s"Opened database in $value"))
        }
        IO.ref(Map.empty[JobId, Int]).toResource.flatMap { ref =>
          store.flatMap { store =>
            Worker.create(store).toResource.flatMap(_.process) *>
              submitter(queue, store).compile.drain.background *>
              orderingRefresh(ref, store).compile.drain.background *>
              SimpleRestJsonBuilder
                .routes(JobServiceImpl(queue, store))
                .resource
                .map(_.orNotFound)
          }
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

  def orderingRefresh(
      ref: Ref[IO, Map[JobId, Int]],
      store: Store
  ) =
    fs2.Stream
      .repeatEval(store.getOrdering())
      .evalTap { m =>
        m.values.maxOption.traverse(i =>
          Log.info(s"Number of bindings left to complete: $i")
        )

      }
      .evalMap(ref.set)
      .meteredStartImmediately(5.seconds)
end app

class JobServiceImpl(
    submissionQueue: Queue[IO, (BindingSpec, Deferred[IO, JobId])],
    store: Store
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
