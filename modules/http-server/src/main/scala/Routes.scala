package bindgen.web

import cats.data.Kleisli
import cats.effect.*
import cats.effect.std.Env
import cats.syntax.all.*
import org.http4s.*
import smithy4s.http4s.SimpleRestJsonBuilder

import api.*

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
