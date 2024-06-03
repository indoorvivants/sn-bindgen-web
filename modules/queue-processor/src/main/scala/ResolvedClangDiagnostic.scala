package bindgen.web.internal.jobs

import bindgen.*
import cats.syntax.all.*

import scala.scalanative.unsafe.Zone

case class ResolvedClangDiagnostic(severity: String, msg: String) derives io.circe.Codec.AsObject

object ResolvedClangDiagnostic:
  def from(cd: ClangDiagnostic)(using Zone) =
    ResolvedClangDiagnostic(severity = cd.severity.toString, msg = cd.message())
