$version: "2"
namespace bindgen.web.internal.jobs

use alloy#simpleRestJson
use bindgen.web.domain#Status
use bindgen.web.domain#JobId
use bindgen.web.domain#BindingSpec
use bindgen.web.domain#GeneratedBinding
use bindgen.web.domain#BindingNotReady
use bindgen.web.domain#BindingNotFound
use bindgen.web.domain#BindingCodeNotFound
use bindgen.web.domain#ValidationError
use bindgen.web.domain#SubmissionFailed

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
  }

  output := {
    @required
    id: JobId
  }

  errors: [ValidationError, SubmissionFailed]
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


