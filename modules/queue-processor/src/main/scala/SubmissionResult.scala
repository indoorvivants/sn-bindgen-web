package bindgen.web.internal.jobs

import bindgen.web.domain.*
enum SubmissionResult:
  case Ok(id: JobId)
  case Failed(msg: String)
