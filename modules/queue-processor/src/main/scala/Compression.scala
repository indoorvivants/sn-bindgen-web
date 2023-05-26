package bindgen.web.internal.jobs

import bindgen.web.domain.JobId
import fs2.io.file.Files
import cats.effect.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scala.scalanative.runtime.libc

object Compression:
  def compressString(code: String): Array[Byte] = Zone { implicit z =>
    import zstd.all.*
    val inBuf      = toCString(code)
    val inBufSize  = size_t(code.getBytes().length.toULong)
    val outBufSize = ZSTD_compressBound(inBufSize)
    val outBuf     = alloc[Byte](outBufSize.value)
    val resultSize =
      ZSTD_compress(outBuf, outBufSize, inBuf, inBufSize, 10)

    scribe.info(
      s"Compressed from $inBufSize to $resultSize with a buffer of $outBufSize"
    )

    val bytes = new Array[Byte](resultSize.value.toInt)
    libc.memcpy(bytes.at(0), outBuf, resultSize.value)

    bytes
  }
end Compression
