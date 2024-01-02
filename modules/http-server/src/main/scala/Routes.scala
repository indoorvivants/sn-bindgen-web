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
import cats.syntax.all.*
import cats.data.Kleisli

object app extends snunit.Http4sApp:
  def routes =
    val service = Env[IO].get("WORKER_HOST").toResource.flatMap {
      case Some(host) =>
        System.err.println(s"Starting web server against $host worker")
        BindgenServiceImpl.create(Uri.unsafeFromString(host))

      case None =>
        IO.raiseError(
          new java.lang.RuntimeException(
            "WORKER_HOST environment variable is not set, cannot establish connection to the worker"
          )
        ).toResource
          .onError(err => Log.error("Worker cannot be started", err).toResource)
    }

    service.flatMap { impl =>
      SimpleRestJsonBuilder.routes(impl).resource.map(handleErrors)
    }
  end routes

  def handleErrors(routes: HttpRoutes[IO]) =
    routes.orNotFound.onError { exc =>
      Kleisli(request =>
        Log.error("WEB: Request failed", request.toString, exc)
      )
    }

end app
