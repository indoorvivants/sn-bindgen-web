package bindgen.web

import api.*

import cats.effect.*
import org.http4s.*
import org.http4s.dsl.io.*
import smithy4s.http4s.SimpleRestJsonBuilder

object app extends snunit.Http4sApp:
  def routes =
    SimpleRestJsonBuilder.routes(BindgenServiceImpl).resource.map(_.orNotFound)
end app

object BindgenServiceImpl extends BindgenService[IO]:
  override def getStatus(id: String): IO[GetStatusOutput] = IO(
    GetStatusOutput(api.Status.PROCESSING)
  )
  override def submit(headerCode: String) = ???
