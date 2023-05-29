package bindgen.web.frontend

import com.raquo.laminar.api.L.*
import bindgen.web.domain.*
import com.raquo.waypoint.Router
import scalajs.js
import org.scalajs.dom
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*
import bindgen.web.domain.Status.*

enum State:
  case None
  case Fatal(msg: String)
  case Fixable(msg: String)
  case Polling(id: JobId, st: Option[Status])

def renderMainPage(using Api, Router[Page]) =
  val sampleCode =
    """
    |typedef struct {
    |  int a
    | } hello;
    |
    | void test_me(hello a, int b);
    |""".stripMargin.trim

  val state = Var(BindingSpec(HeaderCode(sampleCode), PackageName("")))
  val error = Var(State.None)

  val updater = EventStream
    .periodic(intervalMs = 1000)
    .withCurrentValueOf(error.signal)
    .map(_._2)
    .collect {

      case State.Polling(id, None)                           => id
      case State.Polling(id, Some(Status.ProcessingCase(_))) => id
    }
    .flatMap { id =>
      api.stream(
        _.users
          .getStatus(id)
          .map(_.status)
          .map(status => State.Polling(id, Some(status)))
      )
    } --> error.writer

  div(
    updater,
    cls := "w-10/12 m-auto",
    pageTitle,
    form(
      cls := "w-full items-center",
      onSubmit.preventDefault.mapTo(state.now()) --> { code =>
        api.future(_.users.submit(code).attempt).foreach {
          case Right(value) =>
            error.set(State.Polling(value.id, None))
          // redirectTo(Page.BindingPage(value.id))
          case Left(fatal: SubmissionFailed) =>
            error.set(State.Fatal(fatal.reason.getOrElse("Server error 😿")))
          case Left(err: ValidationError) =>
            error.set(State.Fixable(err.message))
          case Left(other) =>
            error.set(
              State
                .Fatal("❌ Something is seriously broken:" + other.getMessage())
            )

        }
      },
      child <-- error.signal.map {
        case State.Fatal(msg) =>
          message(MsgType.Error, msg)
        case State.Fixable(msg) =>
          message(MsgType.Warn, msg)
        case State.Polling(_, None) =>
          message(MsgType.Info, "Submitted, waiting to hear back...")
        case State.Polling(_, Some(ProcessingCase(p: Processing))) =>
          p.remaining match
            case None =>
              message(MsgType.Info, "Binding is in the queue, please hold..")
            case Some(value) =>
              message(
                MsgType.Info,
                s"Binding is in the queue ($value ahead of it), please hold.."
              )
        case State.Polling(id, Some(CompletedCase(_: Completed))) =>
          message(
            MsgType.Info,
            span(
              "Your binding is ready, please click ",
              a(
                href := "#",
                cls  := "font-bold",
                "here",
                navigateTo(Page.BindingPage(id))
              )
            )
          )
        case other => emptyNode
      },
      titleBlock("Package name"),
      input(
        cls := "w-full m-4 p-4 text-xl",
        tpe := "text",
        onInput.mapToValue --> state.updater[String]((spec, newv) =>
          spec.copy(packageName = PackageName(newv))
        ),
        placeholder := "my_bindings"
      ),
      titleBlock("C header code"),
      div(
        cls := "m-4 w-full items-center",
        textArea(
          cls := "w-full",
          onInput.mapToValue --> state.updater[String]((spec, newv) =>
            spec.copy(headerCode = HeaderCode(newv))
          ),
          onMountCallback(el =>
            CodeMirror
              .fromTextArea(
                el.thisNode.ref,
                js.Dictionary(
                  "value"       -> state.now().headerCode.value,
                  "lineNumbers" -> true,
                  "mode"        -> "text/x-csrc"
                )
              )
          ),
          value <-- state.signal.map(_.headerCode.value)
        )
      ),
      button(
        tpe := "submit",
        "Generate",
        cls := "text-2xl m-auto rounded-lg border-2 border-white p-4 text-white"
      )
    )
  )
end renderMainPage
