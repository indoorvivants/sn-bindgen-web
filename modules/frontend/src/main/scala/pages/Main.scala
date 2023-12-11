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

  val bindingSpecState = Var(
    BindingSpec(HeaderCode(sampleCode), PackageName(""))
  )
  val pollingState = Var(State.None)

  var readyBindingState = Var(Option.empty[JobId])

  val updater = EventStream
    .periodic(intervalMs = 300)
    .withCurrentValueOf(pollingState.signal)
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
    } --> pollingState.writer

  val codeUpdater =
    bindingSpecState.updater[String]((spec, newv) =>
      spec.copy(headerCode = HeaderCode(newv))
    )

  div(
    updater,
    cls := "w-10/12 m-auto",
    pageTitle,
    div(
      cls := "w-full items-center flex align-top",
      form(
        cls := "w-1/2 items-center",
        onSubmit.preventDefault.mapTo(bindingSpecState.now()) --> { code =>
          api.future(_.users.submit(code).attempt).foreach {
            case Right(value) =>
              pollingState.set(State.Polling(value.id, None))
            // redirectTo(Page.BindingPage(value.id))
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
        child <-- pollingState.signal.debugSpy(org.scalajs.dom.console.log(_)).map {
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
            val msg = f.message match
              case None        => "well, something! is broken"
              case Some(value) => s"reason: $value"

            message(MsgType.Error, s"Processing failed: $msg")

          case State.Polling(_, Some(NotFoundCase(_))) =>
            message(MsgType.Error, "Binding doesn't exist")

          case State.None => emptyNode
        },
        titleBlock("Package name"),
        input(
          cls := "w-full m-4 p-4 text-xl",
          tpe := "text",
          onInput.mapToValue --> bindingSpecState.updater[String](
            (spec, newv) => spec.copy(packageName = PackageName(newv))
          ),
          placeholder := "my_bindings"
        ),
        titleBlock("C header code"),
        div(
          cls := "m-4 w-full items-center",
          textArea(
            cls := "w-full",
            onMountCallback(el =>
              CodeMirror
                .fromTextArea(
                  el.thisNode.ref,
                  js.Dictionary(
                    "value"       -> bindingSpecState.now().headerCode.value,
                    "lineNumbers" -> true,
                    "mode"        -> "text/x-csrc"
                  )
                )
                .on("change", value => codeUpdater.onNext(value.getValue()))
            ),
            value <-- bindingSpecState.signal.map(_.headerCode.value)
          )
        ),
        button(
          tpe := "submit",
          "Generate",
          cls := "text-2xl m-auto rounded-lg border-2 border-white p-4 text-white"
        )
      ),
      div(
        cls := "w-1/2 items-center",
        renderBindingId(readyBindingState.signal.changes.collectSome, false)
      )
    )
  )
end renderMainPage
