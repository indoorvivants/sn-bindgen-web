package bindgen.web.frontend

import bindgen.web.api.BindgenService
import bindgen.web.domain.BindingStatus.*
import bindgen.web.domain.*
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*
import org.http4s.Uri
import org.http4s.dom.FetchClientBuilder
import org.scalajs.dom
import smithy4s.http4s.SimpleRestJsonBuilder

import java.util.UUID
import scala.concurrent.Future
import scala.scalajs.js.annotation.JSGlobal
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*

class myApp(using api: Api, router: Router[Page]):
  val signal = SplitRender[Page, HtmlElement](router.currentPageSignal)
    .collectSignal[Page.BindingPage] { userPageSignal =>
      renderBindingPage(userPageSignal)
    }
    .collectStatic(Page.Main) { renderMainPage }
    .signal

end myApp

@main def main =
  val app = myApp(using Api.create(), router)
  renderOnDomContentLoaded(
    dom.document.getElementById("appContainer"),
    div(
      child <-- app.signal
    )
  )
end main

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
