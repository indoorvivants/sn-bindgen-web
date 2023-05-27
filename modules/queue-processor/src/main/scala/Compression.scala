package bindgen.web.internal.jobs

import bindgen.web.domain.JobId
import fs2.io.file.Files
import cats.effect.*
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*
import scala.scalanative.runtime.libc
import scodec.bits.ByteVector
import zstd.all.*

object Compression:
  // adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_compression.c
  def compressString(code: String): Array[Byte] = Zone { implicit z =>
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

  // Adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_decompression.c#L38
  def decompressByteVector(bv: ByteVector): Array[Byte] = Zone { implicit z =>
    val arr   = bv.toArray
    val cSize = size_t(arr.length.toULong)
    val cBuff = arr.at(0)
    val rSize = ZSTD_getFrameContentSize(cBuff, cSize)
    val rBuff = alloc[Byte](rSize)
    val dSize = ZSTD_decompress(rBuff, size_t(rSize), cBuff, cSize)

    val bytes = new Array[Byte](rSize.toInt)
    libc.memcpy(bytes.at(0), rBuff, rSize)

    bytes

  }
end Compression
