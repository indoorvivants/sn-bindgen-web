package bindgen.web.frontend

import com.raquo.waypoint.*
import com.raquo.laminar.api.L.*
import bindgen.web.domain.JobId
import java.util.UUID

sealed trait Page extends Product with Serializable
object Page:
  case class BindingPage(id: JobId) extends Page
  case object Main                  extends Page

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

def redirectTo(pg: Page)(using router: Router[Page]) =
  router.pushState(pg)

def forceRedirectTo(pg: Page)(using router: Router[Page]) =
  router.replaceState(pg)

def navigateTo(page: Page)(using router: Router[Page]): Binder[HtmlElement] =
  Binder { el =>
    import org.scalajs.dom

    val isLinkElement = el.ref.isInstanceOf[dom.html.Anchor]

    if isLinkElement then el.amend(href(router.absoluteUrlForPage(page)))

    (onClick
      .filter(ev =>
        !(isLinkElement && (ev.ctrlKey || ev.metaKey || ev.shiftKey || ev.altKey))
      )
      .preventDefault
      --> (_ => redirectTo(page))).bind(el)
  }
