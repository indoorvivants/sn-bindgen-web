package bindgen.web

import cats.effect.*
// import snunit.*
// object HelloWorldExample {
//   def main(args: Array[String]): Unit = {
//     SyncServerBuilder
//       .setRequestHandler(req =>
//         req.send(
//           statusCode = StatusCode.OK,
//           content = "Hello world!\n",
//           headers = Headers("Content-Type" -> "text/plain")
//         )
//       )
//       .build()
//       .listen()
//   }
// }
import org.http4s.*
import org.http4s.dsl.io.*
object app extends snunit.Http4sApp:
  def routes = Routes.all.map(_.orNotFound).evalTap(_ => IO.println("howdy"))
  // def routes = Resource.pure(
  //   HttpRoutes
  //     .of[IO] { case req =>
  //       Ok(s"howdy $req")
  //     }
  //     .orNotFound
  // )
end app

import smithy4s.hello.*

object HelloWorldImpl extends HelloWorldService[IO]:
  def hello(name: String, town: Option[String]): IO[Greeting] = IO.pure {
    town match
      case None    => Greeting(s"Hello $name!")
      case Some(t) => Greeting(s"Hello $name from $t!")
  }

import smithy4s.http4s.SimpleRestJsonBuilder

object Routes:
  private val example: Resource[IO, HttpRoutes[IO]] =
    SimpleRestJsonBuilder.routes(HelloWorldImpl).resource

  val all: Resource[IO, HttpRoutes[IO]] = example
