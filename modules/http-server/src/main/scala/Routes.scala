package bindgen.web

import api.*

import cats.effect.*
import org.http4s.*
import org.http4s.dsl.io.*
import smithy4s.http4s.SimpleRestJsonBuilder
import bindgen.web.domain.GeneratedBinding
import bindgen.web.domain.JobId
import bindgen.web.domain.HeaderCode
import bindgen.web.domain.PackageName
import cats.effect.std.UUIDGen

object app extends snunit.Http4sApp:
  scribe.Logger.root
    .clearHandlers()
    .withHandler(
      writer = scribe.writer.SystemErrWriter,
      outputFormat = scribe.output.format.ANSIOutputFormat
    )
    .withMinimumLevel(scribe.Level.Debug)
    .replace()

  def routes =
    SimpleRestJsonBuilder.routes(BindgenServiceImpl).resource.map(_.orNotFound)
end app

object BindgenServiceImpl extends BindgenService[IO]:
  override def getBinding(id: JobId): IO[GeneratedBinding] = ???
  override def getStatus(id: JobId): IO[GetStatusOutput]   = ???
  override def submit(
      headerCode: HeaderCode,
      name: PackageName,
      clangFlags: Option[List[String]]
  ): IO[SubmitOutput] =
    UUIDGen.randomUUID[IO].map(uuid => SubmitOutput(JobId(uuid)))
end BindgenServiceImpl
