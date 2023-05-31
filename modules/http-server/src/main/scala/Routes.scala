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
    println("here")
    println(sys.env)
    val service = Env[IO].get("WORKER_HOST").toResource.flatMap {
      case Some(host) =>
        println(host)
        BindgenServiceImpl.create(Uri.unsafeFromString(host))

      case None =>
        println("crash baby")
        IO.raiseError(
          new java.lang.RuntimeException(
            "WORKER_HOST environment variable is not set, cannot establish connection to the worker"
          )
        ).toResource
    }

    service.flatMap { impl =>

      println(impl)

      def handleErrors(routes: HttpRoutes[IO]) =
        println(routes)
        routes.orNotFound.onError { exc =>
          Kleisli(request => Log.error("Request failed", request.toString, exc))
        }
      end handleErrors

      SimpleRestJsonBuilder.routes(impl).resource.map(handleErrors)
    }
  end routes

end app
