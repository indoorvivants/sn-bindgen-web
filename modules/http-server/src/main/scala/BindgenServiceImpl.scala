package bindgen.web

import api.*
import cats.effect.*
import org.http4s.*
import smithy4s.http4s.SimpleRestJsonBuilder
import bindgen.web.domain.*
import org.http4s.ember.client.EmberClientBuilder
import bindgen.web.internal.jobs.JobService
import bindgen.web.api.SubmitOutput

class BindgenServiceImpl(workerClient: JobService[IO])
    extends BindgenService[IO]:

  override def health(): IO[HealthOutput] =
    Log.info("Running healthcheck...") *>
      workerClient.health().map { workerHealth =>
        HealthOutput(status = "ok", workerStatus = workerHealth.status)
      }

  override def getBinding(id: JobId): IO[GeneratedBinding] =
    workerClient.getBinding(id)
  override def getStatus(id: JobId): IO[GetStatusOutput] =
    workerClient.getStatus(id).map(s => GetStatusOutput(s.status))
  override def submit(
      spec: BindingSpec
  ): IO[SubmitOutput] =
    workerClient.submit(spec).map(so => SubmitOutput(so.id))
end BindgenServiceImpl

object BindgenServiceImpl:
  def create(workerUri: Uri) =
    EmberClientBuilder
      .default[IO]
      .build
      .flatMap(cl =>
        println(cl)
        SimpleRestJsonBuilder(JobService)
          .client[IO](cl)
          .uri(workerUri)
          .resource
      )
      .map(cl =>
        println(cl)
        BindgenServiceImpl(cl)
      )
end BindgenServiceImpl
