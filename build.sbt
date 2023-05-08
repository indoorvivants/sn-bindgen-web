import scala.scalanative.build.Mode

val V = new {
  val scala = "3.2.2"

  val snunit = "0.5.2"

  val http4s = "0.23.18"
}

lazy val `http-server` =
  project
    .in(file("modules/http-server"))
    .enablePlugins(ScalaNativePlugin)
    .enablePlugins(Smithy4sCodegenPlugin)
    .settings(
      scalaVersion := V.scala,
      libraryDependencies += "com.disneystreaming.smithy4s" %%% "smithy4s-http4s" % smithy4sVersion.value,
      libraryDependencies += "com.github.lolgab" %%% "snunit-http4s0.23" % V.snunit,
      libraryDependencies += "org.http4s" %%% "http4s-dsl" % V.http4s,
      nativeConfig ~= { _.withIncrementalCompilation(true) }
    )

def unitConfig(binaryPath: File, staticPath: File) =
  s"""
{
  "listeners": {
    "*:9999": {
      "pass": "routes"
    }
  },
  "routes": [
    {
      "match": {
        "uri": "/api/*"
      },
      "action": {
        "pass": "applications/bindgen-web"
      }
    },
    {
      "match": {
        "uri": "~^((/(.*)\\\\.(js|css|html))|/)$$"
      },
      "action": {
        "share": "${staticPath}$$uri"
      }
    },
    {
      "action": {
        "share": "${staticPath / "index.html"}"
      }
    }
  ],
  "applications": {
    "bindgen-web": {
      "processes": {
        "max": 10,
        "spare": 2,
        "idle_timeout": 180
      },
      "type": "external",
      "executable": "$binaryPath",
      ${sys.env
      .get("CI")
      .map { _ =>
        """
        "user": "runner",
        "group": "docker",
        """
      }
      .getOrElse("")}
      "environment": {
        "JWT_SECRET": "secret"
      },
      "limits": {
        "timeout": 1,
        "requests": 1000
      }
    }
  }
}

"""

import scala.sys.process

val buildApp = taskKey[Unit]("")
buildApp := {
  buildHttpServer.value
  buildFrontend.value
}

lazy val buildFrontend = taskKey[Unit]("")
buildFrontend := {
  // val js = frontendFile.value
  val buildPath = ((ThisBuild / baseDirectory).value / "build")
  val staticPath =
    new File(
      sys.env.getOrElse("BINDGEN_WEB_STATIC_PATH", buildPath.toString)
    )

  val destination = buildPath / "static"

  IO.write(
    destination / "index.html",
    """
      <!DOCTYPE html>
      <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <meta http-equiv="X-UA-Compatible" content="ie=edge">
          <title>Scala 3 Native bindings generator</title>
        </head>
        <body>
        <div id="appContainer"></div>
        <script src="/frontend.js"></script>
        </body>
      </html>
    """.stripMargin
  )

  // IO.copyFile(js, destination / "frontend.js")
}

val buildHttpServer = taskKey[Unit]("")
buildHttpServer := {
  val target = (`http-server` / Compile / nativeLink).value

  val destination = (ThisBuild / baseDirectory).value / "build" / "bindgen-web"

  IO.delete(destination)

  IO.copyFile(
    target,
    destination,
    preserveExecutable = true,
    preserveLastModified = true
  )

  // process.Process(s"chmod 0777 ${destination}").!!

  sys.env.get("CI").foreach { _ =>
    val sudo = if (sys.env.contains("USE_SUDO")) "sudo " else ""
  }
}

lazy val deployLocally = taskKey[Unit]("")
deployLocally := {
  locally { buildApp.value }
  locally { updateUnitConfiguration.value }
}

lazy val updateUnitConfiguration = taskKey[Unit]("")

updateUnitConfiguration := {
  locally(buildApp.value)
  val configJson = writeConfig.value.toString

  def run(command: Seq[String]) = {
    val sb = new StringBuilder
    process.Process(command).!(process.ProcessLogger(sb ++= _))
    sb.toString
  }

  val sudo = sys.env.get("USE_SUDO").map(_ => "sudo").toSeq

  val create_result = run(sudo ++ Seq("unitc", "PUT", configJson, "/config"))
  assert(
    create_result.contains("Reconfiguration done"),
    s"Unit reconfiguration didn't succeed, returning `$create_result`"
  )
  val reload_result = run(
    sudo ++ Seq("unitc", "GET", "/control/applications/bindgen-web/restart")
  )
  assert(
    reload_result.contains("success"),
    s"Unit reload didn't succeed, returning `$reload_result`"
  )
}

lazy val writeConfig = taskKey[File]("")
writeConfig := {
  val buildPath = ((ThisBuild / baseDirectory).value / "build")
  val binariesPath =
    new File(
      sys.env.getOrElse(
        "BINDGEN_WEB_HTTP_APP_PATH",
        (buildPath / "bindgen-web").toString
      )
    )
  val staticPath =
    new File(
      sys.env.getOrElse("BINDGEN_WEB_STATIC_PATH", buildPath.toString)
    )

  val configPath =
    new File(
      sys.env.getOrElse(
        "BINDGEN_WEB_CONFIG_PATH",
        (buildPath / "config.json").toString
      )
    )

  IO.write(configPath, unitConfig(binariesPath, staticPath))

  configPath
}

Global / onChangedBuildSource := ReloadOnSourceChanges
