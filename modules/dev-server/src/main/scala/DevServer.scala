import java.io.File

@main def hello(mode: String) =
  import sys.process.*

  val modes =
    Map("server" -> "HTTP_SERVER_BINARY", "worker" -> "WORKER_BINARY")

  def getCMD(mode: String) =
    val env = modes.getOrElse(
      mode,
      sys.error(
        s"Unknown mode [$mode], choose one of: ${modes.keySet.toList.sorted.mkString(",")}"
      )
    )

    sys.env.getOrElse(
      env,
      sys.error(s"$env is missing from env")
    )
  end getCMD

  val cmds = mode match
    case "all" =>
      modes.keySet.map(getCMD)
    case other => Set(getCMD(other))

  val bgProcs =
    cmds.map: cmd =>
      println(s"Running [$cmd]")
      Process(cmd).run()

  sys.addShutdownHook {
    println("Caught shutdown, killing process")
    bgProcs.map(_.destroy)
  }
  val ev = bgProcs.map(_.exitValue)
  println(s"Process(es) finished naturally with code $ev")
end hello
