package zstd

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

object predef:
  private[zstd] trait CEnumU[T](using eq: T =:= UInt):
    given Tag[T] = Tag.UInt.asInstanceOf[Tag[T]]
    extension (inline t: T)
     inline def int: CInt = eq.apply(t).toInt
     inline def uint: CUnsignedInt = eq.apply(t)
     inline def value: CUnsignedInt = eq.apply(t)


object enumerations:
  import predef.*
  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_EndDirective = CUnsignedInt
  object ZSTD_EndDirective extends CEnumU[ZSTD_EndDirective]:
    given _tag: Tag[ZSTD_EndDirective] = Tag.UInt
    inline def define(inline a: Long): ZSTD_EndDirective = a.toUInt
    val ZSTD_e_continue = define(0)
    val ZSTD_e_flush = define(1)
    val ZSTD_e_end = define(2)
    inline def getName(inline value: ZSTD_EndDirective): Option[String] =
      inline value match
        case ZSTD_e_continue => Some("ZSTD_e_continue")
        case ZSTD_e_flush => Some("ZSTD_e_flush")
        case ZSTD_e_end => Some("ZSTD_e_end")
        case _ => None
    extension (a: ZSTD_EndDirective)
      inline def &(b: ZSTD_EndDirective): ZSTD_EndDirective = a & b
      inline def |(b: ZSTD_EndDirective): ZSTD_EndDirective = a | b
      inline def is(b: ZSTD_EndDirective): Boolean = (a & b) == b

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_ResetDirective = CUnsignedInt
  object ZSTD_ResetDirective extends CEnumU[ZSTD_ResetDirective]:
    given _tag: Tag[ZSTD_ResetDirective] = Tag.UInt
    inline def define(inline a: Long): ZSTD_ResetDirective = a.toUInt
    val ZSTD_reset_session_only = define(1)
    val ZSTD_reset_parameters = define(2)
    val ZSTD_reset_session_and_parameters = define(3)
    inline def getName(inline value: ZSTD_ResetDirective): Option[String] =
      inline value match
        case ZSTD_reset_session_only => Some("ZSTD_reset_session_only")
        case ZSTD_reset_parameters => Some("ZSTD_reset_parameters")
        case ZSTD_reset_session_and_parameters => Some("ZSTD_reset_session_and_parameters")
        case _ => None
    extension (a: ZSTD_ResetDirective)
      inline def &(b: ZSTD_ResetDirective): ZSTD_ResetDirective = a & b
      inline def |(b: ZSTD_ResetDirective): ZSTD_ResetDirective = a | b
      inline def is(b: ZSTD_ResetDirective): Boolean = (a & b) == b

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_cParameter = CUnsignedInt
  object ZSTD_cParameter extends CEnumU[ZSTD_cParameter]:
    given _tag: Tag[ZSTD_cParameter] = Tag.UInt
    inline def define(inline a: Long): ZSTD_cParameter = a.toUInt
    val ZSTD_c_compressionLevel = define(100)
    val ZSTD_c_windowLog = define(101)
    val ZSTD_c_hashLog = define(102)
    val ZSTD_c_chainLog = define(103)
    val ZSTD_c_searchLog = define(104)
    val ZSTD_c_minMatch = define(105)
    val ZSTD_c_targetLength = define(106)
    val ZSTD_c_strategy = define(107)
    val ZSTD_c_enableLongDistanceMatching = define(160)
    val ZSTD_c_ldmHashLog = define(161)
    val ZSTD_c_ldmMinMatch = define(162)
    val ZSTD_c_ldmBucketSizeLog = define(163)
    val ZSTD_c_ldmHashRateLog = define(164)
    val ZSTD_c_contentSizeFlag = define(200)
    val ZSTD_c_checksumFlag = define(201)
    val ZSTD_c_dictIDFlag = define(202)
    val ZSTD_c_nbWorkers = define(400)
    val ZSTD_c_jobSize = define(401)
    val ZSTD_c_overlapLog = define(402)
    val ZSTD_c_experimentalParam1 = define(500)
    val ZSTD_c_experimentalParam2 = define(10)
    val ZSTD_c_experimentalParam3 = define(1000)
    val ZSTD_c_experimentalParam4 = define(1001)
    val ZSTD_c_experimentalParam5 = define(1002)
    val ZSTD_c_experimentalParam6 = define(1003)
    val ZSTD_c_experimentalParam7 = define(1004)
    val ZSTD_c_experimentalParam8 = define(1005)
    val ZSTD_c_experimentalParam9 = define(1006)
    val ZSTD_c_experimentalParam10 = define(1007)
    val ZSTD_c_experimentalParam11 = define(1008)
    val ZSTD_c_experimentalParam12 = define(1009)
    val ZSTD_c_experimentalParam13 = define(1010)
    val ZSTD_c_experimentalParam14 = define(1011)
    val ZSTD_c_experimentalParam15 = define(1012)
    val ZSTD_c_experimentalParam16 = define(1013)
    val ZSTD_c_experimentalParam17 = define(1014)
    val ZSTD_c_experimentalParam18 = define(1015)
    val ZSTD_c_experimentalParam19 = define(1016)
    inline def getName(inline value: ZSTD_cParameter): Option[String] =
      inline value match
        case ZSTD_c_compressionLevel => Some("ZSTD_c_compressionLevel")
        case ZSTD_c_windowLog => Some("ZSTD_c_windowLog")
        case ZSTD_c_hashLog => Some("ZSTD_c_hashLog")
        case ZSTD_c_chainLog => Some("ZSTD_c_chainLog")
        case ZSTD_c_searchLog => Some("ZSTD_c_searchLog")
        case ZSTD_c_minMatch => Some("ZSTD_c_minMatch")
        case ZSTD_c_targetLength => Some("ZSTD_c_targetLength")
        case ZSTD_c_strategy => Some("ZSTD_c_strategy")
        case ZSTD_c_enableLongDistanceMatching => Some("ZSTD_c_enableLongDistanceMatching")
        case ZSTD_c_ldmHashLog => Some("ZSTD_c_ldmHashLog")
        case ZSTD_c_ldmMinMatch => Some("ZSTD_c_ldmMinMatch")
        case ZSTD_c_ldmBucketSizeLog => Some("ZSTD_c_ldmBucketSizeLog")
        case ZSTD_c_ldmHashRateLog => Some("ZSTD_c_ldmHashRateLog")
        case ZSTD_c_contentSizeFlag => Some("ZSTD_c_contentSizeFlag")
        case ZSTD_c_checksumFlag => Some("ZSTD_c_checksumFlag")
        case ZSTD_c_dictIDFlag => Some("ZSTD_c_dictIDFlag")
        case ZSTD_c_nbWorkers => Some("ZSTD_c_nbWorkers")
        case ZSTD_c_jobSize => Some("ZSTD_c_jobSize")
        case ZSTD_c_overlapLog => Some("ZSTD_c_overlapLog")
        case ZSTD_c_experimentalParam1 => Some("ZSTD_c_experimentalParam1")
        case ZSTD_c_experimentalParam2 => Some("ZSTD_c_experimentalParam2")
        case ZSTD_c_experimentalParam3 => Some("ZSTD_c_experimentalParam3")
        case ZSTD_c_experimentalParam4 => Some("ZSTD_c_experimentalParam4")
        case ZSTD_c_experimentalParam5 => Some("ZSTD_c_experimentalParam5")
        case ZSTD_c_experimentalParam6 => Some("ZSTD_c_experimentalParam6")
        case ZSTD_c_experimentalParam7 => Some("ZSTD_c_experimentalParam7")
        case ZSTD_c_experimentalParam8 => Some("ZSTD_c_experimentalParam8")
        case ZSTD_c_experimentalParam9 => Some("ZSTD_c_experimentalParam9")
        case ZSTD_c_experimentalParam10 => Some("ZSTD_c_experimentalParam10")
        case ZSTD_c_experimentalParam11 => Some("ZSTD_c_experimentalParam11")
        case ZSTD_c_experimentalParam12 => Some("ZSTD_c_experimentalParam12")
        case ZSTD_c_experimentalParam13 => Some("ZSTD_c_experimentalParam13")
        case ZSTD_c_experimentalParam14 => Some("ZSTD_c_experimentalParam14")
        case ZSTD_c_experimentalParam15 => Some("ZSTD_c_experimentalParam15")
        case ZSTD_c_experimentalParam16 => Some("ZSTD_c_experimentalParam16")
        case ZSTD_c_experimentalParam17 => Some("ZSTD_c_experimentalParam17")
        case ZSTD_c_experimentalParam18 => Some("ZSTD_c_experimentalParam18")
        case ZSTD_c_experimentalParam19 => Some("ZSTD_c_experimentalParam19")
        case _ => None
    extension (a: ZSTD_cParameter)
      inline def &(b: ZSTD_cParameter): ZSTD_cParameter = a & b
      inline def |(b: ZSTD_cParameter): ZSTD_cParameter = a | b
      inline def is(b: ZSTD_cParameter): Boolean = (a & b) == b

  /**
   * ********************************************* Advanced decompression API (Requires v1.4.0+) **********************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_dParameter = CUnsignedInt
  object ZSTD_dParameter extends CEnumU[ZSTD_dParameter]:
    given _tag: Tag[ZSTD_dParameter] = Tag.UInt
    inline def define(inline a: Long): ZSTD_dParameter = a.toUInt
    val ZSTD_d_windowLogMax = define(100)
    val ZSTD_d_experimentalParam1 = define(1000)
    val ZSTD_d_experimentalParam2 = define(1001)
    val ZSTD_d_experimentalParam3 = define(1002)
    val ZSTD_d_experimentalParam4 = define(1003)
    val ZSTD_d_experimentalParam5 = define(1004)
    inline def getName(inline value: ZSTD_dParameter): Option[String] =
      inline value match
        case ZSTD_d_windowLogMax => Some("ZSTD_d_windowLogMax")
        case ZSTD_d_experimentalParam1 => Some("ZSTD_d_experimentalParam1")
        case ZSTD_d_experimentalParam2 => Some("ZSTD_d_experimentalParam2")
        case ZSTD_d_experimentalParam3 => Some("ZSTD_d_experimentalParam3")
        case ZSTD_d_experimentalParam4 => Some("ZSTD_d_experimentalParam4")
        case ZSTD_d_experimentalParam5 => Some("ZSTD_d_experimentalParam5")
        case _ => None
    extension (a: ZSTD_dParameter)
      inline def &(b: ZSTD_dParameter): ZSTD_dParameter = a & b
      inline def |(b: ZSTD_dParameter): ZSTD_dParameter = a | b
      inline def is(b: ZSTD_dParameter): Boolean = (a & b) == b

  /**
   * ******************************************* Advanced compression API (Requires v1.4.0+) ********************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_strategy = CUnsignedInt
  object ZSTD_strategy extends CEnumU[ZSTD_strategy]:
    given _tag: Tag[ZSTD_strategy] = Tag.UInt
    inline def define(inline a: Long): ZSTD_strategy = a.toUInt
    val ZSTD_fast = define(1)
    val ZSTD_dfast = define(2)
    val ZSTD_greedy = define(3)
    val ZSTD_lazy = define(4)
    val ZSTD_lazy2 = define(5)
    val ZSTD_btlazy2 = define(6)
    val ZSTD_btopt = define(7)
    val ZSTD_btultra = define(8)
    val ZSTD_btultra2 = define(9)
    inline def getName(inline value: ZSTD_strategy): Option[String] =
      inline value match
        case ZSTD_fast => Some("ZSTD_fast")
        case ZSTD_dfast => Some("ZSTD_dfast")
        case ZSTD_greedy => Some("ZSTD_greedy")
        case ZSTD_lazy => Some("ZSTD_lazy")
        case ZSTD_lazy2 => Some("ZSTD_lazy2")
        case ZSTD_btlazy2 => Some("ZSTD_btlazy2")
        case ZSTD_btopt => Some("ZSTD_btopt")
        case ZSTD_btultra => Some("ZSTD_btultra")
        case ZSTD_btultra2 => Some("ZSTD_btultra2")
        case _ => None
    extension (a: ZSTD_strategy)
      inline def &(b: ZSTD_strategy): ZSTD_strategy = a & b
      inline def |(b: ZSTD_strategy): ZSTD_strategy = a | b
      inline def is(b: ZSTD_strategy): Boolean = (a & b) == b

object aliases:
  import _root_.zstd.enumerations.*
  import _root_.zstd.aliases.*
  import _root_.zstd.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  type ZSTD_CStream = ZSTD_CCtx
  object ZSTD_CStream: 
    given _tag: Tag[ZSTD_CStream] = ZSTD_CCtx._tag
    inline def apply(inline o: ZSTD_CCtx): ZSTD_CStream = o
    extension (v: ZSTD_CStream)
      inline def value: ZSTD_CCtx = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  type ZSTD_DStream = ZSTD_DCtx
  object ZSTD_DStream: 
    given _tag: Tag[ZSTD_DStream] = ZSTD_DCtx._tag
    inline def apply(inline o: ZSTD_DCtx): ZSTD_DStream = o
    extension (v: ZSTD_DStream)
      inline def value: ZSTD_DCtx = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/__stddef_max_align_t.h
  */
  opaque type max_align_t = Double
  object max_align_t: 
    given _tag: Tag[max_align_t] = Tag.Double
    inline def apply(inline o: Double): max_align_t = o
    extension (v: max_align_t)
      inline def value: Double = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/stddef.h
  */
  opaque type ptrdiff_t = CLongInt
  object ptrdiff_t: 
    given _tag: Tag[ptrdiff_t] = Tag.Long
    inline def apply(inline o: CLongInt): ptrdiff_t = o
    extension (v: ptrdiff_t)
      inline def value: CLongInt = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/stddef.h
  */
  opaque type rsize_t = CUnsignedLongInt
  object rsize_t: 
    given _tag: Tag[rsize_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): rsize_t = o
    extension (v: rsize_t)
      inline def value: CUnsignedLongInt = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/stddef.h
  */
  opaque type size_t = CUnsignedLongInt
  object size_t: 
    given _tag: Tag[size_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): size_t = o
    extension (v: size_t)
      inline def value: CUnsignedLongInt = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/stddef.h
  */
  opaque type wchar_t = CInt
  object wchar_t: 
    given _tag: Tag[wchar_t] = Tag.Int
    inline def apply(inline o: CInt): wchar_t = o
    extension (v: wchar_t)
      inline def value: CInt = v

object structs:
  import _root_.zstd.enumerations.*
  import _root_.zstd.aliases.*
  import _root_.zstd.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_CCtx = CStruct0
  object ZSTD_CCtx:
    given _tag: Tag[ZSTD_CCtx] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_CCtx_s = CStruct0
  object ZSTD_CCtx_s:
    given _tag: Tag[ZSTD_CCtx_s] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_CDict = CStruct0
  object ZSTD_CDict:
    given _tag: Tag[ZSTD_CDict] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_CDict_s = CStruct0
  object ZSTD_CDict_s:
    given _tag: Tag[ZSTD_CDict_s] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_DCtx = CStruct0
  object ZSTD_DCtx:
    given _tag: Tag[ZSTD_DCtx] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_DCtx_s = CStruct0
  object ZSTD_DCtx_s:
    given _tag: Tag[ZSTD_DCtx_s] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_DDict = CStruct0
  object ZSTD_DDict:
    given _tag: Tag[ZSTD_DDict] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_DDict_s = CStruct0
  object ZSTD_DDict_s:
    given _tag: Tag[ZSTD_DDict_s] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_bounds = CStruct3[size_t, CInt, CInt]
  object ZSTD_bounds:
    given _tag: Tag[ZSTD_bounds] = Tag.materializeCStruct3Tag[size_t, CInt, CInt]
    def apply()(using Zone): Ptr[ZSTD_bounds] = scala.scalanative.unsafe.alloc[ZSTD_bounds](1)
    def apply(error : size_t, lowerBound : CInt, upperBound : CInt)(using Zone): Ptr[ZSTD_bounds] = 
      val ____ptr = apply()
      (!____ptr).error = error
      (!____ptr).lowerBound = lowerBound
      (!____ptr).upperBound = upperBound
      ____ptr
    extension (struct: ZSTD_bounds)
      def error : size_t = struct._1
      def error_=(value: size_t): Unit = !struct.at1 = value
      def lowerBound : CInt = struct._2
      def lowerBound_=(value: CInt): Unit = !struct.at2 = value
      def upperBound : CInt = struct._3
      def upperBound_=(value: CInt): Unit = !struct.at3 = value

  /**
   * ************************** Streaming **************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_inBuffer = CStruct3[Ptr[Byte], size_t, size_t]
  object ZSTD_inBuffer:
    given _tag: Tag[ZSTD_inBuffer] = Tag.materializeCStruct3Tag[Ptr[Byte], size_t, size_t]
    def apply()(using Zone): Ptr[ZSTD_inBuffer] = scala.scalanative.unsafe.alloc[ZSTD_inBuffer](1)
    def apply(src : Ptr[Byte], size : size_t, pos : size_t)(using Zone): Ptr[ZSTD_inBuffer] = 
      val ____ptr = apply()
      (!____ptr).src = src
      (!____ptr).size = size
      (!____ptr).pos = pos
      ____ptr
    extension (struct: ZSTD_inBuffer)
      def src : Ptr[Byte] = struct._1
      def src_=(value: Ptr[Byte]): Unit = !struct.at1 = value
      def size : size_t = struct._2
      def size_=(value: size_t): Unit = !struct.at2 = value
      def pos : size_t = struct._3
      def pos_=(value: size_t): Unit = !struct.at3 = value

  /**
   * ************************** Streaming **************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_inBuffer_s = CStruct3[Ptr[Byte], size_t, size_t]
  object ZSTD_inBuffer_s:
    given _tag: Tag[ZSTD_inBuffer_s] = Tag.materializeCStruct3Tag[Ptr[Byte], size_t, size_t]
    def apply()(using Zone): Ptr[ZSTD_inBuffer_s] = scala.scalanative.unsafe.alloc[ZSTD_inBuffer_s](1)
    def apply(src : Ptr[Byte], size : size_t, pos : size_t)(using Zone): Ptr[ZSTD_inBuffer_s] = 
      val ____ptr = apply()
      (!____ptr).src = src
      (!____ptr).size = size
      (!____ptr).pos = pos
      ____ptr
    extension (struct: ZSTD_inBuffer_s)
      def src : Ptr[Byte] = struct._1
      def src_=(value: Ptr[Byte]): Unit = !struct.at1 = value
      def size : size_t = struct._2
      def size_=(value: size_t): Unit = !struct.at2 = value
      def pos : size_t = struct._3
      def pos_=(value: size_t): Unit = !struct.at3 = value

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_outBuffer = CStruct3[Ptr[Byte], size_t, size_t]
  object ZSTD_outBuffer:
    given _tag: Tag[ZSTD_outBuffer] = Tag.materializeCStruct3Tag[Ptr[Byte], size_t, size_t]
    def apply()(using Zone): Ptr[ZSTD_outBuffer] = scala.scalanative.unsafe.alloc[ZSTD_outBuffer](1)
    def apply(dst : Ptr[Byte], size : size_t, pos : size_t)(using Zone): Ptr[ZSTD_outBuffer] = 
      val ____ptr = apply()
      (!____ptr).dst = dst
      (!____ptr).size = size
      (!____ptr).pos = pos
      ____ptr
    extension (struct: ZSTD_outBuffer)
      def dst : Ptr[Byte] = struct._1
      def dst_=(value: Ptr[Byte]): Unit = !struct.at1 = value
      def size : size_t = struct._2
      def size_=(value: size_t): Unit = !struct.at2 = value
      def pos : size_t = struct._3
      def pos_=(value: size_t): Unit = !struct.at3 = value

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  opaque type ZSTD_outBuffer_s = CStruct3[Ptr[Byte], size_t, size_t]
  object ZSTD_outBuffer_s:
    given _tag: Tag[ZSTD_outBuffer_s] = Tag.materializeCStruct3Tag[Ptr[Byte], size_t, size_t]
    def apply()(using Zone): Ptr[ZSTD_outBuffer_s] = scala.scalanative.unsafe.alloc[ZSTD_outBuffer_s](1)
    def apply(dst : Ptr[Byte], size : size_t, pos : size_t)(using Zone): Ptr[ZSTD_outBuffer_s] = 
      val ____ptr = apply()
      (!____ptr).dst = dst
      (!____ptr).size = size
      (!____ptr).pos = pos
      ____ptr
    extension (struct: ZSTD_outBuffer_s)
      def dst : Ptr[Byte] = struct._1
      def dst_=(value: Ptr[Byte]): Unit = !struct.at1 = value
      def size : size_t = struct._2
      def size_=(value: size_t): Unit = !struct.at2 = value
      def pos : size_t = struct._3
      def pos_=(value: size_t): Unit = !struct.at3 = value


@extern
private[zstd] object extern_functions:
  import _root_.zstd.enumerations.*
  import _root_.zstd.aliases.*
  import _root_.zstd.structs.*

  /**
   * ZSTD_CCtx_loadDictionary() : Requires v1.4.0+ Create an internal CDict from `dict` buffer. Decompression will have to use same dictionary.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CCtx_loadDictionary(cctx : Ptr[ZSTD_CCtx], dict : Ptr[Byte], dictSize : size_t): size_t = extern

  /**
   * ZSTD_CCtx_refCDict() : Requires v1.4.0+ Reference a prepared dictionary, to be used for all future compressed frames. Note that compression parameters are enforced from within CDict, and supersede any compression parameter previously set within CCtx. The parameters ignored are labelled as "superseded-by-cdict" in the ZSTD_cParameter enum docs. The ignored parameters will be used again if the CCtx is returned to no-dictionary mode. The dictionary will remain valid for future compressed frames using same CCtx.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CCtx_refCDict(cctx : Ptr[ZSTD_CCtx], cdict : Ptr[ZSTD_CDict]): size_t = extern

  /**
   * ZSTD_CCtx_refPrefix() : Requires v1.4.0+ Reference a prefix (single-usage dictionary) for next compressed frame. A prefix is **only used once**. Tables are discarded at end of frame (ZSTD_e_end). Decompression will need same prefix to properly regenerate data. Compressing with a prefix is similar in outcome as performing a diff and compressing it, but performs much faster, especially during decompression (compression speed is tunable with compression level).
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CCtx_refPrefix(cctx : Ptr[ZSTD_CCtx], prefix : Ptr[Byte], prefixSize : size_t): size_t = extern

  /**
   * ZSTD_CCtx_reset() : There are 2 different things that can be reset, independently or jointly : - The session : will stop compressing current frame, and make CCtx ready to start a new one. Useful after an error, or to interrupt any ongoing compression. Any internal data not yet flushed is cancelled. Compression parameters and dictionary remain unchanged. They will be used to compress next frame. Resetting session never fails. - The parameters : changes all parameters back to "default". This also removes any reference to any dictionary or external sequence producer. Parameters can only be changed between 2 sessions (i.e. no compression is currently ongoing) otherwise the reset fails, and function returns an error value (which can be tested using ZSTD_isError()) - Both : similar to resetting the session, followed by resetting parameters.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CCtx_reset(cctx : Ptr[ZSTD_CCtx], reset : ZSTD_ResetDirective): size_t = extern

  /**
   * ZSTD_CCtx_setParameter() : Set one compression parameter, selected by enum ZSTD_cParameter. All parameters have valid bounds. Bounds can be queried using ZSTD_cParam_getBounds(). Providing a value beyond bound will either clamp it, or trigger an error (depending on parameter). Setting a parameter is generally only possible during frame initialization (before starting compression). Exception : when using multi-threading mode (nbWorkers >= 1), the following parameters can be updated _during_ compression (within same frame): => compressionLevel, hashLog, chainLog, searchLog, minMatch, targetLength and strategy. new parameters will be active for next job only (after a flush()).
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CCtx_setParameter(cctx : Ptr[ZSTD_CCtx], param : ZSTD_cParameter, value : CInt): size_t = extern

  /**
   * ZSTD_CCtx_setPledgedSrcSize() : Total input data size to be compressed as a single frame. Value will be written in frame header, unless if explicitly forbidden using ZSTD_c_contentSizeFlag. This value will also be controlled at end of frame, and trigger an error if not respected.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CCtx_setPledgedSrcSize(cctx : Ptr[ZSTD_CCtx], pledgedSrcSize : CUnsignedLongLong): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CStreamInSize(): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_CStreamOutSize(): size_t = extern

  /**
   * ZSTD_DCtx_loadDictionary() : Requires v1.4.0+ Create an internal DDict from dict buffer, to be used to decompress all future frames. The dictionary remains valid for all future frames, until explicitly invalidated, or a new dictionary is loaded.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_DCtx_loadDictionary(dctx : Ptr[ZSTD_DCtx], dict : Ptr[Byte], dictSize : size_t): size_t = extern

  /**
   * ZSTD_DCtx_refDDict() : Requires v1.4.0+ Reference a prepared dictionary, to be used to decompress next frames. The dictionary remains active for decompression of future frames using same DCtx.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_DCtx_refDDict(dctx : Ptr[ZSTD_DCtx], ddict : Ptr[ZSTD_DDict]): size_t = extern

  /**
   * ZSTD_DCtx_refPrefix() : Requires v1.4.0+ Reference a prefix (single-usage dictionary) to decompress next frame. This is the reverse operation of ZSTD_CCtx_refPrefix(), and must use the same prefix as the one used during compression. Prefix is **only used once**. Reference is discarded at end of frame. End of frame is reached when ZSTD_decompressStream() returns 0.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_DCtx_refPrefix(dctx : Ptr[ZSTD_DCtx], prefix : Ptr[Byte], prefixSize : size_t): size_t = extern

  /**
   * ZSTD_DCtx_reset() : Return a DCtx to clean state. Session and parameters can be reset jointly or separately. Parameters can only be reset when no active frame is being decompressed.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_DCtx_reset(dctx : Ptr[ZSTD_DCtx], reset : ZSTD_ResetDirective): size_t = extern

  /**
   * ZSTD_DCtx_setParameter() : Set one compression parameter, selected by enum ZSTD_dParameter. All parameters have valid bounds. Bounds can be queried using ZSTD_dParam_getBounds(). Providing a value beyond bound will either clamp it, or trigger an error (depending on parameter). Setting a parameter is only possible during frame initialization (before starting decompression).
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_DCtx_setParameter(dctx : Ptr[ZSTD_DCtx], param : ZSTD_dParameter, value : CInt): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_DStreamInSize(): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_DStreamOutSize(): size_t = extern

  /**
   * ************************************* Simple API *************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compress(dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t, compressionLevel : CInt): size_t = extern

  /**
   * ZSTD_compress2() : Behave the same as ZSTD_compressCCtx(), but compression parameters are set using the advanced API. ZSTD_compress2() always starts a new frame. Should cctx hold data from a previously unfinished frame, everything about it is forgotten. - Compression parameters are pushed into CCtx before starting compression, using ZSTD_CCtx_set*() - The function is always blocking, returns when compression is completed. Hint : compression runs faster if `dstCapacity` >= `ZSTD_compressBound(srcSize)`.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compress2(cctx : Ptr[ZSTD_CCtx], dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compressBound(srcSize : size_t): size_t = extern

  /**
   * ZSTD_compressCCtx() : Same as ZSTD_compress(), using an explicit ZSTD_CCtx. Important : in order to behave similarly to `ZSTD_compress()`, this function compresses at requested compression level, __ignoring any other parameter__ . If any advanced parameter was set using the advanced API, they will all be reset. Only `compressionLevel` remains.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compressCCtx(cctx : Ptr[ZSTD_CCtx], dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t, compressionLevel : CInt): size_t = extern

  /**
   * Alternative for ZSTD_compressStream2(zcs, output, input, ZSTD_e_continue). NOTE: The return value is different. ZSTD_compressStream() returns a hint for the next read size (if non-zero and not an error). ZSTD_compressStream2() returns the minimum nb of bytes left to flush (if non-zero and not an error).
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compressStream(zcs : Ptr[ZSTD_CStream], output : Ptr[ZSTD_outBuffer], input : Ptr[ZSTD_inBuffer]): size_t = extern

  /**
   * ZSTD_compressStream2() : Requires v1.4.0+ Behaves about the same as ZSTD_compressStream, with additional control on end directive. - Compression parameters are pushed into CCtx before starting compression, using ZSTD_CCtx_set*() - Compression parameters cannot be changed once compression is started (save a list of exceptions in multi-threading mode) - output->pos must be <= dstCapacity, input->pos must be <= srcSize - output->pos and input->pos will be updated. They are guaranteed to remain below their respective limit. - endOp must be a valid directive - When nbWorkers==0 (default), function is blocking : it completes its job before returning to caller. - When nbWorkers>=1, function is non-blocking : it copies a portion of input, distributes jobs to internal worker threads, flush to output whatever is available, and then immediately returns, just indicating that there is some data remaining to be flushed. The function nonetheless guarantees forward progress : it will return only after it reads or write at least 1+ byte. - Exception : if the first call requests a ZSTD_e_end directive and provides enough dstCapacity, the function delegates to ZSTD_compress2() which is always blocking. -
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compressStream2(cctx : Ptr[ZSTD_CCtx], output : Ptr[ZSTD_outBuffer], input : Ptr[ZSTD_inBuffer], endOp : ZSTD_EndDirective): size_t = extern

  /**
   * ZSTD_compress_usingCDict() : Compression using a digested Dictionary. Recommended when same dictionary is used multiple times. Note : compression level is _decided at dictionary creation time_, and frame parameters are hardcoded (dictID=yes, contentSize=yes, checksum=no)
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compress_usingCDict(cctx : Ptr[ZSTD_CCtx], dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t, cdict : Ptr[ZSTD_CDict]): size_t = extern

  /**
   * ************************ Simple dictionary API *************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_compress_usingDict(ctx : Ptr[ZSTD_CCtx], dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t, dict : Ptr[Byte], dictSize : size_t, compressionLevel : CInt): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_createCCtx(): Ptr[ZSTD_CCtx] = extern

  /**
   * ZSTD_createCDict() : When compressing multiple messages or blocks using the same dictionary, it's recommended to digest the dictionary only once, since it's a costly operation. ZSTD_createCDict() will create a state from digesting a dictionary. The resulting state can be used for future compression operations with very limited startup cost. ZSTD_CDict can be created once and shared by multiple threads concurrently, since its usage is read-only. can be released after ZSTD_CDict creation, because its content is copied within CDict. Note 1 : Consider experimental function `ZSTD_createCDict_byReference()` if you prefer to not duplicate content. Note 2 : A ZSTD_CDict can be created from an empty , in which case the only thing that it transports is the . This can be useful in a pipeline featuring ZSTD_compress_usingCDict() exclusively, expecting a ZSTD_CDict parameter with any data, including those without a known dictionary.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_createCDict(dictBuffer : Ptr[Byte], dictSize : size_t, compressionLevel : CInt): Ptr[ZSTD_CDict] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_createCStream(): Ptr[ZSTD_CStream] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_createDCtx(): Ptr[ZSTD_DCtx] = extern

  /**
   * ZSTD_createDDict() : Create a digested dictionary, ready to start decompression operation without startup delay. dictBuffer can be released after DDict creation, as its content is copied inside DDict.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_createDDict(dictBuffer : Ptr[Byte], dictSize : size_t): Ptr[ZSTD_DDict] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_createDStream(): Ptr[ZSTD_DStream] = extern

  /**
   * ZSTD_decompress() : `compressedSize` : must be the _exact_ size of some number of compressed and/or skippable frames. `dstCapacity` is an upper bound of originalSize to regenerate. If user cannot imply a maximum upper bound, it's better to use streaming mode to decompress data.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_decompress(dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], compressedSize : size_t): size_t = extern

  /**
   * ZSTD_decompressDCtx() : Same as ZSTD_decompress(), requires an allocated ZSTD_DCtx. Compatible with sticky parameters.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_decompressDCtx(dctx : Ptr[ZSTD_DCtx], dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t): size_t = extern

  /**
   * ZSTD_decompressStream() : Streaming decompression function. Call repetitively to consume full input updating it as necessary. Function will update both input and output `pos` fields exposing current state via these fields: - `input.pos < input.size`, some input remaining and caller should provide remaining input on the next call. - `output.pos < output.size`, decoder finished and flushed all remaining buffers. - `output.pos == output.size`, potentially uncflushed data present in the internal buffers, call ZSTD_decompressStream() again to flush remaining data to output. Note : with no additional input, amount of data flushed <= ZSTD_BLOCKSIZE_MAX.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_decompressStream(zds : Ptr[ZSTD_DStream], output : Ptr[ZSTD_outBuffer], input : Ptr[ZSTD_inBuffer]): size_t = extern

  /**
   * ZSTD_decompress_usingDDict() : Decompression using a digested Dictionary. Recommended when same dictionary is used multiple times.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_decompress_usingDDict(dctx : Ptr[ZSTD_DCtx], dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t, ddict : Ptr[ZSTD_DDict]): size_t = extern

  /**
   * ZSTD_decompress_usingDict() : Decompression using a known Dictionary. Dictionary must be identical to the one used during compression. Note : This function loads the dictionary, resulting in significant startup delay. It's intended for a dictionary used only once. Note : When `dict == NULL || dictSize < 8` no dictionary is used.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_decompress_usingDict(dctx : Ptr[ZSTD_DCtx], dst : Ptr[Byte], dstCapacity : size_t, src : Ptr[Byte], srcSize : size_t, dict : Ptr[Byte], dictSize : size_t): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_defaultCLevel(): CInt = extern

  /**
   * Equivalent to ZSTD_compressStream2(zcs, output, &emptyInput, ZSTD_e_end).
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_endStream(zcs : Ptr[ZSTD_CStream], output : Ptr[ZSTD_outBuffer]): size_t = extern

  /**
   * ZSTD_findFrameCompressedSize() : Requires v1.4.0+ `src` should point to the start of a ZSTD frame or skippable frame. `srcSize` must be >= first frame size
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_findFrameCompressedSize(src : Ptr[Byte], srcSize : size_t): size_t = extern

  /**
   * Equivalent to ZSTD_compressStream2(zcs, output, &emptyInput, ZSTD_e_flush).
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_flushStream(zcs : Ptr[ZSTD_CStream], output : Ptr[ZSTD_outBuffer]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_freeCCtx(cctx : Ptr[ZSTD_CCtx]): size_t = extern

  /**
   * ZSTD_freeCDict() : Function frees memory allocated by ZSTD_createCDict(). If a NULL pointer is passed, no operation is performed.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_freeCDict(CDict : Ptr[ZSTD_CDict]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_freeCStream(zcs : Ptr[ZSTD_CStream]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_freeDCtx(dctx : Ptr[ZSTD_DCtx]): size_t = extern

  /**
   * ZSTD_freeDDict() : Function frees memory allocated with ZSTD_createDDict() If a NULL pointer is passed, no operation is performed.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_freeDDict(ddict : Ptr[ZSTD_DDict]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_freeDStream(zds : Ptr[ZSTD_DStream]): size_t = extern

  /**
   * ZSTD_getDecompressedSize() : NOTE: This function is now obsolete, in favor of ZSTD_getFrameContentSize(). Both functions work the same way, but ZSTD_getDecompressedSize() blends "empty", "unknown" and "error" results to the same return value (0), while ZSTD_getFrameContentSize() gives them separate return values.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_getDecompressedSize(src : Ptr[Byte], srcSize : size_t): CUnsignedLongLong = extern

  /**
   * ZSTD_getDictID_fromCDict() : Requires v1.5.0+ Provides the dictID of the dictionary loaded into `cdict`. If
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_getDictID_fromCDict(cdict : Ptr[ZSTD_CDict]): CUnsignedInt = extern

  /**
   * ZSTD_getDictID_fromDDict() : Requires v1.4.0+ Provides the dictID of the dictionary loaded into `ddict`. If
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_getDictID_fromDDict(ddict : Ptr[ZSTD_DDict]): CUnsignedInt = extern

  /**
   * ZSTD_getDictID_fromDict() : Requires v1.4.0+ Provides the dictID stored within dictionary. if
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_getDictID_fromDict(dict : Ptr[Byte], dictSize : size_t): CUnsignedInt = extern

  /**
   * ZSTD_getDictID_fromFrame() : Requires v1.4.0+ Provides the dictID required to decompressed the frame stored within `src`. If
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_getDictID_fromFrame(src : Ptr[Byte], srcSize : size_t): CUnsignedInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_getErrorName(code : size_t): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_getFrameContentSize(src : Ptr[Byte], srcSize : size_t): CUnsignedLongLong = extern

  /**
   * Equivalent to:
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_initCStream(zcs : Ptr[ZSTD_CStream], compressionLevel : CInt): size_t = extern

  /**
   * ZSTD_initDStream() : Initialize/reset DStream state for new decompression operation. Call before new decompression operation using same DStream.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_initDStream(zds : Ptr[ZSTD_DStream]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_isError(code : size_t): CUnsignedInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_maxCLevel(): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_minCLevel(): CInt = extern

  /**
   * ZSTD_sizeof_*() : Requires v1.4.0+ These functions give the _current_ memory usage of selected object. Note that object memory usage can evolve (increase or decrease) over time.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_sizeof_CCtx(cctx : Ptr[ZSTD_CCtx]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_sizeof_CDict(cdict : Ptr[ZSTD_CDict]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_sizeof_CStream(zcs : Ptr[ZSTD_CStream]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_sizeof_DCtx(dctx : Ptr[ZSTD_DCtx]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_sizeof_DDict(ddict : Ptr[ZSTD_DDict]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_sizeof_DStream(zds : Ptr[ZSTD_DStream]): size_t = extern

  /**
   * ZSTD_versionNumber() : Return runtime library version, the value is (MAJOR*100*100 + MINOR*100 + RELEASE).
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_versionNumber(): CUnsignedInt = extern

  /**
   * ZSTD_versionString() : Return runtime library version, like "1.4.5". Requires v1.3.0+.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_versionString(): CString = extern

  private[zstd] def __sn_wrap_zstd_ZSTD_cParam_getBounds(cParam : ZSTD_cParameter, __return : Ptr[ZSTD_bounds]): Unit = extern

  private[zstd] def __sn_wrap_zstd_ZSTD_dParam_getBounds(dParam : ZSTD_dParameter, __return : Ptr[ZSTD_bounds]): Unit = extern


object functions:
  import _root_.zstd.enumerations.*
  import _root_.zstd.aliases.*
  import _root_.zstd.structs.*

  import extern_functions.*
  export extern_functions.*

  /**
   * ZSTD_cParam_getBounds() : All parameters must belong to an interval with lower and upper bounds, otherwise they will either trigger an error or be automatically clamped.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_cParam_getBounds(cParam : ZSTD_cParameter)(using Zone): ZSTD_bounds = 
    val __ptr_0: Ptr[ZSTD_bounds] = alloc[ZSTD_bounds](1)
    __sn_wrap_zstd_ZSTD_cParam_getBounds(cParam, (__ptr_0 + 0))
    !(__ptr_0 + 0)

  /**
   * ZSTD_cParam_getBounds() : All parameters must belong to an interval with lower and upper bounds, otherwise they will either trigger an error or be automatically clamped.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_cParam_getBounds(cParam : ZSTD_cParameter)(__return : Ptr[ZSTD_bounds]): Unit = 
    __sn_wrap_zstd_ZSTD_cParam_getBounds(cParam, __return)

  /**
   * ZSTD_dParam_getBounds() : All parameters must belong to an interval with lower and upper bounds, otherwise they will either trigger an error or be automatically clamped.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_dParam_getBounds(dParam : ZSTD_dParameter)(__return : Ptr[ZSTD_bounds]): Unit = 
    __sn_wrap_zstd_ZSTD_dParam_getBounds(dParam, __return)

  /**
   * ZSTD_dParam_getBounds() : All parameters must belong to an interval with lower and upper bounds, otherwise they will either trigger an error or be automatically clamped.
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zstd_arm64-osx/include/zstd.h
  */
  def ZSTD_dParam_getBounds(dParam : ZSTD_dParameter)(using Zone): ZSTD_bounds = 
    val __ptr_0: Ptr[ZSTD_bounds] = alloc[ZSTD_bounds](1)
    __sn_wrap_zstd_ZSTD_dParam_getBounds(dParam, (__ptr_0 + 0))
    !(__ptr_0 + 0)

object types:
  export _root_.zstd.structs.*
  export _root_.zstd.aliases.*
  export _root_.zstd.enumerations.*

object all:
  export _root_.zstd.enumerations.ZSTD_EndDirective
  export _root_.zstd.enumerations.ZSTD_ResetDirective
  export _root_.zstd.enumerations.ZSTD_cParameter
  export _root_.zstd.enumerations.ZSTD_dParameter
  export _root_.zstd.enumerations.ZSTD_strategy
  export _root_.zstd.aliases.ZSTD_CStream
  export _root_.zstd.aliases.ZSTD_DStream
  export _root_.zstd.aliases.max_align_t
  export _root_.zstd.aliases.ptrdiff_t
  export _root_.zstd.aliases.rsize_t
  export _root_.zstd.aliases.size_t
  export _root_.zstd.aliases.wchar_t
  export _root_.zstd.structs.ZSTD_CCtx
  export _root_.zstd.structs.ZSTD_CCtx_s
  export _root_.zstd.structs.ZSTD_CDict
  export _root_.zstd.structs.ZSTD_CDict_s
  export _root_.zstd.structs.ZSTD_DCtx
  export _root_.zstd.structs.ZSTD_DCtx_s
  export _root_.zstd.structs.ZSTD_DDict
  export _root_.zstd.structs.ZSTD_DDict_s
  export _root_.zstd.structs.ZSTD_bounds
  export _root_.zstd.structs.ZSTD_inBuffer
  export _root_.zstd.structs.ZSTD_inBuffer_s
  export _root_.zstd.structs.ZSTD_outBuffer
  export _root_.zstd.structs.ZSTD_outBuffer_s
  export _root_.zstd.functions.ZSTD_CCtx_loadDictionary
  export _root_.zstd.functions.ZSTD_CCtx_refCDict
  export _root_.zstd.functions.ZSTD_CCtx_refPrefix
  export _root_.zstd.functions.ZSTD_CCtx_reset
  export _root_.zstd.functions.ZSTD_CCtx_setParameter
  export _root_.zstd.functions.ZSTD_CCtx_setPledgedSrcSize
  export _root_.zstd.functions.ZSTD_CStreamInSize
  export _root_.zstd.functions.ZSTD_CStreamOutSize
  export _root_.zstd.functions.ZSTD_DCtx_loadDictionary
  export _root_.zstd.functions.ZSTD_DCtx_refDDict
  export _root_.zstd.functions.ZSTD_DCtx_refPrefix
  export _root_.zstd.functions.ZSTD_DCtx_reset
  export _root_.zstd.functions.ZSTD_DCtx_setParameter
  export _root_.zstd.functions.ZSTD_DStreamInSize
  export _root_.zstd.functions.ZSTD_DStreamOutSize
  export _root_.zstd.functions.ZSTD_compress
  export _root_.zstd.functions.ZSTD_compress2
  export _root_.zstd.functions.ZSTD_compressBound
  export _root_.zstd.functions.ZSTD_compressCCtx
  export _root_.zstd.functions.ZSTD_compressStream
  export _root_.zstd.functions.ZSTD_compressStream2
  export _root_.zstd.functions.ZSTD_compress_usingCDict
  export _root_.zstd.functions.ZSTD_compress_usingDict
  export _root_.zstd.functions.ZSTD_createCCtx
  export _root_.zstd.functions.ZSTD_createCDict
  export _root_.zstd.functions.ZSTD_createCStream
  export _root_.zstd.functions.ZSTD_createDCtx
  export _root_.zstd.functions.ZSTD_createDDict
  export _root_.zstd.functions.ZSTD_createDStream
  export _root_.zstd.functions.ZSTD_decompress
  export _root_.zstd.functions.ZSTD_decompressDCtx
  export _root_.zstd.functions.ZSTD_decompressStream
  export _root_.zstd.functions.ZSTD_decompress_usingDDict
  export _root_.zstd.functions.ZSTD_decompress_usingDict
  export _root_.zstd.functions.ZSTD_defaultCLevel
  export _root_.zstd.functions.ZSTD_endStream
  export _root_.zstd.functions.ZSTD_findFrameCompressedSize
  export _root_.zstd.functions.ZSTD_flushStream
  export _root_.zstd.functions.ZSTD_freeCCtx
  export _root_.zstd.functions.ZSTD_freeCDict
  export _root_.zstd.functions.ZSTD_freeCStream
  export _root_.zstd.functions.ZSTD_freeDCtx
  export _root_.zstd.functions.ZSTD_freeDDict
  export _root_.zstd.functions.ZSTD_freeDStream
  export _root_.zstd.functions.ZSTD_getDecompressedSize
  export _root_.zstd.functions.ZSTD_getDictID_fromCDict
  export _root_.zstd.functions.ZSTD_getDictID_fromDDict
  export _root_.zstd.functions.ZSTD_getDictID_fromDict
  export _root_.zstd.functions.ZSTD_getDictID_fromFrame
  export _root_.zstd.functions.ZSTD_getErrorName
  export _root_.zstd.functions.ZSTD_getFrameContentSize
  export _root_.zstd.functions.ZSTD_initCStream
  export _root_.zstd.functions.ZSTD_initDStream
  export _root_.zstd.functions.ZSTD_isError
  export _root_.zstd.functions.ZSTD_maxCLevel
  export _root_.zstd.functions.ZSTD_minCLevel
  export _root_.zstd.functions.ZSTD_sizeof_CCtx
  export _root_.zstd.functions.ZSTD_sizeof_CDict
  export _root_.zstd.functions.ZSTD_sizeof_CStream
  export _root_.zstd.functions.ZSTD_sizeof_DCtx
  export _root_.zstd.functions.ZSTD_sizeof_DDict
  export _root_.zstd.functions.ZSTD_sizeof_DStream
  export _root_.zstd.functions.ZSTD_versionNumber
  export _root_.zstd.functions.ZSTD_versionString
  export _root_.zstd.functions.ZSTD_cParam_getBounds
  export _root_.zstd.functions.ZSTD_dParam_getBounds
