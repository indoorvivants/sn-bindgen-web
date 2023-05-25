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
    val service = Env[IO].get("WORKER_HOST").toResource.flatMap {
      case Some(host) =>
        BindgenServiceImpl.create(Uri.unsafeFromString(host))

      case None =>
        IO.raiseError(
          new java.lang.RuntimeException(
            "WORKER_HOST environment variable is not set, cannot establish connection to the worker"
          )
        ).toResource
    }

    service.flatMap { impl =>
      SimpleRestJsonBuilder.routes(impl).resource.map(_.orNotFound)
    }
  end routes

end app

class BindgenServiceImpl(workerClient: JobService[IO])
    extends BindgenService[IO]:
  override def getBinding(id: JobId): IO[GeneratedBinding] = ???
  override def getStatus(id: JobId): IO[GetStatusOutput]   = ???
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
