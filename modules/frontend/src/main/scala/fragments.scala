package bindgen.web.frontend

import com.raquo.laminar.api.L.*

inline def codeBlock(language: String, value: String) =
  pre(
    code(
      cls := s"language-$language",
      cls := "static-code-block",
      onMountCallback(mnt => hljs.highlightElement(mnt.thisNode.ref)),
      value
    )
  )

def headerBlock(title: String) =
  h2(title, cls := "section-header")

def titleBlock(title: String) =
  h3(title, cls := "subtitle")

enum MsgType(val color: String):
  case Info
      extends MsgType(
        "info-message"
      )
  case Error extends MsgType("error-message")
  case Warn
      extends MsgType(
        "warn-message"
      )
end MsgType

def message(tpe: MsgType, value: Element) =
  div(
    cls := tpe.color,
    value
  )

def message(tpe: MsgType, value: String) =
  div(
    cls := tpe.color,
    value
  )

def pageTitle = h1(
  "Generate Scala 3 Native bindings to C code",
  cls := "page-title"
)

def headerExplanationBlock(value: String) =
  p(cls := "header-explanation-block", value)
