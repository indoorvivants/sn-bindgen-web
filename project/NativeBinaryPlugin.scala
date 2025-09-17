import sbt.Keys.*
import sbt.*
import sbt.plugins.JvmPlugin

import java.nio.file.Files
import java.util.Arrays
import java.util.stream.Collectors
import scala.sys.process
import sjsonnew.JsonFormat
import scala.util.control.NonFatal
import scala.scalanative.sbtplugin.ScalaNativePlugin
import scala.util.Try
import sjsonnew.support.scalajson.unsafe.Converter
import java.nio.file.StandardCopyOption
import java.nio.file.CopyOption
import java.nio.file.Files
import com.indoorvivants.detective.*, Platform.*

package core {

  class BinConfig(private val params: BinConfig.Params) {
    // getters
    def name: String                    = params.name
    def destinationDir: File            = params.destinationDir
    def extraDestinationDirs: Seq[File] = params.extraDestinationDirs

    // setters
    def withName(n: String): BinConfig = copy(_.copy(name = n))
    def withDestinationDir(dir: File)  = copy(_.copy(destinationDir = dir))
    def addDestinationDir(st: File) =
      copy(s => s.copy(extraDestinationDirs = s.extraDestinationDirs :+ st))

    private def copy(f: BinConfig.Params => BinConfig.Params): BinConfig =
      new BinConfig(f(params))
  }
  object BinConfig {
    private case class Params(
        name: String,
        destinationDir: File,
        extraDestinationDirs: Seq[File] = Seq.empty
    )

    def default(name: String, destinationDir: File) = new BinConfig(
      Params(name = name, destinationDir = destinationDir)
    )
  }

  case class BuildResult(file: File, copies: Seq[File]) {
    override def toString() =
      s"BuildResult[file=$file, copies=$copies]"
  }
}

object NativeBinaryPlugin extends AutoPlugin {

  object autoImport {
    type BinConfig = core.BinConfig
    val BinConfig = core.BinConfig

    type BuildResult = core.BuildResult
    val BuildResult = core.BuildResult

    val buildBinaryConfig          = settingKey[BinConfig]("")
    val buildBinaryDebug           = taskKey[BuildResult]("")
    val buildBinaryRelease         = taskKey[BuildResult]("")
    val buildBinaryPlatformDebug   = taskKey[BuildResult]("")
    val buildBinaryPlatformRelease = taskKey[BuildResult]("")
  }

  override def requires: Plugins = ScalaNativePlugin

  import autoImport.*

  private def writeBinary(
      source: File,
      destinationDir: File,
      extraDestinationDirs: Seq[File],
      log: sbt.Logger,
      platform: Option[Platform.Target],
      debug: Boolean,
      name: String
  ): BuildResult = {

    import java.nio.file.*

    val fullName = platform match {
      case None => name
      case Some(target) =>
        val ext = target.os match {
          case Platform.OS.Windows => ".exe"
          case _                   => ""
        }

        name + "-" + ArtifactNames.coursierString(target) + ext
    }

    import scala.sys.process.*

    val built = List.newBuilder[File]

    (destinationDir +: extraDestinationDirs).foreach { dir =>
      val seg  = if (debug) "debug" else "release"
      val dest = dir / seg / fullName

      built += dest

      Files.createDirectories(dest.getParentFile().toPath())

      Files.copy(
        source.toPath(),
        dest.toPath(),
        StandardCopyOption.COPY_ATTRIBUTES,
        StandardCopyOption.REPLACE_EXISTING
      )

      if (debug && platform.exists(_.os == Platform.OS.MacOS))
        s"dsymutil $dest".!!

      log.info(s"Binary [$name] built in ${dest}")

    }

    val artifacts = built.result()

    BuildResult(artifacts.head, artifacts.tail)
  }

  val SN = ScalaNativePlugin.autoImport

  override lazy val projectSettings = Seq(
    buildBinaryConfig := BinConfig.default(
      name.value,
      destinationDir = (ThisBuild / baseDirectory).value / "out"
    ),
    buildBinaryDebug :=
      writeBinary(
        source = (ThisProject / Compile / (SN.nativeLink)).value,
        destinationDir = buildBinaryConfig.value.destinationDir,
        extraDestinationDirs = buildBinaryConfig.value.extraDestinationDirs,
        log = sLog.value,
        platform = None,
        debug = true,
        name = (buildBinaryConfig.value.name)
      ),
    buildBinaryRelease :=
      writeBinary(
        source = (ThisProject / Compile / (SN.nativeLinkReleaseFast)).value,
        destinationDir = buildBinaryConfig.value.destinationDir,
        extraDestinationDirs = buildBinaryConfig.value.extraDestinationDirs,
        log = sLog.value,
        platform = None,
        debug = false,
        name = (buildBinaryConfig.value.name)
      ),
    buildBinaryPlatformDebug :=
      writeBinary(
        source = (ThisProject / Compile / (SN.nativeLink)).value,
        destinationDir = buildBinaryConfig.value.destinationDir,
        extraDestinationDirs = buildBinaryConfig.value.extraDestinationDirs,
        log = sLog.value,
        platform = Some(Platform.target),
        debug = true,
        name = (buildBinaryConfig.value.name)
      ),
    buildBinaryPlatformRelease :=
      writeBinary(
        source = (ThisProject / Compile / (SN.nativeLinkReleaseFast)).value,
        destinationDir = buildBinaryConfig.value.destinationDir,
        extraDestinationDirs = buildBinaryConfig.value.extraDestinationDirs,
        log = sLog.value,
        platform = Some(Platform.target),
        debug = false,
        name = (buildBinaryConfig.value.name)
      )
  )

  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()

}

import com.indoorvivants.detective.Platform
import Platform.*

object ArtifactNames {
  def jarString(os: Platform.OS): String = {
    import Platform.OS.*
    os match {
      case Windows => "windows"
      case MacOS   => "osx"
      case Linux   => "linux"
      case Unknown => "unknown"
    }
  }

  def jarString(bits: Platform.Bits, arch: Platform.Arch): String = {
    (bits, arch) match {
      case (Bits.x64, Arch.Intel) => "x86_64"
      case (Bits.x64, Arch.Arm)   => "aarch64"
      case (Bits.x32, Arch.Intel) => "x86_32"
      case (Bits.x32, Arch.Arm)   => "aarch32"
    }
  }

  def jarString(target: Platform.Target): String = {
    jarString(target.bits, target.arch) + "-" + jarString(target.os)
  }

  def coursierString(os: Platform.OS): String = {
    import Platform.OS.*
    os match {
      case Windows => "pc-win32"
      case MacOS   => "apple-darwin"
      case Linux   => "pc-linux"
      case Unknown => "unknown"
    }
  }

  def coursierString(target: Platform.Target): String = {
    jarString(target.bits, target.arch) + "-" + coursierString(target.os)
  }

}
