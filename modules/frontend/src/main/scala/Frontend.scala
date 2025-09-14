package bindgen.web.frontend

import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*
import org.scalajs.dom

class myApp(using api: Api, router: Router[Page]):
  val recentBindings = RecentBindingsManager(5, unsafeWindowOwner)
  val signal = SplitRender[Page, HtmlElement](router.currentPageSignal)
    .collectSignal[Page.BindingPage] { userPageSignal =>
      renderBindingPage(userPageSignal, recentBindings)
    }
    .collectStatic(Page.Main) { renderMainPage(recentBindings) }
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
