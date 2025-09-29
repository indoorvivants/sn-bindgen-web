package bindgen.web.frontend

import bindgen.web.domain.*
import bindgen.web.domain.BindingStatus.*
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*

private def renderBinding(gb: GeneratedBinding, showSource: Boolean)(using
    Router[Page]
) =
  div(
    Option.when(showSource)(
      div(
        cls := "link-bar",
        a(
          href := "#",
          cls  := "link-on-background",
          "⬅️ Back to main page",
          navigateTo(Page.Main)
        ),
        a(
          href := "#",
          cls  := "link-on-background",
          "📋 Copy sharing link",
          onClick.preventDefault --> { _ =>
            org.scalajs.dom.window.navigator.clipboard
              .writeText(org.scalajs.dom.window.location.href)
          }
        )
      )
    ),
    Option.when(showSource)(
      div(
        cls := "code-block-header",
        a(
          cls := "copy-button",
          "📋",
          href := "#",
          onClick.preventDefault --> { _ =>
            org.scalajs.dom.window.navigator.clipboard
              .writeText(gb.spec.headerCode.value)
          }
        ),
        headerBlock("Original C header")
      )
    ),
    Option.when(showSource)(codeBlock("c", gb.spec.headerCode.value)),
    gb.code.map { gc =>
      val scalaCodeBlock =
        div(
          div(
            cls := "code-block-header",
            a(
              cls := "copy-button",
              "📋",
              href := "#",
              onClick.preventDefault --> { _ =>
                org.scalajs.dom.window.navigator.clipboard
                  .writeText(gc.scalaCode.value)
              }
            ),
            headerBlock(
              "Scala code"
            )
          ),
          Option.when(gc.glueCode.isDefined)(
            div(
              cls := "warn-message",
              "Won't work without glue code below"
            )
          ),
          headerExplanationBlock("This code has no runtime dependencies"),
          codeBlock("scala", gc.scalaCode.value.trim)
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
              div(
                cls := "code-block-header",
                a(
                  cls := "copy-button",
                  "📋",
                  href := "#",
                  onClick.preventDefault --> { _ =>
                    org.scalajs.dom.window.navigator.clipboard
                      .writeText(glue.value)
                  }
                ),
                headerBlock("Glue C code")
              ),
              headerExplanationBlock(
                "In SBT, put this into src/main/resources/scala-native/ folder"
              ),
              headerExplanationBlock(
                "In Scala CLI, put this into resources/scala-native/ folder"
              ),
              codeBlock("c", glue.value.trim())
            )
          )
      end match

    }
  )

def renderFailed(failed: Failed) =
  val diags =
    Option.when(failed.diagnostics.getOrElse(Nil).nonEmpty):
      ul(
        failed.diagnostics
          .getOrElse(Nil)
          .map: diag =>
            li(b(diag.severity), ": ", diag.message)
      )
  div(b(s"Failed: ${failed.message}"), diags)
end renderFailed

def renderBindingId(
    idSignal: Observable[JobId],
    showSource: Boolean,
    recentBindings: RecentBindingsManager
)(using
    Api
)(using Router[Page]) =
  div(
    cls := "w-full",
    child <-- idSignal.flatMapSwitch { id =>
      api
        .signal(_.users.getStatus(id).`then`(_.status))
        // .composeChanges(_.throttle(500))
        .flatMapSwitch {
          case None =>
            Signal.fromValue(message(MsgType.Info, "Please wait.."))
          case Some(value) =>
            value match
              case NotFoundCase(_) =>
                Signal.fromValue(message(MsgType.Error, "Binding not found"))

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
                Signal.fromValue(renderFailed(failed))
              case CompletedCase(completed) =>
                api.signal(_.users.getBinding(id)).map {
                  case None => i("gimme a minute")
                  case Some(value) =>
                    val singleLineCCode =
                      value.spec.headerCode.value.take(150).replace("\n", "↵")

                    recentBindings.add(singleLineCCode, id.value)
                    renderBinding(value, showSource = showSource)
                }

        }

    }
  )
end renderBindingId

def renderBindingPage(
    userPageSignal: Signal[Page.BindingPage],
    recentBindings: RecentBindingsManager
)(using
    Api,
    Router[Page]
): Div =
  div(
    cls := "container",
    renderBindingId(
      userPageSignal.map(_.id),
      showSource = true,
      recentBindings = recentBindings
    )
  )
