$version: "2"

namespace bindgen.web.domain

use alloy#simpleRestJson
use alloy#uuidFormat

@uuidFormat
string JobId

structure BindingSpec {
    @required
    headerCode: HeaderCode

    @required
    packageName: PackageName

    clangFlags: ClangFlags
}

structure GeneratedBinding {
  @required
  spec: BindingSpec

  code: GeneratedCode

  errors: ClangErrors
}

structure GeneratedCode {
  @required 
  scalaCode: ScalaCode

  glueCode: GlueCode

}

string PackageName
string HeaderCode
string GlueCode
string ScalaCode

list ClangFlags {
  member: String
}

list ClangErrors {
  member: ClangError
}

structure ClangError {
  @required
  severity: String

  @required
  message: String
}

union Status {
  processing: Processing
  failed: Failed
  completed: Completed
  notFound: NotFound
}

structure Completed{}
structure NotFound{}

structure Processing {
  remaining: Integer
}

structure Failed {
  message: String
}

@error("server")
@httpError(500)
structure SubmissionFailed {
  reason: String
}

@error("client")
@httpError(400)
structure ValidationError {
  @required 
  message: String
}

@error("client")
@httpError(404)
structure BindingNotFound {}

@error("client")
@httpError(404)
structure BindingNotReady {}

@error("server")
@httpError(500)
structure BindingCodeNotFound {}
