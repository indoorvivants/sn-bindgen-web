package bindgen.web

import cats.data.Kleisli
import cats.effect.*
import cats.effect.std.Env
import cats.syntax.all.*
import org.http4s.*
import smithy4s.http4s.SimpleRestJsonBuilder

import api.*
import com.comcast.ip4s.*
import org.http4s.ember.server.EmberServerBuilder
import decline_derive.CommandApplication

object BindgenWebHttpServer extends IOApp:
  case class CLI(host: Option[String], port: Option[String])
      derives CommandApplication

  override def run(args: List[String]): IO[ExitCode] =
    val (host, port) =
      val cli = CommandApplication.parseOrExit[CLI](args, sys.env)
      val port = cli.port
        .flatMap(port =>
          Port.fromString(port).orElse(sys.error(s"Invalid port $port"))
        )
        .getOrElse(port"8080")

      val host = cli.host
        .flatMap(host =>
          Host.fromString(host).orElse(sys.error(s"Invalid host $host"))
        )
        .getOrElse(host"localhost")

      (host, port)
    end val

    routes
      .flatMap: r =>
        EmberServerBuilder
          .default[IO]
          .withPort(port)
          .withHost(host)
          .withHttpApp(r)
          .build
          .evalTap(srv => Log.info(s"Server started on ${srv.baseUri}"))
      .useForever
  end run

  def routes =
    val service = Env[IO]
      .get("WORKER_HOST")
      .toResource
      .flatMap {
        case Some(host) =>
          Log.info(s"Starting web server against $host worker")
          BindgenServiceImpl.create(Uri.unsafeFromString(host))

        case None =>
          IO.raiseError(
            RuntimeException(
              "WORKER_HOST environment variable is not set, cannot establish connection to the worker"
            )
          ).toResource
      }
      .onError(err => Log.error("Worker cannot be accessed", err).toResource)

    service.flatMap { impl =>
      SimpleRestJsonBuilder.routes(impl).resource.map(handleErrors)
    }
  end routes

  def handleErrors(
      routes: HttpRoutes[IO]
  ): Kleisli[IO, Request[IO], Response[IO]] =
    routes.orNotFound.onError { exc =>
      Kleisli(request =>
        Log.error("WEB: Request failed", request.toString, exc)
      )
    }
end BindgenWebHttpServer
