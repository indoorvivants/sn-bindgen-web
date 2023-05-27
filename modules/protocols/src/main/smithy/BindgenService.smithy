$version: "2"
namespace bindgen.web.api

use alloy#simpleRestJson
use alloy#uuidFormat
use bindgen.web.domain#JobId
use bindgen.web.domain#BindingSpec
use bindgen.web.domain#GeneratedBinding
use bindgen.web.domain#BindingNotReady
use bindgen.web.domain#BindingNotFound
use bindgen.web.domain#BindingCodeNotFound
use bindgen.web.domain#Status

@simpleRestJson
service BindgenService {
  version: "1.0.0",
  operations: [Submit, GetStatus, GetBinding]
}

@http(method: "POST", uri: "/api/submit", code: 200)
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
@http(method: "GET", uri: "/api/status/{id}", code: 200)
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

@readonly
@http(method: "GET", uri: "/api/binding/{id}", code: 200)
operation GetBinding {
  input := {
    @required
    @httpLabel
    id: JobId
  }

  output: GeneratedBinding 
  errors: [BindingNotReady, BindingNotFound, BindingCodeNotFound]
}


