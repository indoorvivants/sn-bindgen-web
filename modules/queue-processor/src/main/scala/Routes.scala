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

class JobServiceImpl(
    submissionQueue: Queue[IO, (BindingSpec, Deferred[IO, SubmissionResult])],
    order: Ref[IO, Map[JobId, Int]],
    store: Store
) extends JobService[IO]:
  override def getBinding(id: JobId): IO[GeneratedBinding] =
    store.getBinding(id)

  override def getStatus(id: JobId): IO[GetStatusOutput] =
    store.isCompleted(id).flatMap {
      case true =>
        GetStatusOutput(
          bindgen.web.domain.Status.CompletedCase(Completed())
        ).pure[IO]
      case false =>
        order.get.map { m =>
          GetStatusOutput(
            bindgen.web.domain.Status
              .ProcessingCase(
                bindgen.web.domain.Processing(remaining = m.get(id))
              )
          )
        }
    }

  override def submit(spec: BindingSpec): IO[SubmitOutput] =
    validate(spec) match
      case None =>
        IO.deferred[SubmissionResult].flatMap { latch =>
          (submissionQueue
            .offer(spec -> latch) *>
            latch.get.timeoutTo(
              500.millis,
              IO.raiseError(
                SubmissionFailed(Some("Timed out trying to save the job"))
              )
            )).flatMap {
            case SubmissionResult.Ok(id) =>
              SubmitOutput(id).pure[IO]
            case SubmissionResult.Failed(msg) =>
              SubmissionFailed(Some(msg)).raiseError
          }
        }

      case Some(err) =>
        err.raiseError

  end submit

  private def validate(spec: BindingSpec): Option[ValidationError] =
    Option.when(spec.headerCode.value.trim.isEmpty)(
      ValidationError("Header code cannot be empty")
    ) orElse
      Option.when(spec.packageName.value.trim.isEmpty)(
        ValidationError("Package name cannot be empty")
      )
  end validate
end JobServiceImpl
