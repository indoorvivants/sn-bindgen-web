import java.io.File

@main def hello =
  val unitdCommand = sys.env.getOrElse(
    "UNITD_COMMAND",
    sys.error("UNITD_COMMAND is missing from env")
  )
  val cwd = sys.env.getOrElse(
    "SERVER_CWD",
    sys.error("SERVER_CWD is missing from env")
  )

  import sys.process.*

  println(s"Running [$unitdCommand] in cwd=[$cwd]")

  val bgProc = Process(unitdCommand, cwd = new File(cwd)).run()

  sys.addShutdownHook {
    println("Caught shutdown, killing process")
    bgProc.destroy
  }
  val ev = bgProc.exitValue
  println(s"Process finished naturally with code $ev")
end hello
