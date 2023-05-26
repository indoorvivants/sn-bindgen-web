package bindgen.web.internal.jobs

object Log:
  private lazy val io =
    scribe.Logger.root
      .clearHandlers()
      .withHandler(
        writer = scribe.writer.SystemErrWriter,
        outputFormat = scribe.output.format.ANSIOutputFormat
      )
      .replace()

    scribe.cats.io
  end io

  export io.{debug, info, warn, error}
end Log
