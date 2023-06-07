package bindgen.web.http

import cats.data.Kleisli
import cats.effect.*
import cats.effect.std.Env
import cats.syntax.all.*
import org.http4s.*
import smithy4s.http4s.SimpleRestJsonBuilder

import bindgen.web.api.*

object HttpServer extends snunit.Http4sApp:
  import org.http4s.dsl.io.*
  final def routes = Log.info("what the fuck is going on").toResource *> IO(
    HttpRoutes
      .of[IO] { case req =>
        Log.info(s"Responding to $req") *> Ok(s"${req.toString}")
      }
      .orNotFound
  ).toResource

  // def routes1 =
  //   Log.unsafe.info("here")
  //   Log.unsafe.info(sys.env.toString)
  //   val service = Env[IO].get("WORKER_HOST").toResource.flatMap {
  //     case Some(host) =>
  //       Log.unsafe.info(host)
  //       BindgenServiceImpl.create(Uri.unsafeFromString(host))

  //     case None =>
  //       Log.unsafe.info("crash baby")
  //       IO.raiseError(
  //         new java.lang.RuntimeException(
  //           "WORKER_HOST environment variable is not set, cannot establish connection to the worker"
  //         )
  //       ).toResource
  //   }

  //   service.flatMap { impl =>

  //     Log.unsafe.info(impl.toString)

  //     def handleErrors(routes: HttpRoutes[IO]) =
  //       Log.unsafe.info(routes.toString)
  //       routes.orNotFound.onError { exc =>
  //         Kleisli(request => Log.error("Request failed", request.toString, exc))
  //       }
  //     end handleErrors

  //     SimpleRestJsonBuilder.routes(impl).resource.map(handleErrors)
  //   }
  // end routes1
end HttpServer
