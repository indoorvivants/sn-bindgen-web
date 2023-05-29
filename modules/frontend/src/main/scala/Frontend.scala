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
import scala.scalajs.js.annotation.JSGlobal
import bindgen.web.domain.Status.ProcessingCase
import bindgen.web.domain.Status.FailedCase
import bindgen.web.domain.Status.CompletedCase
import com.raquo.waypoint.SplitRender

class myApp(api: Api):
  lazy val splitter = SplitRender[Page, HtmlElement](router.currentPageSignal)
    .collectSignal[Page.BindingPage] { userPageSignal =>
      renderUserPage(userPageSignal)
    }
    .collectStatic(Page.Main) { div("Login page") }

  inline def codeBlock(language: String, value: String) =
    pre(
      code(
        cls := s"language-$language m-6 rounded-lg border-fuchsia-50 border-2",
        onMountCallback(mnt => hljs.highlightElement(mnt.thisNode.ref)),
        value
      )
    )

  def renderBinding(gb: GeneratedBinding) =
    div(
      cls := "bg-blue-950 font-sans text-white",
      h2("Original C header", cls := "text-xl font-bold m-6"),
      codeBlock("c", gb.spec.headerCode.value),
      gb.code.map { gc =>
        div(
          h2("Scala code", cls := "text-xl font-bold m-6"),
          codeBlock("scala", gc.scalaCode.value),
          gc.glueCode.map { glue =>
            div(
              h2("Glue C code"),
              codeBlock("c", glue.value)
            )
          }
        )
      }
    )

  def renderUserPage(userPageSignal: Signal[Page.BindingPage]): Div =
    div(
      child <-- userPageSignal.flatMap { page =>
        api
          .signal(
            _.users.getStatus(page.id).map(_.status)
          )
          .flatMap {
            case None => Signal.fromValue(i("gimme a minute"))
            case Some(value) =>
              value match
                case ProcessingCase(processing) =>
                  Signal.fromValue {
                    processing.remaining match
                      case None => b("Binding is still being processed")
                      case Some(value) =>
                        b(
                          s"Binding is being processed, there are $value ahead of it in the queue"
                        )
                  }

                case FailedCase(failed) =>
                  Signal.fromValue(b(s"Failed: ${failed.message}"))
                case CompletedCase(completed) =>
                  api.signal(_.users.getBinding(page.id)).map {
                    case None => i("gimme a minute")
                    case Some(value) =>
                      renderBinding(value)
                  }

          }

      }
    )
end myApp

sealed trait Page extends Product with Serializable
object Page:
  case class BindingPage(id: JobId) extends Page
  case object Main                  extends Page

import com.raquo.waypoint.*

val bindingPageRoute = Route(
  encode = (bindingPage: Page.BindingPage) => bindingPage.id.value.toString,
  decode = arg => Page.BindingPage(id = JobId(UUID.fromString(arg))),
  pattern = root / "binding" / segment[String] / endOfSegments
)

val loginRoute = Route.static(Page.Main, root)

val router = new Router[Page](
  routes = List(bindingPageRoute, loginRoute),
  getPageTitle =
    _.toString, // mock page title (displayed in the browser tab next to favicon)
  serializePage = page =>
    page match
      case Page.Main            => ""
      case Page.BindingPage(id) => s"binding: ${id}"
  ,
  deserializePage = pageStr =>
    pageStr match
      case ""              => Page.Main
      case s"binding: $id" => Page.BindingPage(JobId(UUID.fromString(id)))
)(
  popStateEvents = windowEvents(
    _.onPopState
  ),
  owner = unsafeWindowOwner
)

@main def main =
  val app = myApp(Api.create())
  renderOnDomContentLoaded(
    dom.document.getElementById("appContainer"),
    div(
      child <-- app.splitter.signal
    )
  )
end main

class Api private (
    val users: BindgenService[IO]
):
  import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*
  def future[A](a: Api => IO[A]): Future[A] =
    a(this).unsafeToFuture()

  def stream[A](a: Api => IO[A]): EventStream[A] =
    EventStream.fromFuture(future(a))

  def signal[A](a: Api => IO[A]): Signal[Option[A]] =
    Signal.fromFuture(future(a))
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

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSGlobal
object hljs extends js.Object:

  def highlightElement(element: dom.HTMLElement): Unit = js.native

end hljs
