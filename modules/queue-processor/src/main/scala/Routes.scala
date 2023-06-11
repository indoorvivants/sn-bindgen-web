package bindgen.web.internal.jobs

import bindgen.web.domain.*
import cats.data.Kleisli
import cats.effect.*
import cats.effect.std.*
import cats.syntax.all.*
import org.http4s.*
import org.http4s.dsl.io.*
import smithy4s.http4s.SimpleRestJsonBuilder

import scala.concurrent.duration.*

enum SubmissionResult:
  case Ok(id: JobId)
  case Failed(msg: String)

object app extends snunit.Http4sApp:
  def handleErrors(routes: HttpRoutes[IO]) =
    routes.orNotFound.onError { exc =>
      Kleisli(request => Log.error("Request failed", request.toString, exc))
    }

  def routes =
    Queue
      .bounded[IO, (BindingSpec, Deferred[IO, SubmissionResult])](1024)
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
                .routes(JobServiceImpl(queue, ref, store))
                .resource
                .map(handleErrors)
          }
        }
      }

  def submitter(
      submissionQueue: Queue[IO, (BindingSpec, Deferred[IO, SubmissionResult])],
      store: Store
  ) =
    fs2.Stream
      .fromQueueUnterminated(submissionQueue)
      .evalMap { (spec, latch) =>
        store
          .record(spec)
          .attempt
          .flatMap {
            case Left(err) =>
              UUIDGen.randomUUID[IO].flatMap { errorId =>
                Log.error(
                  s"errorId=${errorId} Failed to record job spec",
                  err
                ) *>
                  latch.complete(
                    SubmissionResult.Failed(
                      s"Internal database error, quote this ID to your system administration: $errorId"
                    )
                  )
              }

            case Right(value) => latch.complete(SubmissionResult.Ok(value))
          }
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
