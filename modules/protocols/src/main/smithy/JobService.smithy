$version: "2"
namespace bindgen.web.internal.jobs

use alloy#simpleRestJson
use bindgen.web.domain#JobId
use bindgen.web.domain#BindingSpec

@simpleRestJson
service JobService {
  version: "1.0.0",
  operations: [Submit, GetStatus]
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

