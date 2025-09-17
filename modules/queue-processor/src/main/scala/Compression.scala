package bindgen.web.internal.jobs

import scodec.bits.ByteVector
import zstd.all.*

import scala.scalanative.libc.string
import scala.scalanative.unsafe.*
import scala.scalanative.unsigned.*

object Compression:
  // adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_compression.c
  def compressString(code: String): Array[Byte] =
    Zone:
      val inBuf      = toCString(code)
      val inBufSize  = size_t(code.getBytes().length.toCSize)
      val outBufSize = ZSTD_compressBound(inBufSize)
      val outBuf     = alloc[Byte](outBufSize.toInt)
      val resultSize =
        ZSTD_compress(outBuf, outBufSize, inBuf, inBufSize, 10)

      val bytes = new Array[Byte](resultSize.toInt)
      string.memcpy(bytes.at(0), outBuf, resultSize)

      scribe.debug(
        s"Compressed `${code.replace("\\n", "\\\n")}` from $inBufSize to $resultSize with a buffer of $outBufSize: ${bytes.toList}"
      )

      bytes

  // adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_compression.c
  def compressArray(codeA: Array[Byte]): Array[Byte] = Zone:
    val inBuf      = codeA.at(0)
    val inBufSize  = size_t(codeA.length.toCSize)
    val outBufSize = ZSTD_compressBound(inBufSize)
    val outBuf     = alloc[Byte](outBufSize.toInt)
    val resultSize =
      ZSTD_compress(outBuf, outBufSize, inBuf, inBufSize, 10)

    val bytes = new Array[Byte](resultSize.toInt)
    string.memcpy(bytes.at(0), outBuf, resultSize)
    scribe.debug(
      s"Compressed from $inBufSize to $resultSize with a buffer of $outBufSize"
    )

    bytes

  // Adapted from https://github.com/facebook/zstd/blob/dev/examples/simple_decompression.c#L38
  def decompressArray(arr: Array[Byte]): Array[Byte] = Zone:
    val cSize = size_t(arr.length.toCSize)
    val cBuff = arr.at(0)
    val rSize = ZSTD_getFrameContentSize(cBuff, cSize)
    val rBuff = alloc[Byte](rSize.toInt)
    ZSTD_decompress(rBuff, size_t(rSize.toLong.toCSize), cBuff, cSize)

    val bytes = new Array[Byte](rSize.toInt)
    string.memcpy(bytes.at(0), rBuff, rSize.toLong.toCSize)
    scribe.debug(
      s"Decompressed: ${bytes.toList} (original ${arr.toList}"
    )

    bytes

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
