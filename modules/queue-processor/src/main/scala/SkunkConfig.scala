package bindgen.web.internal.jobs

case class SkunkConfig(
    maxSessions: Int,
    strategy: skunk.Strategy,
    debug: Boolean
)

object SkunkConfig
    extends SkunkConfig(
      maxSessions = 2,
      strategy = skunk.Strategy.SearchPath,
      debug = false
    )
