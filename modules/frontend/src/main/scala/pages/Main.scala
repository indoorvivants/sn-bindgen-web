package bindgen.web.frontend

import com.raquo.laminar.api.L.*
import bindgen.web.domain.*
import com.raquo.waypoint.Router
import scalajs.js
import org.scalajs.dom
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*
import bindgen.web.domain.BindingStatus.*

import scala.scalajs.js.Thenable.Implicits.*
import com.raquo.airstream.web.WebStorageVar

enum State:
  case None
  case Fatal(msg: String)
  case Fixable(msg: String)
  case Polling(id: JobId, st: Option[BindingStatus])

def renderMainPage(
    recentBindings: RecentBindingsManager
)(using Api, Router[Page]) =
  val sampleCode =
    """
    |typedef struct {
    |  int a
    | } hello;
    |
    | void test_me(hello a, int b);
    |""".stripMargin.trim

  val samplePackage = "my_bindings"

  val headerCodeVar = WebStorageVar
    .localStorage(
      key = "bindgen-web-header-code",
      syncOwner = Some(unsafeWindowOwner)
    )
    .text(sampleCode)

  val packageNameVar = WebStorageVar
    .localStorage(
      key = "bindgen-web-package-name",
      syncOwner = Some(unsafeWindowOwner)
    )
    .text(samplePackage)

  val pollingState = Var(State.None)

  val readyBindingState = Var(Option.empty[JobId])

  val updater = EventStream
    .periodic(intervalMs = 300)
    .withCurrentValueOf(pollingState.signal)
    .map(_._2)
    .collect {

      case State.Polling(id, None)                    => id
      case State.Polling(id, Some(ProcessingCase(_))) => id
    }
    .flatMapSwitch { id =>
      api
        .stream(
          _.users
            .getStatus(id)
        )
        .map(_.status)
        .map(status => State.Polling(id, Some(status)))

    } --> pollingState.writer

  div(
    updater,
    cls := "container",
    p(
      cls := "recent-bindings-title",
      "Recent bindings",
      display <-- recentBindings.signal.map(v =>
        if v.isEmpty then "none" else "block"
      )
    ),
    ul(
      cls := "recent-bindings",
      children <-- recentBindings.signal.map(
        _.map(s =>
          li(
            a(
              href := "#",
              navigateTo(Page.BindingPage(JobId(s._2))),
              s._1,
              cls := "recent-binding-link"
            )
          )
        )
      )
    ),
    div(
      cls := "side-by-side",
      form(
        cls := "binding-form",
        span(
          "Bindgen version: ",
          child <-- api
            .stream(_.users.serverInfo())
            .map(_.bindgenVersion)
            .map: version =>
              a(
                href := s"https://github.com/indoorvivants/sn-bindgen/releases/tag/v$version",
                b(
                  version
                )
              )
        ),
        onSubmit.preventDefault.mapTo(
          BindingSpec(
            HeaderCode(headerCodeVar.now()),
            PackageName(packageNameVar.now())
          )
        ) --> { code =>
          api.futureAttempt(_.users.submit(code)).foreach {
            case Right(value) =>
              pollingState.set(State.Polling(value.id, None))
            case Left(fatal: SubmissionFailed) =>
              pollingState
                .set(State.Fatal(fatal.reason.getOrElse("Server error 😿")))
            case Left(err: ValidationError) =>
              pollingState.set(State.Fixable(err.message))
            case Left(other) =>
              pollingState.set(
                State
                  .Fatal(
                    "❌ Something is seriously broken:" + other.getMessage()
                  )
              )

          }
        },
        child <-- pollingState.signal
          .debugSpy(org.scalajs.dom.console.log(_))
          .map {
            case State.Fatal(msg) =>
              message(MsgType.Error, msg)
            case State.Fixable(msg) =>
              message(MsgType.Warn, msg)
            case State.Polling(_, None) =>
              message(MsgType.Info, "Submitted, waiting to hear back...")
            case State.Polling(_, Some(ProcessingCase(p: Processing))) =>
              p.remaining match
                case None =>
                  message(
                    MsgType.Info,
                    "Binding is in the queue, please hold.."
                  )
                case Some(value) =>
                  message(
                    MsgType.Info,
                    s"Binding is in the queue ($value ahead of it), please hold.."
                  )
            case State.Polling(id, Some(CompletedCase(_: Completed))) =>
              readyBindingState.set(Some(id))
              message(
                MsgType.Info,
                span(
                  "Your binding is ready. ",
                  a(
                    href := "#",
                    cls  := "font-bold",
                    "Permalink",
                    navigateTo(Page.BindingPage(id))
                  )
                )
              )
            case State.Polling(_, Some(FailedCase(f: Failed))) =>
              message(MsgType.Error, renderFailed(f))

            case State.Polling(_, Some(NotFoundCase(_))) =>
              message(MsgType.Error, "Binding doesn't exist")

            case State.None => emptyNode
          },
        titleBlock("Package name"),
        input(
          cls := "w-full m-4 p-4 text-xl",
          tpe := "text",
          onInput.mapToValue --> packageNameVar,
          value <-- packageNameVar.signal,
          placeholder := "my_bindings"
        ),
        titleBlock("C header code"),
        div(
          cls := "m-4 w-full items-center",
          textArea(
            cls := "w-full",
            onMountCallback: el =>
              val editor = CodeMirror
                .fromTextArea(
                  el.thisNode.ref,
                  js.Dictionary(
                    "value"       -> headerCodeVar.now(),
                    "lineNumbers" -> true,
                    "mode"        -> "text/x-csrc"
                  )
                )
              editor
                .on(
                  "change",
                  value => headerCodeVar.toObserver.onNext(value.getValue())
                )
              editor
                .getDoc()
                .setValue(headerCodeVar.now())
            ,
            onInput.mapToValue --> headerCodeVar,
            value <-- headerCodeVar.signal
          )
        ),
        button(
          tpe := "submit",
          "Generate",
          cls := "submit-button"
        )
      ),
      div(
        cls := "w-1/2 items-center",
        renderBindingId(
          readyBindingState.signal.changes.collectSome,
          false,
          recentBindings
        )
      )
    )
  )
end renderMainPage
