import com.raquo.laminar.api.L.*
import org.scalajs.dom
import bindgen.web.api.BindgenService
import cats.effect.IO
import scala.concurrent.Future
import cats.effect.unsafe.implicits.global
import org.http4s.dom.FetchClientBuilder
import org.http4s.Uri
import smithy4s.http4s.SimpleRestJsonBuilder
import java.util.UUID
import bindgen.web.domain.*

def myApp(api: Api) =
  div(
    p("Your yearly incomes is £"),
    child.text <-- api
      .stream(
        _.users.getBinding(
          JobId(UUID.fromString("3beb0e2a-801e-44f9-85c1-68b1f02994ca"))
        )
      )
      .map(_.toString)
  )
end myApp

@main def main =
  renderOnDomContentLoaded(
    dom.document.getElementById("appContainer"),
    myApp(Api.create())
  )

class Api private (
    val users: BindgenService[IO]
):
  import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*
  def future[A](a: Api => IO[A]): Future[A] =
    a(this).unsafeToFuture()

  def stream[A](a: Api => IO[A]): EventStream[A] =
    EventStream.fromFuture(future(a))
end Api

object Api:
  def create(location: String = dom.window.location.origin.get) =
    val uri = Uri.unsafeFromString(location)

    val client = FetchClientBuilder[IO].create

    Api(
      SimpleRestJsonBuilder(BindgenService)
        .client(client)
        .uri(uri)
        .use
        .fold(throw _, identity)
    )
  end create
end Api
