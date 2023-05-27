package bindgen.web

import api.*
import cats.effect.*
import org.http4s.*
import org.http4s.dsl.io.*
import smithy4s.http4s.SimpleRestJsonBuilder
import bindgen.web.domain.*
import cats.effect.std.UUIDGen
import org.http4s.ember.client.EmberClientBuilder
import bindgen.web.internal.jobs.JobService
import cats.effect.std.Env
import bindgen.web.internal.jobs.JobServiceGen
import bindgen.web.api.SubmitOutput
import java.nio.file.Paths

class BindgenServiceImpl(workerClient: JobService[IO])
    extends BindgenService[IO]:
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
        SimpleRestJsonBuilder(JobService)
          .client[IO](cl)
          .uri(workerUri)
          .resource
      )
      .map(BindgenServiceImpl(_))
end BindgenServiceImpl
