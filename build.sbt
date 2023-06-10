import java.lang
import scala.scalanative.build.Mode

val V = new {
  val Scala = "3.3.0"

  val snunit = "0.7.0"

  val snCrypto = "0.0.4"

  val http4s = "0.23.19"

  val fs2 = "3.6.1"

  val scribe = "3.11.1"

  val opaqueNewtypes = "0.0.2"

  val porcupine = "0.0.1"

  val macroTaskExecutor = "1.1.1"

  val laminar = "15.0.1"

  val circe = "0.14.5"
}

val isScala3 = Seq(VirtualAxis.scalaABIVersion(V.Scala))
val isNative = Seq(VirtualAxis.native)
val isJS     = Seq(VirtualAxis.js)

lazy val frontend =
  projectMatrix
    .dependsOn(protocols)
    .in(file("modules/frontend"))
    .defaultAxes((isScala3 ++ isJS)*)
    .jsPlatform(Seq(V.Scala))
    .settings(
      scalaJSUseMainModuleInitializer := true,
      libraryDependencies ++= Seq(
        "org.http4s"   %%% "http4s-dom"                  % "0.2.7",
        "com.raquo"    %%% "laminar"                     % V.laminar,
        "com.raquo"    %%% "waypoint"                    % "6.0.0",
        "org.scala-js" %%% "scala-js-macrotask-executor" % V.macroTaskExecutor
      )
    )

lazy val protocols =
  projectMatrix
    .in(file("modules/protocols"))
    .defaultAxes(isScala3*)
    .nativePlatform(Seq(V.Scala))
    .jsPlatform(Seq(V.Scala))
    .enablePlugins(Smithy4sCodegenPlugin)
    .settings(
      libraryDependencies += "com.disneystreaming.smithy4s" %%% "smithy4s-http4s" % smithy4sVersion.value
    )

lazy val vcpkgSettings = Seq(
  vcpkgDependencies := VcpkgDependencies
    .ManifestFile((ThisBuild / baseDirectory).value / "vcpkg.json"),
  vcpkgNativeConfig ~= {
    _.withRenamedLibraries(
      Map("zstd" -> "libzstd")
    )
  }
)

lazy val `http-server` =
  projectMatrix
    .in(file("modules/http-server"))
    .dependsOn(protocols)
    .defaultAxes((isScala3 ++ isNative)*)
    .enablePlugins(ScalaNativePlugin, VcpkgNativePlugin)
    .nativePlatform(Seq(V.Scala))
    .settings(
      libraryDependencies += "com.github.lolgab" %%% "snunit-http4s0.23" % V.snunit,
      libraryDependencies += "com.github.lolgab" %%% "scala-native-crypto" % V.snCrypto,
      libraryDependencies += "org.http4s" %%% "http4s-dsl"          % V.http4s,
      libraryDependencies += "org.http4s" %%% "http4s-ember-client" % V.http4s,
      libraryDependencies += "com.outr"   %%% "scribe-cats"         % V.scribe,
      nativeConfig ~= { _.withIncrementalCompilation(true) },
      scalacOptions += "-Wunused:all",
      vcpkgSettings
    )

lazy val bindings =
  projectMatrix
    .in(file("modules/bindings"))
    .defaultAxes((isScala3 ++ isNative)*)
    .nativePlatform(Seq(V.Scala))
    .enablePlugins(ScalaNativePlugin, VcpkgNativePlugin, BindgenPlugin)
    .settings(
      vcpkgSettings,
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
            .builder(configurator.includes("zstd") / "zstd.h", "zstd")
            .withCImports(List("zstd.h"))
            .withClangFlags(
              List(
                "-I" + configurator.includes("zstd").toString
              )
            )
            .build
        )
      }
    )

lazy val `queue-processor` =
  projectMatrix
    .in(file("modules/queue-processor"))
    .dependsOn(protocols, bindings)
    .defaultAxes((isScala3 ++ isNative)*)
    .nativePlatform(Seq(V.Scala))
    .enablePlugins(ScalaNativePlugin, VcpkgNativePlugin)
    .settings(
      vcpkgSettings,
      libraryDependencies += ("com.indoorvivants" % "bindgen_native0.4_3" % "0.0.17")
        .classifier(""),
      libraryDependencies += "io.circe" %%% "circe-parser" % V.circe,
      libraryDependencies += "com.indoorvivants" %%% "opaque-newtypes" % V.opaqueNewtypes, // SBT
      libraryDependencies += "com.github.lolgab" %%% "snunit-http4s0.23" % V.snunit,
      libraryDependencies += "com.github.lolgab" %%% "scala-native-crypto" % V.snCrypto,
      libraryDependencies += "com.outr"       %%% "scribe-cats" % V.scribe,
      libraryDependencies += "org.http4s"     %%% "http4s-dsl"  % V.http4s,
      libraryDependencies += "com.armanbilge" %%% "porcupine"   % V.porcupine,
      nativeConfig ~= { _.withIncrementalCompilation(true) },
      nativeConfig ~= usesLibClang,
      scalacOptions += "-Wunused:all"
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
      "environment": {
        "WORKER_HOST": "http://localhost:8888"
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
  locally { buildWeb.value }
  locally { buildWorker.value }
  locally { buildFrontend.value }
}

lazy val frontendFile = taskKey[File]("")
frontendFile := Def.taskIf {
  if (sys.env.get("CI").isDefined)
    (frontend.js(V.Scala) / Compile / fullLinkJSOutput).value
  else
    (frontend.js(V.Scala) / Compile / fastLinkJSOutput).value

}.value

lazy val buildFrontend = taskKey[Unit]("")
buildFrontend := {
  val js        = frontendFile.value / "main.js"
  val buildPath = ((ThisBuild / baseDirectory).value / "build")
  import com.indoorvivants.yank.*
  val tailwind =
    tools.TailwindCSS.bootstrap(tools.TailwindCSS.Config(version = "3.3.2"))

  import scala.sys.process.*
  val s = streams.value

  val inputCss = s.cacheDirectory / "input.css"
  if (!inputCss.exists())
    IO.write(
      inputCss,
      """
        |@tailwind base;
        |@tailwind components;
        |@tailwind utilities;
        """.stripMargin
    )

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
          <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/styles/night-owl.min.css">
          <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/highlight.min.js"></script>
          <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/11.7.0/languages/scala.min.js"></script>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.45.0/codemirror.min.css">
          <link rel="stylesheet" href="/styles.css">
          <title>Scala 3 Native bindings generator</title>
        </head>
        <body class = "bg-blue-950">
        <div id="appContainer"></div>
        <script src="/frontend.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.45.0/codemirror.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.45.0/mode/clike/clike.js"></script>
        </body>
      </html>
    """.stripMargin
  )
  val minify = if (sys.env.contains("CI")) "--minify" else ""

  val css =
    s"$tailwind -i $inputCss $minify --content ${js},${destination / "index.html"}".!!

  IO.write(destination / "styles.css", css)

  IO.copyFile(js, destination / "frontend.js")
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
  val buildPath          = ((ThisBuild / baseDirectory).value / "build")
  val defaultStaticPath  = buildPath / "static"
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
      sys.env.getOrElse("BINDGEN_WEB_STATIC_PATH", defaultStaticPath.toString)
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

import scala.scalanative.build.NativeConfig
import com.indoorvivants.detective.Platform

import java.nio.file.Paths

def usesLibClang(conf: NativeConfig) = {
  val libraryName =
    if (Platform.os == Platform.OS.Windows) "libclang" else "clang"

  val detected = llvmFolder(conf.clang.toAbsolutePath())

  val arm64 =
    if (Platform.arch == Platform.Arch.Arm) Seq("-arch", "arm64") else Seq.empty

  conf
    .withLinkingOptions(
      conf.linkingOptions ++
        Seq("-l" + libraryName) ++
        detected.llvmLib.map("-L" + _) ++ arm64
    )
    .withCompileOptions(
      conf.compileOptions ++ detected.llvmInclude.map("-I" + _) ++ arm64
    )
}

def llvmFolder(clangPath: java.nio.file.Path): LLVMInfo = {
  import Platform.OS.*

  Platform.os match {
    case MacOS =>
      val detected =
        sys.env
          .get("LLVM_BIN")
          .map(Paths.get(_))
          .map(_.getParent)
          .filter(_.toFile.exists)
          .toList

      val speculative =
        if (detected.isEmpty)
          List(
            Paths.get("/usr/local/opt/llvm@14"),
            Paths.get("/usr/local/opt/llvm"),
            Paths.get("/opt/homebrew/opt/llvm@14"),
            Paths.get("/opt/homebrew/opt/llvm")
          )
        else Nil

      val all = (detected ++ speculative).dropWhile(!_.toFile.exists())

      val includes = all
        .map(_.resolve("include"))
        .map(_.toAbsolutePath().toString)

      val lib = all
        .map(_.resolve("lib"))
        .map(_.toAbsolutePath().toString)

      LLVMInfo(
        llvmInclude = includes,
        llvmLib = lib
      )
    case Linux | Windows =>
      // <llvm-path>/bin/clang
      val realPath   = clangPath.toRealPath()
      val binFolder  = realPath.getParent()
      val llvmFolder = binFolder.getParent()

      if (llvmFolder.toFile.exists())
        LLVMInfo(
          llvmInclude = List(llvmFolder.resolve("include").toString),
          llvmLib = List(llvmFolder.resolve("lib").toString)
        )
      else LLVMInfo(Nil, Nil)
    case _ => LLVMInfo(Nil, Nil)
  }
}


concurrentRestrictions in Global ++= Seq(
  Tags.limit(Tags.Test, 1),
  // By default dependencies of test can be run in parallel, it includeds Scala Native/Scala.js linkers
  // Limit them to lower memory usage, especially when targetting LLVM
  Tags.limit(NativeTags.Link, 1),
  Tags.limit(ScalaJSTags.Link, 1)
)
