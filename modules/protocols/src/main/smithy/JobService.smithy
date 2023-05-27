$version: "2"
namespace bindgen.web.internal.jobs

use alloy#simpleRestJson
use bindgen.web.domain#JobId
use bindgen.web.domain#BindingSpec
use bindgen.web.domain#GeneratedBinding
use bindgen.web.domain#BindingNotReady
use bindgen.web.domain#BindingNotFound
use bindgen.web.domain#BindingCodeNotFound

@simpleRestJson
service JobService {
  version: "1.0.0",
  operations: [Submit, GetStatus, GetBinding]
}

@readonly
@http(method: "GET", uri: "/binding/{id}", code: 200)
operation GetBinding {
  input := {
    @required
    @httpLabel
    id: JobId
  }

  output: GeneratedBinding 
  errors: [BindingNotReady, BindingNotFound, BindingCodeNotFound]
}



@http(method: "POST", uri: "/submit", code: 200)
operation Submit {
  input := {
    @required
    spec: BindingSpec
  },
  output := {
    @required
    id: JobId
  }
}

@readonly
@http(method: "GET", uri: "/status/{id}", code: 200)
operation GetStatus {
  input := {
    @required
    @httpLabel
    id: JobId
  },
  output := {
    @required
    status: Status
  }
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

