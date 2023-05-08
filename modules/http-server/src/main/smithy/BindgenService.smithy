$version: "2"
namespace bindgen.web.api

use alloy#simpleRestJson

@simpleRestJson
service BindgenService {
  version: "1.0.0",
  operations: [Submit, GetStatus]
}

@http(method: "POST", uri: "/api/submit", code: 200)
operation Submit {
  input := {
    @required
    headerCode: String
  },
  output := {
    @required
    id: String
  }
}

@readonly
@http(method: "GET", uri: "/api/status/{id}", code: 200)
operation GetStatus {
  input := {
    @required
    @httpLabel
    id: String
  },
  output := {
    @required
    status: Status
  }
}

enum Status {
    PROCESSING
    FAILED
    SUCCESS
}
