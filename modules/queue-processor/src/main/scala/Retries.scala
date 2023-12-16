package bindgen.web.internal.jobs

import concurrent.duration.*
import cats.effect.*

case class RetryPolicy(max: Int, time: FiniteDuration)

extension [A](ioa: IO[A])
  def retry(label: String)(using rp: RetryPolicy) =
    def go(rem: Int): IO[A] =
      rem match
        case 0 => ioa
        case n =>
          ioa.handleErrorWith { err =>
            Log
              .error(s"Retrying `$label`, $n more attempts remaining", err) *>
              IO.sleep(rp.time) *>
              go(n - 1)
          }

    go(rp.max)
