package bindgen.web.internal.jobs

import scala.util.*

class FlyIOLoader(env: Map[String, String]):
  def loadPgCredentials: Option[PgCredentials] =
    env.get("DATABASE_URL").flatMap { url =>
      Try {

        val parsed = new java.net.URI(url)

        val host     = parsed.getHost()
        val port     = parsed.getPort()
        val userInfo = parsed.getUserInfo()
        val dbName   = parsed.getPath().tail // dropping the first slash

        val userName = userInfo.split(":").apply(0)
        val password = userInfo.split(":").apply(1)

        PgCredentials(
          host = host,
          port = port,
          user = userName,
          password = Some(password),
          database = dbName,
          ssl = env.contains("FLY_PG_SSL")
        )
      } match
        case Success(s) => Some(s)
        case Failure(f) =>
          scribe.error("Failed to read from DATABASE_URL", f)
          None

    }
end FlyIOLoader
