import java.lang
import scala.scalanative.build.Mode
import org.scalajs.linker.interface.ModuleSplitStyle

val V = new {
  val Scala = "3.3.1"

  val snunit = "0.7.2"

  val snCrypto = "0.0.4"

  val http4s = "0.23.24"

  val fs2 = "3.6.1"

  val scribe = "3.12.2"

  val opaqueNewtypes = "0.0.2"

  val porcupine = "0.0.1"

  val macroTaskExecutor = "1.1.1"

  val laminar = "16.0.0"

  val circe = "0.14.6"

  val waypoint = "7.0.0"

  val http4sDom = "0.2.10"

  val bindgen = "0.0.23+5-ab40fca8-SNAPSHOT"
}

val isScala3 = Seq(VirtualAxis.scalaABIVersion(V.Scala))
val isNative = Seq(VirtualAxis.native)
val isJS     = Seq(VirtualAxis.js)

val remoteCache = Seq(
  Compile / doc / sources := Seq.empty,
  pushRemoteCacheTo := Some(
    MavenCache(
      "local-cache",
      (ThisBuild / baseDirectory).value / ".remote-cache"
    )
  )
)

lazy val root = project
  .in(file("."))
  .aggregate(frontend.projectRefs*)
  .aggregate(protocols.projectRefs*)
  .aggregate(`http-server`.projectRefs*)
  .aggregate(`queue-processor`.projectRefs*)
  .aggregate(bindings.projectRefs*)
  .settings(
    publish / skip      := true,
    publishLocal / skip := true,
    remoteCache
  )

lazy val frontend =
  projectMatrix
    .dependsOn(protocols)
    .in(file("modules/frontend"))
    .defaultAxes((isScala3 ++ isJS)*)
    .jsPlatform(Seq(V.Scala))
    .settings(
      remoteCache,
      scalaJSUseMainModuleInitializer := true,
      fastLinkJS / scalaJSLinkerConfig ~= {
        _.withModuleKind(ModuleKind.ESModule)
          .withModuleSplitStyle(
            ModuleSplitStyle.SmallModulesFor(List("bindgen.web"))
          )
      },
      libraryDependencies ++= Seq(
        "org.http4s"   %%% "http4s-dom"                  % V.http4sDom,
        "com.raquo"    %%% "laminar"                     % V.laminar,
        "com.raquo"    %%% "waypoint"                    % V.waypoint,
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
      remoteCache,
      libraryDependencies += "com.disneystreaming.smithy4s" %%% "smithy4s-http4s" % smithy4sVersion.value
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
      vcpkgSettings,
      remoteCache
    )

lazy val bindings =
  projectMatrix
    .in(file("modules/bindings"))
    .defaultAxes((isScala3 ++ isNative)*)
    .nativePlatform(Seq(V.Scala))
    .enablePlugins(ScalaNativePlugin, VcpkgNativePlugin, BindgenPlugin)
    .settings(
      remoteCache,
      vcpkgSettings,
      bindgenBinary := {
        if (sys.env.contains("CI")) file(".no") else bindgenBinary.value
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
      remoteCache,
      vcpkgSettings,
      bindgenBinary := file(".no"),
      resolvers += Resolver.sonatypeRepo("snapshots"),
      libraryDependencies += ("com.indoorvivants" % "bindgen_native0.4_3" % V.bindgen)
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

lazy val vcpkgSettings = Seq(
  vcpkgDependencies := VcpkgDependencies
    .ManifestFile((ThisBuild / baseDirectory).value / "vcpkg.json"),
  vcpkgNativeConfig ~= {
    _.withRenamedLibraries(
      Map("zstd" -> "libzstd")
    )
  }
)

import scala.sys.process

val buildApp = taskKey[File]("")
ThisBuild / buildApp := {
  locally { buildWeb.value }
  locally { buildWorker.value }
  locally { buildFrontend.value }

  val dest     = (ThisBuild / baseDirectory).value / "build"
  val statedir = dest / "statedir"
  IO.createDirectory(statedir)

  IO.copyFile(dest.getParentFile() / "conf.json", statedir / "conf.json")

  dest

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
    (ThisBuild / baseDirectory).value / "build" / "worker"

  IO.delete(destination)

  IO.copyFile(
    target,
    destination,
    preserveExecutable = true,
    preserveLastModified = true
  )
}

val buildWeb = taskKey[Unit]("")
buildWeb := {
  val target = (`http-server`.native(V.Scala) / Compile / nativeLink).value

  val destination = (ThisBuild / baseDirectory).value / "build" / "web-server"

  IO.delete(destination)

  IO.copyFile(
    target,
    destination,
    preserveExecutable = true,
    preserveLastModified = true
  )
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

def UNITD_LOCAL_COMMAND =
  "unitd --statedir statedir --log /dev/stderr --no-daemon --control 127.0.0.1:9000"

lazy val runServer = taskKey[Unit]("")
runServer := {
  val dest = buildApp.value

  import scala.sys.process.*
  val data = (ThisBuild / baseDirectory).value / "data" / "worker.db"

  val proc = Process(
    UNITD_LOCAL_COMMAND,
    cwd = dest,
    "WORKER_HOST" -> "http://localhost:8888",
    "DB_PATH"     -> data.toString
  )

  proc.!
}

lazy val devServer = project
  .in(file("modules/dev-server"))
  .enablePlugins(RevolverPlugin)
  .settings(
    fork         := true,
    scalaVersion := V.Scala,
    envVars ++= Map(
      "SERVER_BINARY" -> (ThisBuild / buildApp).value.toString,
      "UNITD_COMMAND" -> UNITD_LOCAL_COMMAND,
      "SERVER_CWD"    -> ((ThisBuild / baseDirectory).value / "build").toString,
      "WORKER_HOST"   -> "http://localhost:8888",
      "DB_PATH" -> ((ThisBuild / baseDirectory).value / "data" / "worker.db").toString,
      "LLVM_BIN" -> "/opt/homebrew/opt/llvm@14/bin"
    )
  )
