package bindgen.web.frontend

import com.raquo.laminar.api.L.*
import bindgen.web.domain.*
import bindgen.web.domain.Status.*
import com.raquo.waypoint.*

private def renderBinding(gb: GeneratedBinding)(using Router[Page]) =
  div(
    pageTitle,
    a(href := "#", "← Go back", navigateTo(Page.Main)),
    cls := "bg-blue-950 font-sans text-white w-11/12 m-auto",
    headerBlock("Original C header"),
    codeBlock("c", gb.spec.headerCode.value),
    gb.code.map { gc =>
      val scalaCodeBlock =
        div(
          headerBlock("Scala code"),
          codeBlock("scala", gc.scalaCode.value)
        )

      gc.glueCode match
        case None => scalaCodeBlock

        case Some(glue) =>
          div(
            cls := "flex items-start",
            scalaCodeBlock.amend(
              cls := "grow-0 w-6/12"
            ),
            div(
              cls := "grow-0 w-6/12",
              headerBlock("Glue C code"),
              codeBlock("c", glue.value)
            )
          )
      end match

    }
  )

def renderBindingPage(userPageSignal: Signal[Page.BindingPage])(using
    Api,
    Router[Page]
): Div =
  div(
    cls := "w-full",
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
