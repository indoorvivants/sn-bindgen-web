package bindgen.web.internal.jobs

import scodec.bits.ByteVector
import zstd.all.*

import scala.scalanative.runtime.libc
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

object Compression:
  // adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_compression.c
  def compressString(code: String): Array[Byte] = Zone { implicit z =>
    val inBuf      = toCString(code)
    val inBufSize  = size_t(code.getBytes().length.toULong)
    val outBufSize = ZSTD_compressBound(inBufSize)
    val outBuf     = alloc[Byte](outBufSize.value)
    val resultSize =
      ZSTD_compress(outBuf, outBufSize, inBuf, inBufSize, 10)

    val bytes = new Array[Byte](resultSize.value.toInt)
    libc.memcpy(bytes.at(0), outBuf, resultSize.value)

    scribe.debug(
      s"Compressed `${code.replace("\\n", "\\\n")}` from $inBufSize to $resultSize with a buffer of $outBufSize: ${bytes.toList}"
    )

    bytes
  }

  // adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_compression.c
  def compressArray(codeA: Array[Byte]): Array[Byte] = Zone { implicit z =>
    // val code = new String(codeA) // TODO

    // val inBuf      = toCString(code)
    val inBuf      = codeA.at(0)
    val inBufSize  = size_t(codeA.length.toULong)
    val outBufSize = ZSTD_compressBound(inBufSize)
    val outBuf     = alloc[Byte](outBufSize.value)
    val resultSize =
      ZSTD_compress(outBuf, outBufSize, inBuf, inBufSize, 10)

    val bytes = new Array[Byte](resultSize.value.toInt)
    libc.memcpy(bytes.at(0), outBuf, resultSize.value)
    scribe.debug(
      s"Compressed from $inBufSize to $resultSize with a buffer of $outBufSize"
    )

    bytes
  }

  // Adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_decompression.c#L38
  def decompressArray(arr: Array[Byte]): Array[Byte] = Zone { implicit z =>
    val cSize = size_t(arr.length.toULong)
    val cBuff = arr.at(0)
    val rSize = ZSTD_getFrameContentSize(cBuff, cSize)
    val rBuff = alloc[Byte](rSize)
    ZSTD_decompress(rBuff, size_t(rSize), cBuff, cSize)

    val bytes = new Array[Byte](rSize.toInt)
    libc.memcpy(bytes.at(0), rBuff, rSize)
    scribe.debug(
      s"Decompressed: ${bytes.toList} (original ${arr.toList}"
    )

    bytes

  }

  // // Adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_decompression.c#L38
  // def decompressByteVector(bv: ByteVector): Array[Byte] = Zone { implicit z =>
  //   val arr   = bv.toArray
  //   val cSize = size_t(arr.length.toULong)
  //   val cBuff = arr.at(0)
  //   val rSize = ZSTD_getFrameContentSize(cBuff, cSize)
  //   val rBuff = alloc[Byte](rSize)
  //   // val dSize = ZSTD_decompress(rBuff, size_t(rSize), cBuff, cSize)

  //   val bytes = new Array[Byte](rSize.toInt)
  //   libc.memcpy(bytes.at(0), rBuff, rSize)

  //   bytes

  // }
end Compression
