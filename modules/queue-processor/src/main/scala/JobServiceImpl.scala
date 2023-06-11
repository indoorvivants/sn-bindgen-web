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
