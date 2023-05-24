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
    name: PackageName

    clangFlags: ClangFlags
}

structure GeneratedBinding {
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

union Status {
  processing: Processing
  failed: Failed
  completed: Completed
}

structure Completed{}

structure Processing {
  remaining: Integer
}

structure Failed {
  message: String
}


