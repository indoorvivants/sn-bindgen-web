package bindgen.web.internal.jobs

object Log:
  private lazy val io =
    scribe.Logger.root
      .clearHandlers()
      .withHandler(writer = scribe.writer.SystemErrWriter)
      .replace()

    scribe.cats.io

  export io.{debug, info, warn, error}
end Log
