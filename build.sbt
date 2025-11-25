import java.lang
import scala.scalanative.build.Mode
import org.scalajs.linker.interface.ModuleSplitStyle

val V = new {
  val Scala = "3.7.3"

  val snCrypto = "0.1.0"

  val http4s = "0.23.30-161-f5b9629-SNAPSHOT"

  val fs2 = "3.13.0-M7"

  val scribe = "3.17.0"

  val opaqueNewtypes = "0.1.0"

  val skunk = "1.0-7f46fa8-SNAPSHOT"

  val macroTaskExecutor = "1.1.1"

  val laminar = "17.2.1"

  val circe = "0.14.14"

  val waypoint = "8.0.0"

  val http4sDom = "0.2.11"

  val bindgen = "0.3.1"

  val dumbo = "0.6.0-9-f0f2e0f-SNAPSHOT"

  val smithy4sFetch = "0.0.4"

  val declineDerive = "0.3.1"
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
  .aggregate(httpServer.projectRefs*)
  .aggregate(queueProcessor.projectRefs*)
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
      scalacOptions += "-Wunused:all",
      libraryDependencies ++= Seq(
        "com.raquo"    %%% "laminar"                     % V.laminar,
        "com.raquo"    %%% "waypoint"                    % V.waypoint,
        "org.scala-js" %%% "scala-js-macrotask-executor" % V.macroTaskExecutor,
        "tech.neander" %%% "smithy4s-fetch"              % V.smithy4sFetch
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
      resolvers += Resolver.sonatypeCentralSnapshots,
      libraryDependencies += "com.disneystreaming.smithy4s" %%% "smithy4s-http4s" % smithy4sVersion.value
    )

lazy val httpServer =
  projectMatrix
    .in(file("modules/http-server"))
    .dependsOn(protocols)
    .defaultAxes((isScala3 ++ isNative)*)
    .enablePlugins(ScalaNativePlugin, VcpkgNativePlugin, NativeBinaryPlugin, BuildInfoPlugin)
    .nativePlatform(Seq(V.Scala))
    .settings(configurePlatform())
    .settings(
      buildBinaryConfig := buildBinaryConfig.value
        .withName("bindgen-web-http-server"),
      resolvers += Resolver.sonatypeCentralSnapshots,
      buildInfoKeys := Seq[BuildInfoKey]("bindgenVersion" -> V.bindgen),
      libraryDependencies += "com.indoorvivants" %%% "decline-derive" % V.declineDerive,
      libraryDependencies += "com.github.lolgab" %%% "scala-native-crypto" % V.snCrypto,
      libraryDependencies += "org.http4s" %%% "http4s-dsl"          % V.http4s,
      libraryDependencies += "org.http4s" %%% "http4s-ember-client" % V.http4s,
      libraryDependencies += "org.http4s" %%% "http4s-ember-server" % V.http4s,
      libraryDependencies += "com.outr"   %%% "scribe-cats"         % V.scribe,
      nativeConfig ~= { _.withIncrementalCompilation(true).withLinkingOptions(_ ++ Seq("-arch", "arm64")) },
      scalacOptions += "-Wunused:all",
      // scalacOptions += "-P:scalanative:genStaticForwardersForNonTopLevelObjects",
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
          Binding(configurator.includes("zstd") / "zstd.h", "zstd")
            .withCImports(List("zstd.h"))
            .withClangFlags(
              List(
                "-I" + configurator.includes("zstd").toString
              )
            )
        )
      }
    )

lazy val queueProcessor =
  projectMatrix
    .in(file("modules/queue-processor"))
    .dependsOn(protocols, bindings)
    .defaultAxes((isScala3 ++ isNative)*)
    .nativePlatform(Seq(V.Scala))
    .enablePlugins(ScalaNativePlugin, VcpkgNativePlugin, NativeBinaryPlugin)
    .settings(
      remoteCache,
      vcpkgSettings,
      bindgenBinary := file(".no"),
      buildBinaryConfig := buildBinaryConfig.value
        .withName("bindgen-web-queue-processor"),
      resolvers += Resolver.sonatypeCentralSnapshots,
      libraryDependencies += "com.indoorvivants" %%% "decline-derive" % V.declineDerive,
      libraryDependencies += "com.indoorvivants" %%% "bindgen"      % V.bindgen,
      libraryDependencies += "io.circe"          %%% "circe-parser" % V.circe,
      libraryDependencies += "com.indoorvivants" %%% "opaque-newtypes" % V.opaqueNewtypes, // SBT
      libraryDependencies += "com.github.lolgab" %%% "scala-native-crypto" % V.snCrypto,
      libraryDependencies += "org.http4s" %%% "http4s-ember-server" % V.http4s,
      libraryDependencies += "com.outr"   %%% "scribe-cats"         % V.scribe,
      libraryDependencies += "org.http4s" %%% "http4s-dsl"          % V.http4s,
      libraryDependencies += "org.tpolecat" %%% "skunk-core" % V.skunk,
      libraryDependencies += "dev.rolang"   %%% "dumbo"      % V.dumbo,
      (Compile / compile) := ((Compile / compile) dependsOn (Compile / copyResources)).value,
      nativeConfig ~= {
        _.withIncrementalCompilation(true).withEmbedResources(true)
      },
      nativeLinkReleaseFast / nativeConfig ~= {
        _.withEmbedResources(true)
      },
      nativeConfig := usesLibClang(nativeConfig.value, sLog.value),
      scalacOptions += "-Wunused:all"
    )
    .settings(configurePlatform())

lazy val vcpkgSettings = Seq(
  vcpkgDependencies := VcpkgDependencies
    .ManifestFile((ThisBuild / baseDirectory).value / "vcpkg.json"),
  vcpkgNativeConfig ~= {
    _.withRenamedLibraries(
      Map("zstd" -> "libzstd", "utf8proc" -> "libutf8proc")
    )
  }
)

import scala.sys.process

val buildApp = taskKey[File]("")
ThisBuild / buildApp := {
  val web       = (httpServer.native(V.Scala) / buildBinaryDebug).value
  val processor = (queueProcessor.native(V.Scala) / buildBinaryDebug).value
  val frontend  = buildWebapp.value

  val dest     = (ThisBuild / baseDirectory).value / "out" / "debug"
  dest
}

val buildAppRelease = taskKey[File]("")
ThisBuild / buildAppRelease := {
  val web       = (httpServer.native(V.Scala) / buildBinaryRelease).value
  val processor = (queueProcessor.native(V.Scala) / buildBinaryRelease).value
  val frontend  = buildWebappRelease.value

  val dest     = (ThisBuild / baseDirectory).value / "out"  / "release"
  dest

}

lazy val buildWebapp = taskKey[File]("")
ThisBuild / buildWebapp := {
  val dest = (ThisBuild / baseDirectory).value / "out" / "debug" / "frontend"
  val webappRoot = (ThisBuild / baseDirectory).value / "modules" / "frontend"

  import scala.sys.process.*

  assert(
    Process("npm install", cwd = webappRoot).! == 0,
    "Command [npm install] did not finish successfully"
  )

  assert(
    Process("npm run build", cwd = webappRoot).! == 0,
    "Command [npm run build] did not finish successfully"
  )

  IO.delete(dest)
  IO.createDirectory(dest)
  IO.copyDirectory(webappRoot / "dist", dest)

  dest
}

lazy val buildWebappRelease = taskKey[File]("")
ThisBuild / buildWebappRelease := {
  val dest = (ThisBuild / baseDirectory).value / "out" / "release" / "frontend"
  val webappRoot = (ThisBuild / baseDirectory).value / "modules" / "frontend"

  import scala.sys.process.*

  assert(
    Process("npm install", cwd = webappRoot).! == 0,
    "Command [npm install] did not finish successfully"
  )

  assert(
    Process("npm run build", cwd = webappRoot).! == 0,
    "Command [npm run build] did not finish successfully"
  )

  IO.delete(dest)
  IO.copyDirectory(webappRoot / "dist", dest)

  dest
}

import scala.scalanative.build.NativeConfig
import com.indoorvivants.detective.Platform

import java.nio.file.Paths

def usesLibClang(conf: NativeConfig, logger: sbt.Logger) = {
  val libraryName =
    if (Platform.os == Platform.OS.Windows) "libclang" else "clang"

  val detected = llvmFolder(conf.clang.toAbsolutePath(), logger)

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

def llvmFolder(clangPath: java.nio.file.Path, logger: sbt.Logger): LLVMInfo = {
  import Platform.OS.*

  Platform.os match {
    case MacOS | Linux =>
      val detected =
        sys.env
          .get("LLVM_BIN")
          .map(Paths.get(_))
          .map(_.getParent)
          .filter(_.toFile.exists)
          .toList

      val speculative =
        if (detected.isEmpty) {
          logger.warn(
            "LLVM_BIN variable not set, will try to detect any LLVM installation (only works on Linux and MacOS)"
          )
          for {
            llV <- (17 until 21)
            fld <- List(
              s"/opt/homebrew/opt/llvm@$llV",
              s"/usr/local/opt/llvm@$llV",
              s"/usr/lib/llvm-$llV"
            )
          } yield Paths.get(fld)
        } else Nil

      val all = (detected ++ speculative)
        .dropWhile(!_.toFile.exists())
        .filter { path =>
          path.resolve("include").toFile().isDirectory() &&
          path.resolve("lib").toFile().isDirectory() &&
          path.resolve("bin/clang").toFile().isFile()

        }
        .headOption

      all match {
        case None =>
          logger.error(
            s"Could not detect LLVM installation in any of the paths: [${speculative.mkString(", ")}]"
          )
        case Some(path) =>
          logger.warn(s"LLVM installation was detected in $path")
      }

      val includes = all.toList
        .map(_.resolve("include"))
        .map(_.toAbsolutePath().toString)

      val lib = all.toList
        .map(_.resolve("lib"))
        .map(_.toAbsolutePath().toString)

      LLVMInfo(
        llvmInclude = includes,
        llvmLib = lib
      )
    case Windows =>
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

lazy val devServer = project
  .in(file("modules/dev-server"))
  .enablePlugins(RevolverPlugin)
  .settings(
    fork         := true,
    scalaVersion := V.Scala,
    envVars ++= Map(
      "HTTP_SERVER_BINARY" -> (httpServer.native(V.Scala) / buildBinaryDebug).value.file.toString,
      "WORKER_BINARY" -> (queueProcessor.native(V.Scala) / buildBinaryDebug).value.file.toString,
      "WORKER_HOST" -> "http://localhost:8081",
      "LLVM_BIN" -> sys.env
        .getOrElse("LLVM_BIN", "/opt/homebrew/opt/llvm@17/bin"),
      "DATABASE_URL" -> "postgres://sn_bindgen_web@localhost:5432/sn_bindgen_web?sslmode=disable"
    )
  )


def configurePlatform(
    rename: String => String = identity
) =
  Seq(
    nativeConfig := {
      import com.indoorvivants.detective.*
      import Platform.OS.*
      val conf =
        nativeConfig.value
      val arch64 =
        if (
          Platform.os == MacOS && Platform.arch == Platform.Arch.Arm && Platform.bits == Platform.Bits.x64
        )
          List("-arch", "arm64")
        else Nil

      conf
        .withLinkingOptions(
          conf.linkingOptions ++ arch64
        )
        .withCompileOptions(
          conf.compileOptions ++ arch64
        )
    }
  )

