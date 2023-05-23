import scala.scalanative.build.Mode

val V = new {
  val Scala = "3.2.2"

  val snunit = "0.5.2"

  val snCrypto = "0.0.4"

  val http4s = "0.23.18"

  val fs2 = "3.6.1"

  val scribe = "3.11.1"

  val opaqueNewtypes = "0.0.2"

  val porcupine = "0.0.1"
}

val isScala3 = Seq(VirtualAxis.scalaABIVersion(V.Scala))
val isNative = Seq(VirtualAxis.native)

lazy val protocols =
  projectMatrix
    .in(file("modules/protocols"))
    .defaultAxes(isScala3*)
    .nativePlatform(Seq(V.Scala))
    .enablePlugins(Smithy4sCodegenPlugin)
    .settings(
      libraryDependencies += "com.disneystreaming.smithy4s" %%% "smithy4s-http4s" % smithy4sVersion.value
    )

lazy val `http-server` =
  projectMatrix
    .in(file("modules/http-server"))
    .dependsOn(protocols)
    .defaultAxes((isScala3 ++ isNative)*)
    .enablePlugins(ScalaNativePlugin)
    .nativePlatform(Seq(V.Scala))
    .settings(
      libraryDependencies += "com.github.lolgab" %%% "snunit-http4s0.23" % V.snunit,
      libraryDependencies += "com.github.lolgab" %%% "scala-native-crypto" % V.snCrypto,
      libraryDependencies += "org.http4s" %%% "http4s-dsl" % V.http4s,
      libraryDependencies += "com.outr"   %%% "scribe-cats"     % V.scribe,
      nativeConfig ~= { _.withIncrementalCompilation(true) }
    )

lazy val `queue-processor` =
  projectMatrix
    .in(file("modules/queue-processor"))
    .dependsOn(protocols)
    .defaultAxes((isScala3 ++ isNative)*)
    .nativePlatform(Seq(V.Scala))
    .enablePlugins(ScalaNativePlugin, VcpkgNativePlugin, BindgenPlugin)
    .settings(
      libraryDependencies += "com.indoorvivants" %%% "opaque-newtypes" % V.opaqueNewtypes, // SBT
      libraryDependencies += "com.github.lolgab" %%% "snunit-http4s0.23" % V.snunit,
      libraryDependencies += "com.github.lolgab" %%% "scala-native-crypto" % V.snCrypto,
      libraryDependencies += "com.outr"       %%% "scribe-cats"     % V.scribe,
      libraryDependencies += "org.http4s"     %%% "http4s-dsl" % V.http4s,
      libraryDependencies += "com.armanbilge" %%% "porcupine"  % V.porcupine,
      nativeConfig ~= { _.withIncrementalCompilation(true) },
      vcpkgDependencies := VcpkgDependencies
        .ManifestFile((ThisBuild / baseDirectory).value / "vcpkg.json"),
      vcpkgNativeConfig ~= {
        _.withRenamedLibraries(
          Map("zeromq" -> "libzmq", "czmq" -> "libczmq")
        )
      },
      bindgenMode := bindgen.plugin.BindgenMode.Manual(
        scalaDir = sourceDirectory.value / "main" / "scala" / "generated",
        cDir =
          (Compile / resourceDirectory).value / "scala-native" / "generated"
      ),
      bindgenBindings := {
        val configurator = vcpkgConfigurator.value
        import bindgen.interface.*
        Seq(
          Binding
            .builder(configurator.includes("czmq") / "czmq.h", "czmq")
            .withCImports(List("czmq.h"))
            .withClangFlags(
              List(
                "-I" + configurator.includes("czmq").toString,
                "-I" + configurator.includes("zeromq").toString
              )
            )
            .build,
          Binding
            .builder(configurator.includes("zstd") / "zstd.h", "zstd")
            .withCImports(List("zstd.h"))
            .withClangFlags(
              List(
                "-I" + configurator.includes("zstd").toString
              )
            )
            .build,
          Binding
            .builder(configurator.includes("sqlite3") / "sqlite3.h", "sqlite3")
            .withCImports(List("sqlite.h"))
            .withClangFlags(
              List(
                "-I" + configurator.includes("sqlite3").toString,
                "-fsigned-char"
              )
            )
            .build
        )
      }
    )

def unitConfig(
    webPath: File,
    workerPath: File,
    staticPath: File,
    storagePath: File
) =
  s"""
{
  "listeners": {
    "*:9999": {
      "pass": "routes/web"
    },
    "*:8888": {
      "pass": "routes/worker"
    }
  },
  "routes": {
    "worker": [
      {
        "match": {
          "uri": "/*"
        },
        "action": {
          "pass": "applications/worker"
        }
      }
    ],
    "web": [
      {
        "match": {
          "uri": "/api/*"
        },
        "action": {
          "pass": "applications/web"
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
    ]
  },
  "applications": {
    "worker": {
      "processes": {
        "max": 1,
        "spare": 1
      },
      "type": "external",
      "executable": "$workerPath",
      "environment": {
        "DB_PATH": "$storagePath/store.db",
        "FILES_PATH": "$storagePath",
      }
    },
    "web": {
      "processes": {
        "max": 10,
        "idle_timeout": 180
      },
      "type": "external",
      "executable": "$webPath",
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
  locally { buildWeb.value }
  locally { buildWorker.value }
  locally { buildFrontend.value }
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

val buildWorker = taskKey[Unit]("")
buildWorker := {
  val target = (`queue-processor`.native(V.Scala) / Compile / nativeLink).value

  val destination =
    (ThisBuild / baseDirectory).value / "build" / "bindgen-worker"

  IO.delete(destination)

  IO.copyFile(
    target,
    destination,
    preserveExecutable = true,
    preserveLastModified = true
  )

  sys.env.get("CI").foreach { _ =>
    val sudo = if (sys.env.contains("USE_SUDO")) "sudo " else ""
  }
}

val buildWeb = taskKey[Unit]("")
buildWeb := {
  val target = (`http-server`.native(V.Scala) / Compile / nativeLink).value

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

  def reloadApp(name: String) = {
    val web_reload_result = run(
      sudo
        ++ Seq("unitc", "GET", s"/control/applications/$name/restart")
    )
    assert(
      web_reload_result.contains("success"),
      s"$name: Unit reload didn't succeed, returning `$web_reload_result`"
    )
  }

  reloadApp("web")
  reloadApp("worker")
}

lazy val writeConfig = taskKey[File]("")
writeConfig := {
  val buildPath   = ((ThisBuild / baseDirectory).value / "build")
  val defaultStoragePath = ((ThisBuild / baseDirectory).value / "data")
  IO.createDirectory(defaultStoragePath)

  val webPath =
    new File(
      sys.env.getOrElse(
        "BINDGEN_WEB_HTTP_APP_PATH",
        (buildPath / "bindgen-web").toString
      )
    )
  val workerPath =
    new File(
      sys.env.getOrElse(
        "BINDGEN_WEB_WORKER_APP_PATH",
        (buildPath / "bindgen-worker").toString
      )
    )
  val staticPath =
    new File(
      sys.env.getOrElse("BINDGEN_WEB_STATIC_PATH", buildPath.toString)
    )
  val storagePath =
    new File(
      sys.env.getOrElse("BINDGEN_WEB_STORAGE_PATH", defaultStoragePath.toString)
    )

  val configPath =
    new File(
      sys.env.getOrElse(
        "BINDGEN_WEB_CONFIG_PATH",
        (buildPath / "config.json").toString
      )
    )

  IO.write(configPath, unitConfig(webPath, workerPath, staticPath, storagePath))

  configPath
}

Global / onChangedBuildSource := ReloadOnSourceChanges
