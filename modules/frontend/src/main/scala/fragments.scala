package bindgen.web.frontend

import com.raquo.laminar.api.L.*

inline def codeBlock(language: String, value: String) =
  pre(
    code(
      cls := s"language-$language m-6 rounded-lg border-fuchsia-50 border-2 overflow-auto",
      onMountCallback(mnt => hljs.highlightElement(mnt.thisNode.ref)),
      value
    )
  )

inline def headerBlock(title: String) =
  h2(title, cls := "text-xl font-bold m-6 text-white")

inline def titleBlock(title: String) =
  h3(title, cls := "text-lg font-bold m-6 text-white")

enum MsgType(val color: String):
  case Info
      extends MsgType(
        "bg-green-300 border-green-700 border-2 p-4 text-green-700 m-4"
      )
  case Error
      extends MsgType("bg-red-300 border-red-700 border-2 p-4 text-red-700 m-4")
  case Warn
      extends MsgType(
        "bg-yellow-300 border-yellow-700 border-2 p-4 text-yellow-700 m-4"
      )
end MsgType

inline def message(tpe: MsgType, value: Element) =
  div(
    cls := tpe.color,
    value
  )

inline def message(tpe: MsgType, value: String) =
  div(
    cls := tpe.color,
    value
  )

inline def pageTitle = div(
  "Generate Scala 3 Native bindings to C code",
  cls := "text-6xl drop-shadow-2xl text-white m-4 font-bold"
)
