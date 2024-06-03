package bindgen.web.internal.jobs

import bindgen.*
import bindgen.web.domain.*

import scala.scalanative.unsafe.Zone

enum BindgenResult:
  case Error(msg: String)
  case Failure(diagnostics: List[ResolvedClangDiagnostic])
  case Success(code: GeneratedCode)

object BindgenResult:
  def adapt(error: BindingError)(using Zone): BindgenResult =
    error match
      case bindgen.BindingError.ClangErrors(diagnostics) =>
        BindgenResult.Failure(diagnostics.map(ResolvedClangDiagnostic.from))
      case BindingError.FailedToDetectSystemHeaders(msg) =>
        BindgenResult.Error(msg)
      case other => BindgenResult.Error(other.render)
end BindgenResult
