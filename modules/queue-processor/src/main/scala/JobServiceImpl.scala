package bindgen.web.internal.jobs

import bindgen.web.domain.*
import cats.effect.*
import cats.effect.std.*
import cats.syntax.all.*
import scala.concurrent.duration.*

class JobServiceImpl(
    submissionQueue: Queue[IO, (BindingSpec, Deferred[IO, SubmissionResult])],
    order: Ref[IO, Map[JobId, Int]],
    store: Store
) extends JobService[IO]:
  override def health(): IO[HealthOutput] =
    IO(HealthOutput("ok"))

  override def getBinding(id: JobId): IO[GeneratedBinding] =
    store.getBinding(id)

  override def getStatus(id: JobId): IO[GetStatusOutput] =
    store.getState(id).flatMap {
      case None =>
        GetStatusOutput(BindingStatus.notFound(NotFound())).pure[IO]
      case Some(State.Completed) =>
        GetStatusOutput(
          BindingStatus.CompletedCase(Completed())
        ).pure[IO]
      case Some(State.Added) =>
        order.get.map { m =>
          GetStatusOutput(
            BindingStatus
              .ProcessingCase(
                bindgen.web.domain.Processing(remaining = m.get(id))
              )
          )
        }

      case Some(State.Failed) =>
        store
          .getFailure(id)
          .map: f =>
            GetStatusOutput(
              bindgen.web.domain.BindingStatus
                .FailedCase(
                  Failed(
                    message = f.get.message,
                    diagnostics =
                      f.map(_.diags.map(rv => Diag(rv.severity, rv.msg)))
                  )
                )
            )
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
