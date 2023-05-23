package czmq

import _root_.scala.scalanative.unsafe.*
import _root_.scala.scalanative.unsigned.*
import _root_.scala.scalanative.libc.*
import _root_.scala.scalanative.*

object aliases:
  import _root_.czmq.aliases.*
  import _root_.czmq.structs.*

  type FILE = libc.stdio.FILE
  object FILE: 
    val _tag: Tag[FILE] = summon[Tag[libc.stdio.FILE]]
    inline def apply(inline o: libc.stdio.FILE): FILE = o
    extension (v: FILE)
      inline def value: libc.stdio.FILE = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  opaque type SOCKET = CInt
  object SOCKET: 
    given _tag: Tag[SOCKET] = Tag.Int
    inline def apply(inline o: CInt): SOCKET = o
    extension (v: SOCKET)
      inline def value: CInt = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/SDKs/MacOSX.sdk/usr/include/arm/_types.h
  */
  opaque type __uint16_t = CUnsignedShort
  object __uint16_t: 
    given _tag: Tag[__uint16_t] = Tag.UShort
    inline def apply(inline o: CUnsignedShort): __uint16_t = o
    extension (v: __uint16_t)
      inline def value: CUnsignedShort = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/SDKs/MacOSX.sdk/usr/include/arm/_types.h
  */
  opaque type __uint32_t = CUnsignedInt
  object __uint32_t: 
    given _tag: Tag[__uint32_t] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): __uint32_t = o
    extension (v: __uint32_t)
      inline def value: CUnsignedInt = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/SDKs/MacOSX.sdk/usr/include/arm/_types.h
  */
  opaque type __uint8_t = CUnsignedChar
  object __uint8_t: 
    given _tag: Tag[__uint8_t] = Tag.UByte
    inline def apply(inline o: CUnsignedChar): __uint8_t = o
    extension (v: __uint8_t)
      inline def value: CUnsignedChar = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  opaque type byte = CUnsignedChar
  object byte: 
    given _tag: Tag[byte] = Tag.UByte
    inline def apply(inline o: CUnsignedChar): byte = o
    extension (v: byte)
      inline def value: CUnsignedChar = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq.h
  */
  type czmq_comparator = CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
  object czmq_comparator: 
    given _tag: Tag[czmq_comparator] = Tag.materializeCFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
    inline def apply(inline o: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]): czmq_comparator = o
    extension (v: czmq_comparator)
      inline def value: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq.h
  */
  type czmq_destructor = CFuncPtr1[Ptr[Ptr[Byte]], Unit]
  object czmq_destructor: 
    given _tag: Tag[czmq_destructor] = Tag.materializeCFuncPtr1[Ptr[Ptr[Byte]], Unit]
    inline def apply(inline o: CFuncPtr1[Ptr[Ptr[Byte]], Unit]): czmq_destructor = o
    extension (v: czmq_destructor)
      inline def value: CFuncPtr1[Ptr[Ptr[Byte]], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq.h
  */
  type czmq_duplicator = CFuncPtr1[Ptr[Byte], Ptr[Byte]]
  object czmq_duplicator: 
    given _tag: Tag[czmq_duplicator] = Tag.materializeCFuncPtr1[Ptr[Byte], Ptr[Byte]]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], Ptr[Byte]]): czmq_duplicator = o
    extension (v: czmq_duplicator)
      inline def value: CFuncPtr1[Ptr[Byte], Ptr[Byte]] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  opaque type dbyte = CUnsignedShort
  object dbyte: 
    given _tag: Tag[dbyte] = Tag.UShort
    inline def apply(inline o: CUnsignedShort): dbyte = o
    extension (v: dbyte)
      inline def value: CUnsignedShort = v

  type in6_addr = posix.netinet.in.in6_addr
  object in6_addr: 
    val _tag: Tag[in6_addr] = summon[Tag[posix.netinet.in.in6_addr]]
    inline def apply(inline o: posix.netinet.in.in6_addr): in6_addr = o
    extension (v: in6_addr)
      inline def value: posix.netinet.in.in6_addr = v

  type in_addr = posix.netinet.in.in_addr
  object in_addr: 
    val _tag: Tag[in_addr] = summon[Tag[posix.netinet.in.in_addr]]
    inline def apply(inline o: posix.netinet.in.in_addr): in_addr = o
    extension (v: in_addr)
      inline def value: posix.netinet.in.in_addr = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/SDKs/MacOSX.sdk/usr/include/sys/_types/_in_port_t.h
  */
  type in_port_t = __uint16_t
  object in_port_t: 
    given _tag: Tag[in_port_t] = __uint16_t._tag
    inline def apply(inline o: __uint16_t): in_port_t = o
    extension (v: in_port_t)
      inline def value: __uint16_t = v

  type int64_t = scala.Long
  object int64_t: 
    val _tag: Tag[int64_t] = summon[Tag[scala.Long]]
    inline def apply(inline o: scala.Long): int64_t = o
    extension (v: int64_t)
      inline def value: scala.Long = v

  type iovec = posix.sys.uio.iovec
  object iovec: 
    val _tag: Tag[iovec] = summon[Tag[posix.sys.uio.iovec]]
    inline def apply(inline o: posix.sys.uio.iovec): iovec = o
    extension (v: iovec)
      inline def value: posix.sys.uio.iovec = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/__stddef_max_align_t.h
  */
  opaque type max_align_t = Double
  object max_align_t: 
    given _tag: Tag[max_align_t] = Tag.Double
    inline def apply(inline o: Double): max_align_t = o
    extension (v: max_align_t)
      inline def value: Double = v

  type mode_t = posix.sys.types.mode_t
  object mode_t: 
    val _tag: Tag[mode_t] = summon[Tag[posix.sys.types.mode_t]]
    inline def apply(inline o: posix.sys.types.mode_t): mode_t = o
    extension (v: mode_t)
      inline def value: posix.sys.types.mode_t = v

  type off_t = posix.unistd.off_t
  object off_t: 
    val _tag: Tag[off_t] = summon[Tag[posix.unistd.off_t]]
    inline def apply(inline o: posix.unistd.off_t): off_t = o
    extension (v: off_t)
      inline def value: posix.unistd.off_t = v

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
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  opaque type qbyte = CUnsignedInt
  object qbyte: 
    given _tag: Tag[qbyte] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): qbyte = o
    extension (v: qbyte)
      inline def value: CUnsignedInt = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/stddef.h
  */
  opaque type rsize_t = CUnsignedLongInt
  object rsize_t: 
    given _tag: Tag[rsize_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): rsize_t = o
    extension (v: rsize_t)
      inline def value: CUnsignedLongInt = v

  type sa_family_t = posix.sys.socket.sa_family_t
  object sa_family_t: 
    val _tag: Tag[sa_family_t] = summon[Tag[posix.sys.socket.sa_family_t]]
    inline def apply(inline o: posix.sys.socket.sa_family_t): sa_family_t = o
    extension (v: sa_family_t)
      inline def value: posix.sys.socket.sa_family_t = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/stddef.h
  */
  opaque type size_t = CUnsignedLongInt
  object size_t: 
    given _tag: Tag[size_t] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): size_t = o
    extension (v: size_t)
      inline def value: CUnsignedLongInt = v

  type ssize_t = posix.sys.types.ssize_t
  object ssize_t: 
    val _tag: Tag[ssize_t] = summon[Tag[posix.sys.types.ssize_t]]
    inline def apply(inline o: posix.sys.types.ssize_t): ssize_t = o
    extension (v: ssize_t)
      inline def value: posix.sys.types.ssize_t = v

  type time_t = posix.sys.types.time_t
  object time_t: 
    val _tag: Tag[time_t] = summon[Tag[posix.sys.types.time_t]]
    inline def apply(inline o: posix.sys.types.time_t): time_t = o
    extension (v: time_t)
      inline def value: posix.sys.types.time_t = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  opaque type uint = CUnsignedInt
  object uint: 
    given _tag: Tag[uint] = Tag.UInt
    inline def apply(inline o: CUnsignedInt): uint = o
    extension (v: uint)
      inline def value: CUnsignedInt = v

  type uint8_t = scala.scalanative.unsigned.UByte
  object uint8_t: 
    val _tag: Tag[uint8_t] = summon[Tag[scala.scalanative.unsigned.UByte]]
    inline def apply(inline o: scala.scalanative.unsigned.UByte): uint8_t = o
    extension (v: uint8_t)
      inline def value: scala.scalanative.unsigned.UByte = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  opaque type ulong = CUnsignedLongInt
  object ulong: 
    given _tag: Tag[ulong] = Tag.ULong
    inline def apply(inline o: CUnsignedLongInt): ulong = o
    extension (v: ulong)
      inline def value: CUnsignedLongInt = v

  type va_list = unsafe.CVarArgList
  object va_list: 
    val _tag: Tag[va_list] = summon[Tag[unsafe.CVarArgList]]
    inline def apply(inline o: unsafe.CVarArgList): va_list = o
    extension (v: va_list)
      inline def value: unsafe.CVarArgList = v

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/usr/lib/clang/14.0.3/include/stddef.h
  */
  opaque type wchar_t = CInt
  object wchar_t: 
    given _tag: Tag[wchar_t] = Tag.Int
    inline def apply(inline o: CInt): wchar_t = o
    extension (v: wchar_t)
      inline def value: CInt = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  type zactor_fn = CFuncPtr2[Ptr[zsock_t], Ptr[Byte], Unit]
  object zactor_fn: 
    given _tag: Tag[zactor_fn] = Tag.materializeCFuncPtr2[Ptr[zsock_t], Ptr[Byte], Unit]
    inline def apply(inline o: CFuncPtr2[Ptr[zsock_t], Ptr[Byte], Unit]): zactor_fn = o
    extension (v: zactor_fn)
      inline def value: CFuncPtr2[Ptr[zsock_t], Ptr[Byte], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  type zconfig_fct = CFuncPtr3[Ptr[zconfig_t], Ptr[Byte], CInt, CInt]
  object zconfig_fct: 
    given _tag: Tag[zconfig_fct] = Tag.materializeCFuncPtr3[Ptr[zconfig_t], Ptr[Byte], CInt, CInt]
    inline def apply(inline o: CFuncPtr3[Ptr[zconfig_t], Ptr[Byte], CInt, CInt]): zconfig_fct = o
    extension (v: zconfig_fct)
      inline def value: CFuncPtr3[Ptr[zconfig_t], Ptr[Byte], CInt, CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  type zhash_free_fn = CFuncPtr1[Ptr[Byte], Unit]
  object zhash_free_fn: 
    given _tag: Tag[zhash_free_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], Unit]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], Unit]): zhash_free_fn = o
    extension (v: zhash_free_fn)
      inline def value: CFuncPtr1[Ptr[Byte], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  type zhashx_comparator_fn = CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
  object zhashx_comparator_fn: 
    given _tag: Tag[zhashx_comparator_fn] = Tag.materializeCFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
    inline def apply(inline o: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]): zhashx_comparator_fn = o
    extension (v: zhashx_comparator_fn)
      inline def value: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  type zhashx_deserializer_fn = CFuncPtr1[CString, Ptr[Byte]]
  object zhashx_deserializer_fn: 
    given _tag: Tag[zhashx_deserializer_fn] = Tag.materializeCFuncPtr1[CString, Ptr[Byte]]
    inline def apply(inline o: CFuncPtr1[CString, Ptr[Byte]]): zhashx_deserializer_fn = o
    extension (v: zhashx_deserializer_fn)
      inline def value: CFuncPtr1[CString, Ptr[Byte]] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  type zhashx_destructor_fn = CFuncPtr1[Ptr[Ptr[Byte]], Unit]
  object zhashx_destructor_fn: 
    given _tag: Tag[zhashx_destructor_fn] = Tag.materializeCFuncPtr1[Ptr[Ptr[Byte]], Unit]
    inline def apply(inline o: CFuncPtr1[Ptr[Ptr[Byte]], Unit]): zhashx_destructor_fn = o
    extension (v: zhashx_destructor_fn)
      inline def value: CFuncPtr1[Ptr[Ptr[Byte]], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  type zhashx_duplicator_fn = CFuncPtr1[Ptr[Byte], Ptr[Byte]]
  object zhashx_duplicator_fn: 
    given _tag: Tag[zhashx_duplicator_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], Ptr[Byte]]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], Ptr[Byte]]): zhashx_duplicator_fn = o
    extension (v: zhashx_duplicator_fn)
      inline def value: CFuncPtr1[Ptr[Byte], Ptr[Byte]] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  type zhashx_free_fn = CFuncPtr1[Ptr[Byte], Unit]
  object zhashx_free_fn: 
    given _tag: Tag[zhashx_free_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], Unit]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], Unit]): zhashx_free_fn = o
    extension (v: zhashx_free_fn)
      inline def value: CFuncPtr1[Ptr[Byte], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  type zhashx_hash_fn = CFuncPtr1[Ptr[Byte], size_t]
  object zhashx_hash_fn: 
    given _tag: Tag[zhashx_hash_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], size_t]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], size_t]): zhashx_hash_fn = o
    extension (v: zhashx_hash_fn)
      inline def value: CFuncPtr1[Ptr[Byte], size_t] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  type zhashx_serializer_fn = CFuncPtr1[Ptr[Byte], CString]
  object zhashx_serializer_fn: 
    given _tag: Tag[zhashx_serializer_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], CString]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], CString]): zhashx_serializer_fn = o
    extension (v: zhashx_serializer_fn)
      inline def value: CFuncPtr1[Ptr[Byte], CString] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  type zlist_compare_fn = CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
  object zlist_compare_fn: 
    given _tag: Tag[zlist_compare_fn] = Tag.materializeCFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
    inline def apply(inline o: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]): zlist_compare_fn = o
    extension (v: zlist_compare_fn)
      inline def value: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  type zlist_free_fn = CFuncPtr1[Ptr[Byte], Unit]
  object zlist_free_fn: 
    given _tag: Tag[zlist_free_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], Unit]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], Unit]): zlist_free_fn = o
    extension (v: zlist_free_fn)
      inline def value: CFuncPtr1[Ptr[Byte], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  type zlistx_comparator_fn = CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
  object zlistx_comparator_fn: 
    given _tag: Tag[zlistx_comparator_fn] = Tag.materializeCFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]
    inline def apply(inline o: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt]): zlistx_comparator_fn = o
    extension (v: zlistx_comparator_fn)
      inline def value: CFuncPtr2[Ptr[Byte], Ptr[Byte], CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  type zlistx_destructor_fn = CFuncPtr1[Ptr[Ptr[Byte]], Unit]
  object zlistx_destructor_fn: 
    given _tag: Tag[zlistx_destructor_fn] = Tag.materializeCFuncPtr1[Ptr[Ptr[Byte]], Unit]
    inline def apply(inline o: CFuncPtr1[Ptr[Ptr[Byte]], Unit]): zlistx_destructor_fn = o
    extension (v: zlistx_destructor_fn)
      inline def value: CFuncPtr1[Ptr[Ptr[Byte]], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  type zlistx_duplicator_fn = CFuncPtr1[Ptr[Byte], Ptr[Byte]]
  object zlistx_duplicator_fn: 
    given _tag: Tag[zlistx_duplicator_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], Ptr[Byte]]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], Ptr[Byte]]): zlistx_duplicator_fn = o
    extension (v: zlistx_duplicator_fn)
      inline def value: CFuncPtr1[Ptr[Byte], Ptr[Byte]] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  type zloop_fn = CFuncPtr3[Ptr[zloop_t], Ptr[zmq_pollitem_t], Ptr[Byte], CInt]
  object zloop_fn: 
    given _tag: Tag[zloop_fn] = Tag.materializeCFuncPtr3[Ptr[zloop_t], Ptr[zmq_pollitem_t], Ptr[Byte], CInt]
    inline def apply(inline o: CFuncPtr3[Ptr[zloop_t], Ptr[zmq_pollitem_t], Ptr[Byte], CInt]): zloop_fn = o
    extension (v: zloop_fn)
      inline def value: CFuncPtr3[Ptr[zloop_t], Ptr[zmq_pollitem_t], Ptr[Byte], CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  type zloop_reader_fn = CFuncPtr3[Ptr[zloop_t], Ptr[zsock_t], Ptr[Byte], CInt]
  object zloop_reader_fn: 
    given _tag: Tag[zloop_reader_fn] = Tag.materializeCFuncPtr3[Ptr[zloop_t], Ptr[zsock_t], Ptr[Byte], CInt]
    inline def apply(inline o: CFuncPtr3[Ptr[zloop_t], Ptr[zsock_t], Ptr[Byte], CInt]): zloop_reader_fn = o
    extension (v: zloop_reader_fn)
      inline def value: CFuncPtr3[Ptr[zloop_t], Ptr[zsock_t], Ptr[Byte], CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  type zloop_timer_fn = CFuncPtr3[Ptr[zloop_t], CInt, Ptr[Byte], CInt]
  object zloop_timer_fn: 
    given _tag: Tag[zloop_timer_fn] = Tag.materializeCFuncPtr3[Ptr[zloop_t], CInt, Ptr[Byte], CInt]
    inline def apply(inline o: CFuncPtr3[Ptr[zloop_t], CInt, Ptr[Byte], CInt]): zloop_timer_fn = o
    extension (v: zloop_timer_fn)
      inline def value: CFuncPtr3[Ptr[zloop_t], CInt, Ptr[Byte], CInt] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  opaque type zmq_fd_t = CInt
  object zmq_fd_t: 
    given _tag: Tag[zmq_fd_t] = Tag.Int
    inline def apply(inline o: CInt): zmq_fd_t = o
    extension (v: zmq_fd_t)
      inline def value: CInt = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  type zmq_free_fn = CFuncPtr2[Ptr[Byte], Ptr[Byte], Unit]
  object zmq_free_fn: 
    given _tag: Tag[zmq_free_fn] = Tag.materializeCFuncPtr2[Ptr[Byte], Ptr[Byte], Unit]
    inline def apply(inline o: CFuncPtr2[Ptr[Byte], Ptr[Byte], Unit]): zmq_free_fn = o
    extension (v: zmq_free_fn)
      inline def value: CFuncPtr2[Ptr[Byte], Ptr[Byte], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  type zmq_thread_fn = CFuncPtr1[Ptr[Byte], Unit]
  object zmq_thread_fn: 
    given _tag: Tag[zmq_thread_fn] = Tag.materializeCFuncPtr1[Ptr[Byte], Unit]
    inline def apply(inline o: CFuncPtr1[Ptr[Byte], Unit]): zmq_thread_fn = o
    extension (v: zmq_thread_fn)
      inline def value: CFuncPtr1[Ptr[Byte], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  type zmq_timer_fn = CFuncPtr2[CInt, Ptr[Byte], Unit]
  object zmq_timer_fn: 
    given _tag: Tag[zmq_timer_fn] = Tag.materializeCFuncPtr2[CInt, Ptr[Byte], Unit]
    inline def apply(inline o: CFuncPtr2[CInt, Ptr[Byte], Unit]): zmq_timer_fn = o
    extension (v: zmq_timer_fn)
      inline def value: CFuncPtr2[CInt, Ptr[Byte], Unit] = v

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  type zsys_handler_fn = CFuncPtr1[CInt, Unit]
  object zsys_handler_fn: 
    given _tag: Tag[zsys_handler_fn] = Tag.materializeCFuncPtr1[CInt, Unit]
    inline def apply(inline o: CFuncPtr1[CInt, Unit]): zsys_handler_fn = o
    extension (v: zsys_handler_fn)
      inline def value: CFuncPtr1[CInt, Unit] = v

object structs:
  import _root_.czmq.aliases.*
  import _root_.czmq.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zactor_t = CStruct0
  object _zactor_t:
    given _tag: Tag[_zactor_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zarmour_t = CStruct0
  object _zarmour_t:
    given _tag: Tag[_zarmour_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zauth_t = CStruct0
  object _zauth_t:
    given _tag: Tag[_zauth_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zbeacon_t = CStruct0
  object _zbeacon_t:
    given _tag: Tag[_zbeacon_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zcert_t = CStruct0
  object _zcert_t:
    given _tag: Tag[_zcert_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zcertstore_t = CStruct0
  object _zcertstore_t:
    given _tag: Tag[_zcertstore_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zchunk_t = CStruct0
  object _zchunk_t:
    given _tag: Tag[_zchunk_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zclock_t = CStruct0
  object _zclock_t:
    given _tag: Tag[_zclock_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zconfig_t = CStruct0
  object _zconfig_t:
    given _tag: Tag[_zconfig_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zdigest_t = CStruct0
  object _zdigest_t:
    given _tag: Tag[_zdigest_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zdir_patch_t = CStruct0
  object _zdir_patch_t:
    given _tag: Tag[_zdir_patch_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zdir_t = CStruct0
  object _zdir_t:
    given _tag: Tag[_zdir_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zfile_t = CStruct0
  object _zfile_t:
    given _tag: Tag[_zfile_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zframe_t = CStruct0
  object _zframe_t:
    given _tag: Tag[_zframe_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zgossip_t = CStruct0
  object _zgossip_t:
    given _tag: Tag[_zgossip_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zhash_t = CStruct0
  object _zhash_t:
    given _tag: Tag[_zhash_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zhashx_t = CStruct0
  object _zhashx_t:
    given _tag: Tag[_zhashx_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _ziflist_t = CStruct0
  object _ziflist_t:
    given _tag: Tag[_ziflist_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zlist_t = CStruct0
  object _zlist_t:
    given _tag: Tag[_zlist_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zlistx_t = CStruct0
  object _zlistx_t:
    given _tag: Tag[_zlistx_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zloop_t = CStruct0
  object _zloop_t:
    given _tag: Tag[_zloop_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zmonitor_t = CStruct0
  object _zmonitor_t:
    given _tag: Tag[_zmonitor_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zmsg_t = CStruct0
  object _zmsg_t:
    given _tag: Tag[_zmsg_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zpoller_t = CStruct0
  object _zpoller_t:
    given _tag: Tag[_zpoller_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zproxy_t = CStruct0
  object _zproxy_t:
    given _tag: Tag[_zproxy_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zrex_t = CStruct0
  object _zrex_t:
    given _tag: Tag[_zrex_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zsock_t = CStruct0
  object _zsock_t:
    given _tag: Tag[_zsock_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zstr_t = CStruct0
  object _zstr_t:
    given _tag: Tag[_zstr_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zsys_t = CStruct0
  object _zsys_t:
    given _tag: Tag[_zsys_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type _zuuid_t = CStruct0
  object _zuuid_t:
    given _tag: Tag[_zuuid_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/SDKs/MacOSX.sdk/usr/include/netinet6/in6.h
  */
  opaque type in6addr_t = CStruct6[__uint8_t, sa_family_t, in_port_t, __uint32_t, in6_addr, __uint32_t]
  object in6addr_t:
    given _tag: Tag[in6addr_t] = Tag.materializeCStruct6Tag[__uint8_t, sa_family_t, in_port_t, __uint32_t, in6_addr, __uint32_t]
    def apply()(using Zone): Ptr[in6addr_t] = scala.scalanative.unsafe.alloc[in6addr_t](1)
    def apply(sin6_len : __uint8_t, sin6_family : sa_family_t, sin6_port : in_port_t, sin6_flowinfo : __uint32_t, sin6_addr : in6_addr, sin6_scope_id : __uint32_t)(using Zone): Ptr[in6addr_t] = 
      val ____ptr = apply()
      (!____ptr).sin6_len = sin6_len
      (!____ptr).sin6_family = sin6_family
      (!____ptr).sin6_port = sin6_port
      (!____ptr).sin6_flowinfo = sin6_flowinfo
      (!____ptr).sin6_addr = sin6_addr
      (!____ptr).sin6_scope_id = sin6_scope_id
      ____ptr
    extension (struct: in6addr_t)
      def sin6_len : __uint8_t = struct._1
      def sin6_len_=(value: __uint8_t): Unit = !struct.at1 = value
      def sin6_family : sa_family_t = struct._2
      def sin6_family_=(value: sa_family_t): Unit = !struct.at2 = value
      def sin6_port : in_port_t = struct._3
      def sin6_port_=(value: in_port_t): Unit = !struct.at3 = value
      def sin6_flowinfo : __uint32_t = struct._4
      def sin6_flowinfo_=(value: __uint32_t): Unit = !struct.at4 = value
      def sin6_addr : in6_addr = struct._5
      def sin6_addr_=(value: in6_addr): Unit = !struct.at5 = value
      def sin6_scope_id : __uint32_t = struct._6
      def sin6_scope_id_=(value: __uint32_t): Unit = !struct.at6 = value

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  opaque type inaddr_storage_t = CStruct2[inaddr_storage_t.Union0, CInt]
  object inaddr_storage_t:
    /**
     * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
    */
    opaque type Union0 = CArray[Byte, Nat.Digit2[Nat._3, Nat._2]]
    object Union0:
      given _tag: Tag[Union0] = Tag.CArray[CChar, Nat.Digit2[Nat._3, Nat._2]](Tag.Byte, Tag.Digit2[Nat._3, Nat._2](Tag.Nat3, Tag.Nat2))
      def apply()(using Zone): Ptr[Union0] = 
        val ___ptr = alloc[Union0](1)
        ___ptr
      @scala.annotation.targetName("apply___addr")
      def apply(__addr: inaddr_t)(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[inaddr_t]].update(0, __addr)
        ___ptr
      @scala.annotation.targetName("apply___addr6")
      def apply(__addr6: in6addr_t)(using Zone): Ptr[Union0] =
        val ___ptr = alloc[Union0](1)
        val un = !___ptr
        un.at(0).asInstanceOf[Ptr[in6addr_t]].update(0, __addr6)
        ___ptr
      extension (struct: Union0)
        def __addr : inaddr_t = !struct.at(0).asInstanceOf[Ptr[inaddr_t]]
        def __addr_=(value: inaddr_t): Unit = !struct.at(0).asInstanceOf[Ptr[inaddr_t]] = value
        def __addr6 : in6addr_t = !struct.at(0).asInstanceOf[Ptr[in6addr_t]]
        def __addr6_=(value: in6addr_t): Unit = !struct.at(0).asInstanceOf[Ptr[in6addr_t]] = value
    given _tag: Tag[inaddr_storage_t] = Tag.materializeCStruct2Tag[inaddr_storage_t.Union0, CInt]
    def apply()(using Zone): Ptr[inaddr_storage_t] = scala.scalanative.unsafe.alloc[inaddr_storage_t](1)
    def apply(__inaddr_u : inaddr_storage_t.Union0, inaddrlen : CInt)(using Zone): Ptr[inaddr_storage_t] = 
      val ____ptr = apply()
      (!____ptr).__inaddr_u = __inaddr_u
      (!____ptr).inaddrlen = inaddrlen
      ____ptr
    extension (struct: inaddr_storage_t)
      def __inaddr_u : inaddr_storage_t.Union0 = struct._1
      def __inaddr_u_=(value: inaddr_storage_t.Union0): Unit = !struct.at1 = value
      def inaddrlen : CInt = struct._2
      def inaddrlen_=(value: CInt): Unit = !struct.at2 = value

  /**
   * [bindgen] header: /Library/Developer/CommandLineTools/SDKs/MacOSX.sdk/usr/include/netinet/in.h
  */
  opaque type inaddr_t = CStruct5[__uint8_t, sa_family_t, in_port_t, in_addr, CArray[CChar, Nat._8]]
  object inaddr_t:
    given _tag: Tag[inaddr_t] = Tag.materializeCStruct5Tag[__uint8_t, sa_family_t, in_port_t, in_addr, CArray[CChar, Nat._8]]
    def apply()(using Zone): Ptr[inaddr_t] = scala.scalanative.unsafe.alloc[inaddr_t](1)
    def apply(sin_len : __uint8_t, sin_family : sa_family_t, sin_port : in_port_t, sin_addr : in_addr, sin_zero : CArray[CChar, Nat._8])(using Zone): Ptr[inaddr_t] = 
      val ____ptr = apply()
      (!____ptr).sin_len = sin_len
      (!____ptr).sin_family = sin_family
      (!____ptr).sin_port = sin_port
      (!____ptr).sin_addr = sin_addr
      (!____ptr).sin_zero = sin_zero
      ____ptr
    extension (struct: inaddr_t)
      def sin_len : __uint8_t = struct._1
      def sin_len_=(value: __uint8_t): Unit = !struct.at1 = value
      def sin_family : sa_family_t = struct._2
      def sin_family_=(value: sa_family_t): Unit = !struct.at2 = value
      def sin_port : in_port_t = struct._3
      def sin_port_=(value: in_port_t): Unit = !struct.at3 = value
      def sin_addr : in_addr = struct._4
      def sin_addr_=(value: in_addr): Unit = !struct.at4 = value
      def sin_zero : CArray[CChar, Nat._8] = struct._5
      def sin_zero_=(value: CArray[CChar, Nat._8]): Unit = !struct.at5 = value

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zactor_t = CStruct0
  object zactor_t:
    given _tag: Tag[zactor_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zarmour_t = CStruct0
  object zarmour_t:
    given _tag: Tag[zarmour_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zauth_t = CStruct0
  object zauth_t:
    given _tag: Tag[zauth_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zbeacon_t = CStruct0
  object zbeacon_t:
    given _tag: Tag[zbeacon_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zcert_t = CStruct0
  object zcert_t:
    given _tag: Tag[zcert_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zcertstore_t = CStruct0
  object zcertstore_t:
    given _tag: Tag[zcertstore_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zchunk_t = CStruct0
  object zchunk_t:
    given _tag: Tag[zchunk_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zclock_t = CStruct0
  object zclock_t:
    given _tag: Tag[zclock_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zconfig_t = CStruct0
  object zconfig_t:
    given _tag: Tag[zconfig_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zdigest_t = CStruct0
  object zdigest_t:
    given _tag: Tag[zdigest_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zdir_patch_t = CStruct0
  object zdir_patch_t:
    given _tag: Tag[zdir_patch_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zdir_t = CStruct0
  object zdir_t:
    given _tag: Tag[zdir_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zfile_t = CStruct0
  object zfile_t:
    given _tag: Tag[zfile_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zframe_t = CStruct0
  object zframe_t:
    given _tag: Tag[zframe_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zgossip_t = CStruct0
  object zgossip_t:
    given _tag: Tag[zgossip_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zhash_t = CStruct0
  object zhash_t:
    given _tag: Tag[zhash_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zhashx_t = CStruct0
  object zhashx_t:
    given _tag: Tag[zhashx_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type ziflist_t = CStruct0
  object ziflist_t:
    given _tag: Tag[ziflist_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zlist_t = CStruct0
  object zlist_t:
    given _tag: Tag[zlist_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zlistx_t = CStruct0
  object zlistx_t:
    given _tag: Tag[zlistx_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zloop_t = CStruct0
  object zloop_t:
    given _tag: Tag[zloop_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zmonitor_t = CStruct0
  object zmonitor_t:
    given _tag: Tag[zmonitor_t] = Tag.materializeCStruct0Tag

  /**
   * ***************************************************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  opaque type zmq_msg_t = CStruct1[CArray[CUnsignedChar, Nat.Digit2[Nat._6, Nat._4]]]
  object zmq_msg_t:
    given _tag: Tag[zmq_msg_t] = Tag.materializeCStruct1Tag[CArray[CUnsignedChar, Nat.Digit2[Nat._6, Nat._4]]]
    def apply()(using Zone): Ptr[zmq_msg_t] = scala.scalanative.unsafe.alloc[zmq_msg_t](1)
    def apply($underscore : CArray[CUnsignedChar, Nat.Digit2[Nat._6, Nat._4]])(using Zone): Ptr[zmq_msg_t] = 
      val ____ptr = apply()
      (!____ptr).$underscore = $underscore
      ____ptr
    extension (struct: zmq_msg_t)
      def $underscore : CArray[CUnsignedChar, Nat.Digit2[Nat._6, Nat._4]] = struct._1
      def $underscore_=(value: CArray[CUnsignedChar, Nat.Digit2[Nat._6, Nat._4]]): Unit = !struct.at1 = value

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  opaque type zmq_pollitem_t = CStruct4[Ptr[Byte], zmq_fd_t, CShort, CShort]
  object zmq_pollitem_t:
    given _tag: Tag[zmq_pollitem_t] = Tag.materializeCStruct4Tag[Ptr[Byte], zmq_fd_t, CShort, CShort]
    def apply()(using Zone): Ptr[zmq_pollitem_t] = scala.scalanative.unsafe.alloc[zmq_pollitem_t](1)
    def apply(socket : Ptr[Byte], fd : zmq_fd_t, events : CShort, revents : CShort)(using Zone): Ptr[zmq_pollitem_t] = 
      val ____ptr = apply()
      (!____ptr).socket = socket
      (!____ptr).fd = fd
      (!____ptr).events = events
      (!____ptr).revents = revents
      ____ptr
    extension (struct: zmq_pollitem_t)
      def socket : Ptr[Byte] = struct._1
      def socket_=(value: Ptr[Byte]): Unit = !struct.at1 = value
      def fd : zmq_fd_t = struct._2
      def fd_=(value: zmq_fd_t): Unit = !struct.at2 = value
      def events : CShort = struct._3
      def events_=(value: CShort): Unit = !struct.at3 = value
      def revents : CShort = struct._4
      def revents_=(value: CShort): Unit = !struct.at4 = value

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zmsg_t = CStruct0
  object zmsg_t:
    given _tag: Tag[zmsg_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zpoller_t = CStruct0
  object zpoller_t:
    given _tag: Tag[zpoller_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zproxy_t = CStruct0
  object zproxy_t:
    given _tag: Tag[zproxy_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zrex_t = CStruct0
  object zrex_t:
    given _tag: Tag[zrex_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zsock_t = CStruct0
  object zsock_t:
    given _tag: Tag[zsock_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zstr_t = CStruct0
  object zstr_t:
    given _tag: Tag[zstr_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zsys_t = CStruct0
  object zsys_t:
    given _tag: Tag[zsys_t] = Tag.materializeCStruct0Tag

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_library.h
  */
  opaque type zuuid_t = CStruct0
  object zuuid_t:
    given _tag: Tag[zuuid_t] = Tag.materializeCStruct0Tag


@extern
private[czmq] object extern_functions:
  import _root_.czmq.aliases.*
  import _root_.czmq.structs.*

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/czmq_prelude.h
  */
  def safe_malloc(size : size_t, file : CString, line : CUnsignedInt): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_destroy(self_p : Ptr[Ptr[zactor_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_is(self : Ptr[Byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_new(task : zactor_fn, args : Ptr[Byte]): Ptr[zactor_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_recv(self : Ptr[zactor_t]): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_resolve(self : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_send(self : Ptr[zactor_t], msg_p : Ptr[Ptr[zmsg_t]]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_sock(self : Ptr[zactor_t]): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zactor.h
  */
  def zactor_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_decode(self : Ptr[zarmour_t], data : CString): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_destroy(self_p : Ptr[Ptr[zarmour_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_encode(self : Ptr[zarmour_t], data : Ptr[byte], size : size_t): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_line_breaks(self : Ptr[zarmour_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_line_length(self : Ptr[zarmour_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_mode(self : Ptr[zarmour_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_mode_str(self : Ptr[zarmour_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_new(): Ptr[zarmour_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_pad(self : Ptr[zarmour_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_pad_char(self : Ptr[zarmour_t]): CChar = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_print(self : Ptr[zarmour_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_set_line_breaks(self : Ptr[zarmour_t], line_breaks : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_set_line_length(self : Ptr[zarmour_t], line_length : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_set_mode(self : Ptr[zarmour_t], mode : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_set_pad(self : Ptr[zarmour_t], pad : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_set_pad_char(self : Ptr[zarmour_t], pad_char : CChar): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zarmour.h
  */
  def zarmour_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zauth.h
  */
  def zauth(pipe : Ptr[zsock_t], certstore : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zauth.h
  */
  def zauth_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zbeacon.h
  */
  def zbeacon(pipe : Ptr[zsock_t], unused : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zbeacon.h
  */
  def zbeacon_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_apply(self : Ptr[zcert_t], socket : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_destroy(self_p : Ptr[Ptr[zcert_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_dup(self : Ptr[zcert_t]): Ptr[zcert_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_eq(self : Ptr[zcert_t], compare : Ptr[zcert_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_load(filename : CString): Ptr[zcert_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_meta(self : Ptr[zcert_t], name : CString): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_meta_keys(self : Ptr[zcert_t]): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_new(): Ptr[zcert_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_new_from(public_key : Ptr[byte], secret_key : Ptr[byte]): Ptr[zcert_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_print(self : Ptr[zcert_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_public_key(self : Ptr[zcert_t]): Ptr[byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_public_txt(self : Ptr[zcert_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_save(self : Ptr[zcert_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_save_public(self : Ptr[zcert_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_save_secret(self : Ptr[zcert_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_secret_key(self : Ptr[zcert_t]): Ptr[byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_secret_txt(self : Ptr[zcert_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_set_meta(self : Ptr[zcert_t], name : CString, format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcert.h
  */
  def zcert_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcertstore.h
  */
  def zcertstore_destroy(self_p : Ptr[Ptr[zcertstore_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcertstore.h
  */
  def zcertstore_insert(self : Ptr[zcertstore_t], cert_p : Ptr[Ptr[zcert_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcertstore.h
  */
  def zcertstore_lookup(self : Ptr[zcertstore_t], public_key : CString): Ptr[zcert_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcertstore.h
  */
  def zcertstore_new(location : CString): Ptr[zcertstore_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcertstore.h
  */
  def zcertstore_print(self : Ptr[zcertstore_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zcertstore.h
  */
  def zcertstore_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_append(self : Ptr[zchunk_t], data : Ptr[Byte], size : size_t): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_consume(self : Ptr[zchunk_t], source : Ptr[zchunk_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_data(self : Ptr[zchunk_t]): Ptr[byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_destroy(self_p : Ptr[Ptr[zchunk_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_digest(self : Ptr[zchunk_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_dup(self : Ptr[zchunk_t]): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_exhausted(self : Ptr[zchunk_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_extend(self : Ptr[zchunk_t], data : Ptr[Byte], size : size_t): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_fill(self : Ptr[zchunk_t], filler : byte, size : size_t): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_fprint(self : Ptr[zchunk_t], file : Ptr[FILE]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_is(self : Ptr[Byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_max_size(self : Ptr[zchunk_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_new(data : Ptr[Byte], size : size_t): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_pack(self : Ptr[zchunk_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_print(self : Ptr[zchunk_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_read(handle : Ptr[FILE], bytes : size_t): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_resize(self : Ptr[zchunk_t], size : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_set(self : Ptr[zchunk_t], data : Ptr[Byte], size : size_t): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_size(self : Ptr[zchunk_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_slurp(filename : CString, maxsize : size_t): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_strdup(self : Ptr[zchunk_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_streq(self : Ptr[zchunk_t], string : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_strhex(self : Ptr[zchunk_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_unpack(frame : Ptr[zframe_t]): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zchunk.h
  */
  def zchunk_write(self : Ptr[zchunk_t], handle : Ptr[FILE]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zclock.h
  */
  def zclock_log(format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zclock.h
  */
  def zclock_mono(): int64_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zclock.h
  */
  def zclock_sleep(msecs : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zclock.h
  */
  def zclock_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zclock.h
  */
  def zclock_time(): int64_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zclock.h
  */
  def zclock_timestr(): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zclock.h
  */
  def zclock_usecs(): int64_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_at_depth(self : Ptr[zconfig_t], level : CInt): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_child(self : Ptr[zconfig_t]): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_chunk_load(chunk : Ptr[zchunk_t]): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_chunk_save(self : Ptr[zconfig_t]): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_comments(self : Ptr[zconfig_t]): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_destroy(self_p : Ptr[Ptr[zconfig_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_execute(self : Ptr[zconfig_t], handler : zconfig_fct, arg : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_filename(self : Ptr[zconfig_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_fprint(self : Ptr[zconfig_t], file : Ptr[FILE]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_get(self : Ptr[zconfig_t], path : CString, default_value : CString): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_has_changed(self : Ptr[zconfig_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_load(filename : CString): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_loadf(format : CString, rest: Any*): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_locate(self : Ptr[zconfig_t], path : CString): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_name(self : Ptr[zconfig_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_new(name : CString, parent : Ptr[zconfig_t]): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_next(self : Ptr[zconfig_t]): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_print(self : Ptr[zconfig_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_put(self : Ptr[zconfig_t], path : CString, value : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_putf(self : Ptr[zconfig_t], path : CString, format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_reload(self_p : Ptr[Ptr[zconfig_t]]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_save(self : Ptr[zconfig_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_savef(self : Ptr[zconfig_t], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_set_comment(self : Ptr[zconfig_t], format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_set_name(self : Ptr[zconfig_t], name : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_set_value(self : Ptr[zconfig_t], format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_str_load(string : CString): Ptr[zconfig_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_str_save(self : Ptr[zconfig_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zconfig.h
  */
  def zconfig_value(self : Ptr[zconfig_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdigest.h
  */
  def zdigest_data(self : Ptr[zdigest_t]): Ptr[byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdigest.h
  */
  def zdigest_destroy(self_p : Ptr[Ptr[zdigest_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdigest.h
  */
  def zdigest_new(): Ptr[zdigest_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdigest.h
  */
  def zdigest_size(self : Ptr[zdigest_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdigest.h
  */
  def zdigest_string(self : Ptr[zdigest_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdigest.h
  */
  def zdigest_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdigest.h
  */
  def zdigest_update(self : Ptr[zdigest_t], buffer : Ptr[byte], length : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_cache(self : Ptr[zdir_t]): Ptr[zhash_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_count(self : Ptr[zdir_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_cursize(self : Ptr[zdir_t]): off_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_destroy(self_p : Ptr[Ptr[zdir_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_diff(older : Ptr[zdir_t], newer : Ptr[zdir_t], alias : CString): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_flatten(self : Ptr[zdir_t]): Ptr[Ptr[zfile_t]] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_flatten_free(files_p : Ptr[Ptr[Ptr[zfile_t]]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_fprint(self : Ptr[zdir_t], file : Ptr[FILE], indent : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_list(self : Ptr[zdir_t]): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_modified(self : Ptr[zdir_t]): time_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_new(path : CString, parent : CString): Ptr[zdir_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_destroy(self_p : Ptr[Ptr[zdir_patch_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_digest(self : Ptr[zdir_patch_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_digest_set(self : Ptr[zdir_patch_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_dup(self : Ptr[zdir_patch_t]): Ptr[zdir_patch_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_file(self : Ptr[zdir_patch_t]): Ptr[zfile_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_new(path : CString, file : Ptr[zfile_t], op : CInt, alias : CString): Ptr[zdir_patch_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_op(self : Ptr[zdir_patch_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_path(self : Ptr[zdir_patch_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir_patch.h
  */
  def zdir_patch_vpath(self : Ptr[zdir_patch_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_path(self : Ptr[zdir_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_print(self : Ptr[zdir_t], indent : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_remove(self : Ptr[zdir_t], force : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_resync(self : Ptr[zdir_t], alias : CString): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zdir.h
  */
  def zdir_watch(pipe : Ptr[zsock_t], unused : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_close(self : Ptr[zfile_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_cursize(self : Ptr[zfile_t]): off_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_delete(filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_destroy(self_p : Ptr[Ptr[zfile_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_digest(self : Ptr[zfile_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_dup(self : Ptr[zfile_t]): Ptr[zfile_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_eof(self : Ptr[zfile_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_exists(filename : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_filename(self : Ptr[zfile_t], path : CString): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_handle(self : Ptr[zfile_t]): Ptr[FILE] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_has_changed(self : Ptr[zfile_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_input(self : Ptr[zfile_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_is_directory(self : Ptr[zfile_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_is_readable(self : Ptr[zfile_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_is_regular(self : Ptr[zfile_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_is_stable(self : Ptr[zfile_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_is_writeable(self : Ptr[zfile_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_mkdir(pathname : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_mode(filename : CString): mode_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_mode_default(): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_mode_private(): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_modified(self : Ptr[zfile_t]): time_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_new(path : CString, name : CString): Ptr[zfile_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_output(self : Ptr[zfile_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_read(self : Ptr[zfile_t], bytes : size_t, offset : off_t): Ptr[zchunk_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_readln(self : Ptr[zfile_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_remove(self : Ptr[zfile_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_restat(self : Ptr[zfile_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_rmdir(pathname : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_size(filename : CString): ssize_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_stable(filename : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zfile.h
  */
  def zfile_write(self : Ptr[zfile_t], chunk : Ptr[zchunk_t], offset : off_t): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_data(self : Ptr[zframe_t]): Ptr[byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_destroy(self_p : Ptr[Ptr[zframe_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_dup(self : Ptr[zframe_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_eq(self : Ptr[zframe_t], other : Ptr[zframe_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_fprint(self : Ptr[zframe_t], prefix : CString, file : Ptr[FILE]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_from(string : CString): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_is(self : Ptr[Byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_meta(self : Ptr[zframe_t], property : CString): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_more(self : Ptr[zframe_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_new(data : Ptr[Byte], size : size_t): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_new_empty(): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_print(self : Ptr[zframe_t], prefix : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_recv(source : Ptr[Byte]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_recv_nowait(source : Ptr[Byte]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_reset(self : Ptr[zframe_t], data : Ptr[Byte], size : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_send(self_p : Ptr[Ptr[zframe_t]], dest : Ptr[Byte], flags : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_set_more(self : Ptr[zframe_t], more : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_size(self : Ptr[zframe_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_strdup(self : Ptr[zframe_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_streq(self : Ptr[zframe_t], string : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_strhex(self : Ptr[zframe_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zframe.h
  */
  def zframe_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zgossip.h
  */
  def zgossip(pipe : Ptr[zsock_t], args : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zgossip.h
  */
  def zgossip_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_autofree(self : Ptr[zhash_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_comment(self : Ptr[zhash_t], format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_cursor(self : Ptr[zhash_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_delete(self : Ptr[zhash_t], key : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_destroy(self_p : Ptr[Ptr[zhash_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_dup(self : Ptr[zhash_t]): Ptr[zhash_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_first(self : Ptr[zhash_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_freefn(self : Ptr[zhash_t], key : CString, free_fn : zhash_free_fn): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_insert(self : Ptr[zhash_t], key : CString, item : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_keys(self : Ptr[zhash_t]): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_load(self : Ptr[zhash_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_lookup(self : Ptr[zhash_t], key : CString): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_new(): Ptr[zhash_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_next(self : Ptr[zhash_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_pack(self : Ptr[zhash_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_refresh(self : Ptr[zhash_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_rename(self : Ptr[zhash_t], old_key : CString, new_key : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_save(self : Ptr[zhash_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_size(self : Ptr[zhash_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_unpack(frame : Ptr[zframe_t]): Ptr[zhash_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhash.h
  */
  def zhash_update(self : Ptr[zhash_t], key : CString, item : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_comment(self : Ptr[zhashx_t], format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_cursor(self : Ptr[zhashx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_delete(self : Ptr[zhashx_t], key : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_destroy(self_p : Ptr[Ptr[zhashx_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_dup(self : Ptr[zhashx_t]): Ptr[zhashx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_dup_v2(self : Ptr[zhashx_t]): Ptr[zhashx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_first(self : Ptr[zhashx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_freefn(self : Ptr[zhashx_t], key : Ptr[Byte], free_fn : zhashx_free_fn): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_insert(self : Ptr[zhashx_t], key : Ptr[Byte], item : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_keys(self : Ptr[zhashx_t]): Ptr[zlistx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_load(self : Ptr[zhashx_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_lookup(self : Ptr[zhashx_t], key : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_new(): Ptr[zhashx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_next(self : Ptr[zhashx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_pack(self : Ptr[zhashx_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_purge(self : Ptr[zhashx_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_refresh(self : Ptr[zhashx_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_rename(self : Ptr[zhashx_t], old_key : Ptr[Byte], new_key : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_save(self : Ptr[zhashx_t], filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_set_destructor(self : Ptr[zhashx_t], destructor : zhashx_destructor_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_set_duplicator(self : Ptr[zhashx_t], duplicator : zhashx_duplicator_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_set_key_comparator(self : Ptr[zhashx_t], comparator : zhashx_comparator_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_set_key_destructor(self : Ptr[zhashx_t], destructor : zhashx_destructor_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_set_key_duplicator(self : Ptr[zhashx_t], duplicator : zhashx_duplicator_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_set_key_hasher(self : Ptr[zhashx_t], hasher : zhashx_hash_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_size(self : Ptr[zhashx_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_unpack(frame : Ptr[zframe_t]): Ptr[zhashx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_update(self : Ptr[zhashx_t], key : Ptr[Byte], item : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zhashx.h
  */
  def zhashx_values(self : Ptr[zhashx_t]): Ptr[zlistx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_address(self : Ptr[ziflist_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_broadcast(self : Ptr[ziflist_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_destroy(self_p : Ptr[Ptr[ziflist_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_first(self : Ptr[ziflist_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_netmask(self : Ptr[ziflist_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_new(): Ptr[ziflist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_next(self : Ptr[ziflist_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_print(self : Ptr[ziflist_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_reload(self : Ptr[ziflist_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_size(self : Ptr[ziflist_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/ziflist.h
  */
  def ziflist_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_append(self : Ptr[zlist_t], item : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_autofree(self : Ptr[zlist_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_comparefn(self : Ptr[zlist_t], fn : zlist_compare_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_destroy(self_p : Ptr[Ptr[zlist_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_dup(self : Ptr[zlist_t]): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_exists(self : Ptr[zlist_t], item : Ptr[Byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_first(self : Ptr[zlist_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_freefn(self : Ptr[zlist_t], item : Ptr[Byte], fn : zlist_free_fn, at_tail : Boolean): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_head(self : Ptr[zlist_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_item(self : Ptr[zlist_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_last(self : Ptr[zlist_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_new(): Ptr[zlist_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_next(self : Ptr[zlist_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_pop(self : Ptr[zlist_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_purge(self : Ptr[zlist_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_push(self : Ptr[zlist_t], item : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_remove(self : Ptr[zlist_t], item : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_size(self : Ptr[zlist_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_sort(self : Ptr[zlist_t], compare : zlist_compare_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_tail(self : Ptr[zlist_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlist.h
  */
  def zlist_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_add_end(self : Ptr[zlistx_t], item : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_add_start(self : Ptr[zlistx_t], item : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_cursor(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_delete(self : Ptr[zlistx_t], handle : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_destroy(self_p : Ptr[Ptr[zlistx_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_detach(self : Ptr[zlistx_t], handle : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_detach_cur(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_dup(self : Ptr[zlistx_t]): Ptr[zlistx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_find(self : Ptr[zlistx_t], item : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_first(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_handle_item(handle : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_head(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_insert(self : Ptr[zlistx_t], item : Ptr[Byte], low_value : Boolean): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_item(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_last(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_move_end(self : Ptr[zlistx_t], handle : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_move_start(self : Ptr[zlistx_t], handle : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_new(): Ptr[zlistx_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_next(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_prev(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_purge(self : Ptr[zlistx_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_reorder(self : Ptr[zlistx_t], handle : Ptr[Byte], low_value : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_set_comparator(self : Ptr[zlistx_t], comparator : zlistx_comparator_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_set_destructor(self : Ptr[zlistx_t], destructor : zlistx_destructor_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_set_duplicator(self : Ptr[zlistx_t], duplicator : zlistx_duplicator_fn): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_size(self : Ptr[zlistx_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_sort(self : Ptr[zlistx_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_tail(self : Ptr[zlistx_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zlistx.h
  */
  def zlistx_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_destroy(self_p : Ptr[Ptr[zloop_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_new(): Ptr[zloop_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_poller(self : Ptr[zloop_t], item : Ptr[zmq_pollitem_t], handler : zloop_fn, arg : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_poller_end(self : Ptr[zloop_t], item : Ptr[zmq_pollitem_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_poller_set_tolerant(self : Ptr[zloop_t], item : Ptr[zmq_pollitem_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_reader(self : Ptr[zloop_t], sock : Ptr[zsock_t], handler : zloop_reader_fn, arg : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_reader_end(self : Ptr[zloop_t], sock : Ptr[zsock_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_reader_set_tolerant(self : Ptr[zloop_t], sock : Ptr[zsock_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_set_max_timers(self : Ptr[zloop_t], max_timers : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_set_nonstop(self : Ptr[zloop_t], nonstop : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_set_ticket_delay(self : Ptr[zloop_t], ticket_delay : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_set_verbose(self : Ptr[zloop_t], verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_start(self : Ptr[zloop_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_ticket(self : Ptr[zloop_t], handler : zloop_timer_fn, arg : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_ticket_delete(self : Ptr[zloop_t], handle : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_ticket_reset(self : Ptr[zloop_t], handle : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_timer(self : Ptr[zloop_t], delay : size_t, times : size_t, handler : zloop_timer_fn, arg : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zloop.h
  */
  def zloop_timer_end(self : Ptr[zloop_t], timer_id : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmonitor.h
  */
  def zmonitor(pipe : Ptr[zsock_t], sock : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmonitor.h
  */
  def zmonitor_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_atomic_counter_dec(`counter_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_atomic_counter_destroy(`counter_p_` : Ptr[Ptr[Byte]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_atomic_counter_inc(`counter_` : Ptr[Byte]): CInt = extern

  /**
   * ***************************************************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_atomic_counter_new(): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_atomic_counter_set(`counter_` : Ptr[Byte], `value_` : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_atomic_counter_value(`counter_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_bind(`s_` : Ptr[Byte], `addr_` : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_close(`s_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_connect(`s_` : Ptr[Byte], `addr_` : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_ctx_destroy(`context_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_ctx_get(`context_` : Ptr[Byte], `option_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_ctx_new(): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_ctx_set(`context_` : Ptr[Byte], `option_` : CInt, `optval_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_ctx_shutdown(`context_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_ctx_term(`context_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_curve_keypair(`z85_public_key_` : CString, `z85_secret_key_` : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_curve_public(`z85_public_key_` : CString, `z85_secret_key_` : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_device(`type_` : CInt, `frontend_` : Ptr[Byte], `backend_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_disconnect(`s_` : Ptr[Byte], `addr_` : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_errno(): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_getsockopt(`s_` : Ptr[Byte], `option_` : CInt, `optval_` : Ptr[Byte], `optvallen_` : Ptr[size_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_has(`capability_` : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_init(`io_threads_` : CInt): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_close(`msg_` : Ptr[zmq_msg_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_copy(`dest_` : Ptr[zmq_msg_t], `src_` : Ptr[zmq_msg_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_data(`msg_` : Ptr[zmq_msg_t]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_get(`msg_` : Ptr[zmq_msg_t], `property_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_gets(`msg_` : Ptr[zmq_msg_t], `property_` : CString): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_init(`msg_` : Ptr[zmq_msg_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_init_data(`msg_` : Ptr[zmq_msg_t], `data_` : Ptr[Byte], `size_` : size_t, `ffn_` : Ptr[zmq_free_fn], `hint_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_init_size(`msg_` : Ptr[zmq_msg_t], `size_` : size_t): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_more(`msg_` : Ptr[zmq_msg_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_move(`dest_` : Ptr[zmq_msg_t], `src_` : Ptr[zmq_msg_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_recv(`msg_` : Ptr[zmq_msg_t], `s_` : Ptr[Byte], `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_send(`msg_` : Ptr[zmq_msg_t], `s_` : Ptr[Byte], `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_set(`msg_` : Ptr[zmq_msg_t], `property_` : CInt, `optval_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_msg_size(`msg_` : Ptr[zmq_msg_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_poll(`items_` : Ptr[zmq_pollitem_t], `nitems_` : CInt, `timeout_` : CLongInt): CInt = extern

  /**
   * ***************************************************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_proxy(`frontend_` : Ptr[Byte], `backend_` : Ptr[Byte], `capture_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_proxy_steerable(`frontend_` : Ptr[Byte], `backend_` : Ptr[Byte], `capture_` : Ptr[Byte], `control_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_recv(`s_` : Ptr[Byte], `buf_` : Ptr[Byte], `len_` : size_t, `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_recviov(`s_` : Ptr[Byte], `iov_` : Ptr[iovec], `count_` : Ptr[size_t], `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_recvmsg(`s_` : Ptr[Byte], `msg_` : Ptr[zmq_msg_t], `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_send(`s_` : Ptr[Byte], `buf_` : Ptr[Byte], `len_` : size_t, `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_send_const(`s_` : Ptr[Byte], `buf_` : Ptr[Byte], `len_` : size_t, `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_sendiov(`s_` : Ptr[Byte], `iov_` : Ptr[iovec], `count_` : size_t, `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_sendmsg(`s_` : Ptr[Byte], `msg_` : Ptr[zmq_msg_t], `flags_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_setsockopt(`s_` : Ptr[Byte], `option_` : CInt, `optval_` : Ptr[Byte], `optvallen_` : size_t): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_sleep(`seconds_` : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_socket(_0 : Ptr[Byte], `type_` : CInt): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_socket_monitor(`s_` : Ptr[Byte], `addr_` : CString, `events_` : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_stopwatch_intermediate(`watch_` : Ptr[Byte]): CUnsignedLongInt = extern

  /**
   * ***************************************************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_stopwatch_start(): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_stopwatch_stop(`watch_` : Ptr[Byte]): CUnsignedLongInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_strerror(`errnum_` : CInt): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_term(`context_` : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_threadclose(`thread_` : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_threadstart(`func_` : Ptr[zmq_thread_fn], `arg_` : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_add(timers : Ptr[Byte], interval : size_t, handler : zmq_timer_fn, arg : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_cancel(timers : Ptr[Byte], timer_id : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_destroy(timers_p : Ptr[Ptr[Byte]]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_execute(timers : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_new(): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_reset(timers : Ptr[Byte], timer_id : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_set_interval(timers : Ptr[Byte], timer_id : CInt, interval : size_t): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_timers_timeout(timers : Ptr[Byte]): CLongInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_unbind(`s_` : Ptr[Byte], `addr_` : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_version(`major_` : Ptr[CInt], `minor_` : Ptr[CInt], `patch_` : Ptr[CInt]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_z85_decode(`dest_` : Ptr[uint8_t], `string_` : CString): Ptr[uint8_t] = extern

  /**
   * ***************************************************************************
  
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/zeromq_arm64-osx/include/zmq.h
  */
  def zmq_z85_encode(`dest_` : CString, `data_` : Ptr[uint8_t], `size_` : size_t): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_add(self : Ptr[zmsg_t], frame : Ptr[zframe_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_addmem(self : Ptr[zmsg_t], data : Ptr[Byte], size : size_t): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_addmsg(self : Ptr[zmsg_t], msg_p : Ptr[Ptr[zmsg_t]]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_addstr(self : Ptr[zmsg_t], string : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_addstrf(self : Ptr[zmsg_t], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_append(self : Ptr[zmsg_t], frame_p : Ptr[Ptr[zframe_t]]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_content_size(self : Ptr[zmsg_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_decode(frame : Ptr[zframe_t]): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_destroy(self_p : Ptr[Ptr[zmsg_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_dup(self : Ptr[zmsg_t]): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_encode(self : Ptr[zmsg_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_eq(self : Ptr[zmsg_t], other : Ptr[zmsg_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_first(self : Ptr[zmsg_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_fprint(self : Ptr[zmsg_t], file : Ptr[FILE]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_is(self : Ptr[Byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_last(self : Ptr[zmsg_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_load(file : Ptr[FILE]): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_new(): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_new_signal(status : byte): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_next(self : Ptr[zmsg_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_pop(self : Ptr[zmsg_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_popmsg(self : Ptr[zmsg_t]): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_popstr(self : Ptr[zmsg_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_prepend(self : Ptr[zmsg_t], frame_p : Ptr[Ptr[zframe_t]]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_print(self : Ptr[zmsg_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_push(self : Ptr[zmsg_t], frame : Ptr[zframe_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_pushmem(self : Ptr[zmsg_t], data : Ptr[Byte], size : size_t): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_pushstr(self : Ptr[zmsg_t], string : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_pushstrf(self : Ptr[zmsg_t], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_recv(source : Ptr[Byte]): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_recv_nowait(source : Ptr[Byte]): Ptr[zmsg_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_remove(self : Ptr[zmsg_t], frame : Ptr[zframe_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_save(self : Ptr[zmsg_t], file : Ptr[FILE]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_send(self_p : Ptr[Ptr[zmsg_t]], dest : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_sendm(self_p : Ptr[Ptr[zmsg_t]], dest : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_signal(self : Ptr[zmsg_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_size(self : Ptr[zmsg_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_unwrap(self : Ptr[zmsg_t]): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zmsg.h
  */
  def zmsg_wrap(self : Ptr[zmsg_t], frame : Ptr[zframe_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_add(self : Ptr[zpoller_t], reader : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_destroy(self_p : Ptr[Ptr[zpoller_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_expired(self : Ptr[zpoller_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_new(reader : Ptr[Byte], rest: Any*): Ptr[zpoller_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_remove(self : Ptr[zpoller_t], reader : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_set_nonstop(self : Ptr[zpoller_t], nonstop : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_terminated(self : Ptr[zpoller_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zpoller.h
  */
  def zpoller_wait(self : Ptr[zpoller_t], timeout : CInt): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zproxy.h
  */
  def zproxy(pipe : Ptr[zsock_t], unused : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zproxy.h
  */
  def zproxy_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_destroy(self_p : Ptr[Ptr[zrex_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_eq(self : Ptr[zrex_t], text : CString, expression : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_fetch(self : Ptr[zrex_t], string_p : Ptr[CString], rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_hit(self : Ptr[zrex_t], index : uint): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_hits(self : Ptr[zrex_t]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_matches(self : Ptr[zrex_t], text : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_new(expression : CString): Ptr[zrex_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_strerror(self : Ptr[zrex_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zrex.h
  */
  def zrex_valid(self : Ptr[zrex_t]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_affinity(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_attach(self : Ptr[zsock_t], endpoints : CString, serverish : Boolean): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_backlog(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_bind(self : Ptr[zsock_t], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_bindtodevice(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_brecv(self : Ptr[Byte], picture : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_bsend(self : Ptr[Byte], picture : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_connect(self : Ptr[zsock_t], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_connect_timeout(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_curve_publickey(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_curve_secretkey(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_curve_server(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_curve_serverkey(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_destroy(self_p : Ptr[Ptr[zsock_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_destroy_checked(self_p : Ptr[Ptr[zsock_t]], filename : CString, line_nbr : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_disconnect(self : Ptr[zsock_t], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_endpoint(self : Ptr[zsock_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_events(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_fd(self : Ptr[Byte]): SOCKET = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_flush(self : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_gssapi_plaintext(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_gssapi_principal(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_gssapi_principal_nametype(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_gssapi_server(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_gssapi_service_principal(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_gssapi_service_principal_nametype(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_handshake_ivl(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_heartbeat_ivl(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_heartbeat_timeout(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_heartbeat_ttl(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_hwm(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_identity(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_immediate(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_in_batch_size(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_invert_matching(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_ipv4only(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_ipv6(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_is(self : Ptr[Byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_last_endpoint(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_linger(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_loopback_fastpath(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_maxmsgsize(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_mcast_loop(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_mechanism(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_metadata(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_multicast_hops(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_multicast_loop(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_multicast_maxtpdu(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new(`type` : CInt): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_checked(`type` : CInt, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_dealer(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_dealer_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_pair(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_pair_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_pub(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_pub_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_pull(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_pull_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_push(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_push_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_rep(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_rep_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_req(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_req_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_router(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_router_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_stream(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_stream_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_sub(endpoint : CString, subscribe : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_sub_checked(endpoint : CString, subscribe : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_xpub(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_xpub_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_xsub(endpoint : CString): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_new_xsub_checked(endpoint : CString, filename : CString, line_nbr : size_t): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_out_batch_size(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_plain_password(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_plain_server(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_plain_username(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_priority(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_rate(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_rcvbuf(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_rcvhwm(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_rcvmore(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_rcvtimeo(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_reconnect_ivl(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_reconnect_ivl_max(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_reconnect_stop(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_recovery_ivl(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_recovery_ivl_msec(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_recv(self : Ptr[Byte], picture : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_resolve(self : Ptr[Byte]): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_router_notify(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_send(self : Ptr[Byte], picture : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_affinity(self : Ptr[Byte], affinity : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_backlog(self : Ptr[Byte], backlog : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_bindtodevice(self : Ptr[Byte], bindtodevice : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_conflate(self : Ptr[Byte], conflate : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_connect_rid(self : Ptr[Byte], connect_rid : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_connect_rid_bin(self : Ptr[Byte], connect_rid : Ptr[byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_connect_timeout(self : Ptr[Byte], connect_timeout : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_curve_publickey(self : Ptr[Byte], curve_publickey : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_curve_publickey_bin(self : Ptr[Byte], curve_publickey : Ptr[byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_curve_secretkey(self : Ptr[Byte], curve_secretkey : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_curve_secretkey_bin(self : Ptr[Byte], curve_secretkey : Ptr[byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_curve_server(self : Ptr[Byte], curve_server : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_curve_serverkey(self : Ptr[Byte], curve_serverkey : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_curve_serverkey_bin(self : Ptr[Byte], curve_serverkey : Ptr[byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_delay_attach_on_connect(self : Ptr[Byte], delay_attach_on_connect : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_disconnect_msg(self : Ptr[Byte], disconnect_msg : Ptr[zframe_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_gssapi_plaintext(self : Ptr[Byte], gssapi_plaintext : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_gssapi_principal(self : Ptr[Byte], gssapi_principal : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_gssapi_principal_nametype(self : Ptr[Byte], gssapi_principal_nametype : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_gssapi_server(self : Ptr[Byte], gssapi_server : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_gssapi_service_principal(self : Ptr[Byte], gssapi_service_principal : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_gssapi_service_principal_nametype(self : Ptr[Byte], gssapi_service_principal_nametype : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_handshake_ivl(self : Ptr[Byte], handshake_ivl : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_heartbeat_ivl(self : Ptr[Byte], heartbeat_ivl : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_heartbeat_timeout(self : Ptr[Byte], heartbeat_timeout : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_heartbeat_ttl(self : Ptr[Byte], heartbeat_ttl : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_hello_msg(self : Ptr[Byte], hello_msg : Ptr[zframe_t]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_hwm(self : Ptr[Byte], hwm : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_identity(self : Ptr[Byte], identity : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_immediate(self : Ptr[Byte], immediate : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_in_batch_size(self : Ptr[Byte], in_batch_size : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_invert_matching(self : Ptr[Byte], invert_matching : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_ipv4only(self : Ptr[Byte], ipv4only : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_ipv6(self : Ptr[Byte], ipv6 : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_linger(self : Ptr[Byte], linger : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_loopback_fastpath(self : Ptr[Byte], loopback_fastpath : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_maxmsgsize(self : Ptr[Byte], maxmsgsize : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_mcast_loop(self : Ptr[Byte], mcast_loop : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_metadata(self : Ptr[Byte], metadata : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_multicast_hops(self : Ptr[Byte], multicast_hops : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_multicast_loop(self : Ptr[Byte], multicast_loop : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_multicast_maxtpdu(self : Ptr[Byte], multicast_maxtpdu : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_only_first_subscribe(self : Ptr[Byte], only_first_subscribe : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_out_batch_size(self : Ptr[Byte], out_batch_size : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_plain_password(self : Ptr[Byte], plain_password : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_plain_server(self : Ptr[Byte], plain_server : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_plain_username(self : Ptr[Byte], plain_username : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_priority(self : Ptr[Byte], priority : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_probe_router(self : Ptr[Byte], probe_router : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_rate(self : Ptr[Byte], rate : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_rcvbuf(self : Ptr[Byte], rcvbuf : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_rcvhwm(self : Ptr[Byte], rcvhwm : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_rcvtimeo(self : Ptr[Byte], rcvtimeo : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_reconnect_ivl(self : Ptr[Byte], reconnect_ivl : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_reconnect_ivl_max(self : Ptr[Byte], reconnect_ivl_max : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_reconnect_stop(self : Ptr[Byte], reconnect_stop : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_recovery_ivl(self : Ptr[Byte], recovery_ivl : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_recovery_ivl_msec(self : Ptr[Byte], recovery_ivl_msec : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_req_correlate(self : Ptr[Byte], req_correlate : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_req_relaxed(self : Ptr[Byte], req_relaxed : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_router_handover(self : Ptr[Byte], router_handover : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_router_mandatory(self : Ptr[Byte], router_mandatory : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_router_notify(self : Ptr[Byte], router_notify : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_router_raw(self : Ptr[Byte], router_raw : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_sndbuf(self : Ptr[Byte], sndbuf : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_sndhwm(self : Ptr[Byte], sndhwm : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_sndtimeo(self : Ptr[Byte], sndtimeo : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_socks_password(self : Ptr[Byte], socks_password : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_socks_proxy(self : Ptr[Byte], socks_proxy : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_socks_username(self : Ptr[Byte], socks_username : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_stream_notify(self : Ptr[Byte], stream_notify : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_subscribe(self : Ptr[Byte], subscribe : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_swap(self : Ptr[Byte], swap : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_tcp_accept_filter(self : Ptr[Byte], tcp_accept_filter : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_tcp_keepalive(self : Ptr[Byte], tcp_keepalive : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_tcp_keepalive_cnt(self : Ptr[Byte], tcp_keepalive_cnt : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_tcp_keepalive_idle(self : Ptr[Byte], tcp_keepalive_idle : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_tcp_keepalive_intvl(self : Ptr[Byte], tcp_keepalive_intvl : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_tcp_maxrt(self : Ptr[Byte], tcp_maxrt : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_tos(self : Ptr[Byte], tos : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_unbounded(self : Ptr[Byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_unsubscribe(self : Ptr[Byte], unsubscribe : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_use_fd(self : Ptr[Byte], use_fd : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_vmci_buffer_max_size(self : Ptr[Byte], vmci_buffer_max_size : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_vmci_buffer_min_size(self : Ptr[Byte], vmci_buffer_min_size : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_vmci_buffer_size(self : Ptr[Byte], vmci_buffer_size : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_vmci_connect_timeout(self : Ptr[Byte], vmci_connect_timeout : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_wss_cert_pem(self : Ptr[Byte], wss_cert_pem : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_wss_hostname(self : Ptr[Byte], wss_hostname : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_wss_key_pem(self : Ptr[Byte], wss_key_pem : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_wss_trust_pem(self : Ptr[Byte], wss_trust_pem : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_wss_trust_system(self : Ptr[Byte], wss_trust_system : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_xpub_manual(self : Ptr[Byte], xpub_manual : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_xpub_manual_last_value(self : Ptr[Byte], xpub_manual_last_value : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_xpub_nodrop(self : Ptr[Byte], xpub_nodrop : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_xpub_verbose(self : Ptr[Byte], xpub_verbose : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_xpub_verboser(self : Ptr[Byte], xpub_verboser : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_xpub_welcome_msg(self : Ptr[Byte], xpub_welcome_msg : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_zap_domain(self : Ptr[Byte], zap_domain : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_set_zap_enforce_domain(self : Ptr[Byte], zap_enforce_domain : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_signal(self : Ptr[Byte], status : byte): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_sndbuf(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_sndhwm(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_sndtimeo(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_socks_password(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_socks_proxy(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_socks_username(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_swap(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_tcp_accept_filter(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_tcp_keepalive(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_tcp_keepalive_cnt(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_tcp_keepalive_idle(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_tcp_keepalive_intvl(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_tcp_maxrt(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_thread_safe(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_tos(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_type(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_type_str(self : Ptr[zsock_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_unbind(self : Ptr[zsock_t], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_use_fd(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_vmci_buffer_max_size(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_vmci_buffer_min_size(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_vmci_buffer_size(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_vmci_connect_timeout(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_vrecv(self : Ptr[Byte], picture : CString, argptr : va_list): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_vsend(self : Ptr[Byte], picture : CString, argptr : va_list): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_wait(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_zap_domain(self : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsock.h
  */
  def zsock_zap_enforce_domain(self : Ptr[Byte]): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_free(string_p : Ptr[CString]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_recv(source : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_recv_nowait(source : Ptr[Byte]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_recvx(source : Ptr[Byte], string_p : Ptr[CString], rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_send(dest : Ptr[Byte], string : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_sendf(dest : Ptr[Byte], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_sendfm(dest : Ptr[Byte], format : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_sendm(dest : Ptr[Byte], string : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_sendx(dest : Ptr[Byte], string : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zstr.h
  */
  def zstr_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_auto_use_fd(): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_catch_interrupts(): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_close(handle : Ptr[Byte], filename : CString, line_nbr : size_t): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_create_pipe(backend_p : Ptr[Ptr[zsock_t]]): Ptr[zsock_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_daemonize(workdir : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_debug(format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_dir_change(pathname : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_dir_create(pathname : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_dir_delete(pathname : CString, rest: Any*): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_error(format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_delete(filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_exists(filename : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_mode(filename : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_mode_default(): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_mode_private(): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_modified(filename : CString): time_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_size(filename : CString): ssize_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_file_stable(filename : CString): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_handler_reset(): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_handler_set(handler_fn : Ptr[zsys_handler_fn]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_has_curve(): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_hostname(): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_info(format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_init(): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_interface(): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_ipv6(): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_ipv6_address(): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_ipv6_mcast_address(): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_max_msgsz(): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_notice(format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_pipehwm(): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_run_as(lockfile : CString, group : CString, user : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_auto_use_fd(auto_use_fd : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_interface(value : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_io_threads(io_threads : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_ipv6(ipv6 : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_ipv6_address(value : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_ipv6_mcast_address(value : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_linger(linger : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_logident(value : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_logsender(endpoint : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_logstream(stream : Ptr[FILE]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_logsystem(logsystem : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_max_msgsz(max_msgsz : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_max_sockets(max_sockets : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_pipehwm(pipehwm : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_rcvhwm(rcvhwm : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_sndhwm(sndhwm : size_t): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_thread_name_prefix(prefix : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_thread_priority(priority : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_set_thread_sched_policy(policy : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_shutdown(): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_socket(`type` : CInt, filename : CString, line_nbr : size_t): Ptr[Byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_socket_error(reason : CString): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_socket_limit(): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_sockname(socktype : CInt): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_sprintf(format : CString, rest: Any*): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_test(verbose : Boolean): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_thread_affinity_cpu_add(cpu : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_thread_affinity_cpu_remove(cpu : CInt): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_thread_name_prefix(): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_udp_close(handle : SOCKET): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_udp_new(routable : Boolean): SOCKET = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_udp_recv(udpsock : SOCKET, peername : CString, peerlen : CInt): Ptr[zframe_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_udp_send(udpsock : SOCKET, frame : Ptr[zframe_t], address : Ptr[inaddr_t], addrlen : CInt): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_version(major : Ptr[CInt], minor : Ptr[CInt], patch : Ptr[CInt]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_vprintf(format : CString, argptr : va_list): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zsys.h
  */
  def zsys_warning(format : CString, rest: Any*): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_data(self : Ptr[zuuid_t]): Ptr[byte] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_destroy(self_p : Ptr[Ptr[zuuid_t]]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_dup(self : Ptr[zuuid_t]): Ptr[zuuid_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_eq(self : Ptr[zuuid_t], compare : Ptr[byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_export(self : Ptr[zuuid_t], target : Ptr[byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_neq(self : Ptr[zuuid_t], compare : Ptr[byte]): Boolean = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_new(): Ptr[zuuid_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_new_from(source : Ptr[byte]): Ptr[zuuid_t] = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_set(self : Ptr[zuuid_t], source : Ptr[byte]): Unit = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_set_str(self : Ptr[zuuid_t], source : CString): CInt = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_size(self : Ptr[zuuid_t]): size_t = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_str(self : Ptr[zuuid_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_str_canonical(self : Ptr[zuuid_t]): CString = extern

  /**
   * [bindgen] header: /Users/velvetbaldmime/Library/Caches/sbt-vcpkg/vcpkg/packages/czmq_arm64-osx/include/zuuid.h
  */
  def zuuid_test(verbose : Boolean): Unit = extern


object functions:
  import _root_.czmq.aliases.*
  import _root_.czmq.structs.*

  import extern_functions.*
  export extern_functions.*

object types:
  export _root_.czmq.structs.*
  export _root_.czmq.aliases.*

object all:
  export _root_.czmq.aliases.FILE
  export _root_.czmq.aliases.SOCKET
  export _root_.czmq.aliases.__uint16_t
  export _root_.czmq.aliases.__uint32_t
  export _root_.czmq.aliases.__uint8_t
  export _root_.czmq.aliases.byte
  export _root_.czmq.aliases.czmq_comparator
  export _root_.czmq.aliases.czmq_destructor
  export _root_.czmq.aliases.czmq_duplicator
  export _root_.czmq.aliases.dbyte
  export _root_.czmq.aliases.in6_addr
  export _root_.czmq.aliases.in_addr
  export _root_.czmq.aliases.in_port_t
  export _root_.czmq.aliases.int64_t
  export _root_.czmq.aliases.iovec
  export _root_.czmq.aliases.max_align_t
  export _root_.czmq.aliases.mode_t
  export _root_.czmq.aliases.off_t
  export _root_.czmq.aliases.ptrdiff_t
  export _root_.czmq.aliases.qbyte
  export _root_.czmq.aliases.rsize_t
  export _root_.czmq.aliases.sa_family_t
  export _root_.czmq.aliases.size_t
  export _root_.czmq.aliases.ssize_t
  export _root_.czmq.aliases.time_t
  export _root_.czmq.aliases.uint
  export _root_.czmq.aliases.uint8_t
  export _root_.czmq.aliases.ulong
  export _root_.czmq.aliases.va_list
  export _root_.czmq.aliases.wchar_t
  export _root_.czmq.aliases.zactor_fn
  export _root_.czmq.aliases.zconfig_fct
  export _root_.czmq.aliases.zhash_free_fn
  export _root_.czmq.aliases.zhashx_comparator_fn
  export _root_.czmq.aliases.zhashx_deserializer_fn
  export _root_.czmq.aliases.zhashx_destructor_fn
  export _root_.czmq.aliases.zhashx_duplicator_fn
  export _root_.czmq.aliases.zhashx_free_fn
  export _root_.czmq.aliases.zhashx_hash_fn
  export _root_.czmq.aliases.zhashx_serializer_fn
  export _root_.czmq.aliases.zlist_compare_fn
  export _root_.czmq.aliases.zlist_free_fn
  export _root_.czmq.aliases.zlistx_comparator_fn
  export _root_.czmq.aliases.zlistx_destructor_fn
  export _root_.czmq.aliases.zlistx_duplicator_fn
  export _root_.czmq.aliases.zloop_fn
  export _root_.czmq.aliases.zloop_reader_fn
  export _root_.czmq.aliases.zloop_timer_fn
  export _root_.czmq.aliases.zmq_fd_t
  export _root_.czmq.aliases.zmq_free_fn
  export _root_.czmq.aliases.zmq_thread_fn
  export _root_.czmq.aliases.zmq_timer_fn
  export _root_.czmq.aliases.zsys_handler_fn
  export _root_.czmq.structs._zactor_t
  export _root_.czmq.structs._zarmour_t
  export _root_.czmq.structs._zauth_t
  export _root_.czmq.structs._zbeacon_t
  export _root_.czmq.structs._zcert_t
  export _root_.czmq.structs._zcertstore_t
  export _root_.czmq.structs._zchunk_t
  export _root_.czmq.structs._zclock_t
  export _root_.czmq.structs._zconfig_t
  export _root_.czmq.structs._zdigest_t
  export _root_.czmq.structs._zdir_patch_t
  export _root_.czmq.structs._zdir_t
  export _root_.czmq.structs._zfile_t
  export _root_.czmq.structs._zframe_t
  export _root_.czmq.structs._zgossip_t
  export _root_.czmq.structs._zhash_t
  export _root_.czmq.structs._zhashx_t
  export _root_.czmq.structs._ziflist_t
  export _root_.czmq.structs._zlist_t
  export _root_.czmq.structs._zlistx_t
  export _root_.czmq.structs._zloop_t
  export _root_.czmq.structs._zmonitor_t
  export _root_.czmq.structs._zmsg_t
  export _root_.czmq.structs._zpoller_t
  export _root_.czmq.structs._zproxy_t
  export _root_.czmq.structs._zrex_t
  export _root_.czmq.structs._zsock_t
  export _root_.czmq.structs._zstr_t
  export _root_.czmq.structs._zsys_t
  export _root_.czmq.structs._zuuid_t
  export _root_.czmq.structs.in6addr_t
  export _root_.czmq.structs.inaddr_storage_t
  export _root_.czmq.structs.inaddr_t
  export _root_.czmq.structs.zactor_t
  export _root_.czmq.structs.zarmour_t
  export _root_.czmq.structs.zauth_t
  export _root_.czmq.structs.zbeacon_t
  export _root_.czmq.structs.zcert_t
  export _root_.czmq.structs.zcertstore_t
  export _root_.czmq.structs.zchunk_t
  export _root_.czmq.structs.zclock_t
  export _root_.czmq.structs.zconfig_t
  export _root_.czmq.structs.zdigest_t
  export _root_.czmq.structs.zdir_patch_t
  export _root_.czmq.structs.zdir_t
  export _root_.czmq.structs.zfile_t
  export _root_.czmq.structs.zframe_t
  export _root_.czmq.structs.zgossip_t
  export _root_.czmq.structs.zhash_t
  export _root_.czmq.structs.zhashx_t
  export _root_.czmq.structs.ziflist_t
  export _root_.czmq.structs.zlist_t
  export _root_.czmq.structs.zlistx_t
  export _root_.czmq.structs.zloop_t
  export _root_.czmq.structs.zmonitor_t
  export _root_.czmq.structs.zmq_msg_t
  export _root_.czmq.structs.zmq_pollitem_t
  export _root_.czmq.structs.zmsg_t
  export _root_.czmq.structs.zpoller_t
  export _root_.czmq.structs.zproxy_t
  export _root_.czmq.structs.zrex_t
  export _root_.czmq.structs.zsock_t
  export _root_.czmq.structs.zstr_t
  export _root_.czmq.structs.zsys_t
  export _root_.czmq.structs.zuuid_t
  export _root_.czmq.functions.safe_malloc
  export _root_.czmq.functions.zactor_destroy
  export _root_.czmq.functions.zactor_is
  export _root_.czmq.functions.zactor_new
  export _root_.czmq.functions.zactor_recv
  export _root_.czmq.functions.zactor_resolve
  export _root_.czmq.functions.zactor_send
  export _root_.czmq.functions.zactor_sock
  export _root_.czmq.functions.zactor_test
  export _root_.czmq.functions.zarmour_decode
  export _root_.czmq.functions.zarmour_destroy
  export _root_.czmq.functions.zarmour_encode
  export _root_.czmq.functions.zarmour_line_breaks
  export _root_.czmq.functions.zarmour_line_length
  export _root_.czmq.functions.zarmour_mode
  export _root_.czmq.functions.zarmour_mode_str
  export _root_.czmq.functions.zarmour_new
  export _root_.czmq.functions.zarmour_pad
  export _root_.czmq.functions.zarmour_pad_char
  export _root_.czmq.functions.zarmour_print
  export _root_.czmq.functions.zarmour_set_line_breaks
  export _root_.czmq.functions.zarmour_set_line_length
  export _root_.czmq.functions.zarmour_set_mode
  export _root_.czmq.functions.zarmour_set_pad
  export _root_.czmq.functions.zarmour_set_pad_char
  export _root_.czmq.functions.zarmour_test
  export _root_.czmq.functions.zauth
  export _root_.czmq.functions.zauth_test
  export _root_.czmq.functions.zbeacon
  export _root_.czmq.functions.zbeacon_test
  export _root_.czmq.functions.zcert_apply
  export _root_.czmq.functions.zcert_destroy
  export _root_.czmq.functions.zcert_dup
  export _root_.czmq.functions.zcert_eq
  export _root_.czmq.functions.zcert_load
  export _root_.czmq.functions.zcert_meta
  export _root_.czmq.functions.zcert_meta_keys
  export _root_.czmq.functions.zcert_new
  export _root_.czmq.functions.zcert_new_from
  export _root_.czmq.functions.zcert_print
  export _root_.czmq.functions.zcert_public_key
  export _root_.czmq.functions.zcert_public_txt
  export _root_.czmq.functions.zcert_save
  export _root_.czmq.functions.zcert_save_public
  export _root_.czmq.functions.zcert_save_secret
  export _root_.czmq.functions.zcert_secret_key
  export _root_.czmq.functions.zcert_secret_txt
  export _root_.czmq.functions.zcert_set_meta
  export _root_.czmq.functions.zcert_test
  export _root_.czmq.functions.zcertstore_destroy
  export _root_.czmq.functions.zcertstore_insert
  export _root_.czmq.functions.zcertstore_lookup
  export _root_.czmq.functions.zcertstore_new
  export _root_.czmq.functions.zcertstore_print
  export _root_.czmq.functions.zcertstore_test
  export _root_.czmq.functions.zchunk_append
  export _root_.czmq.functions.zchunk_consume
  export _root_.czmq.functions.zchunk_data
  export _root_.czmq.functions.zchunk_destroy
  export _root_.czmq.functions.zchunk_digest
  export _root_.czmq.functions.zchunk_dup
  export _root_.czmq.functions.zchunk_exhausted
  export _root_.czmq.functions.zchunk_extend
  export _root_.czmq.functions.zchunk_fill
  export _root_.czmq.functions.zchunk_fprint
  export _root_.czmq.functions.zchunk_is
  export _root_.czmq.functions.zchunk_max_size
  export _root_.czmq.functions.zchunk_new
  export _root_.czmq.functions.zchunk_pack
  export _root_.czmq.functions.zchunk_print
  export _root_.czmq.functions.zchunk_read
  export _root_.czmq.functions.zchunk_resize
  export _root_.czmq.functions.zchunk_set
  export _root_.czmq.functions.zchunk_size
  export _root_.czmq.functions.zchunk_slurp
  export _root_.czmq.functions.zchunk_strdup
  export _root_.czmq.functions.zchunk_streq
  export _root_.czmq.functions.zchunk_strhex
  export _root_.czmq.functions.zchunk_test
  export _root_.czmq.functions.zchunk_unpack
  export _root_.czmq.functions.zchunk_write
  export _root_.czmq.functions.zclock_log
  export _root_.czmq.functions.zclock_mono
  export _root_.czmq.functions.zclock_sleep
  export _root_.czmq.functions.zclock_test
  export _root_.czmq.functions.zclock_time
  export _root_.czmq.functions.zclock_timestr
  export _root_.czmq.functions.zclock_usecs
  export _root_.czmq.functions.zconfig_at_depth
  export _root_.czmq.functions.zconfig_child
  export _root_.czmq.functions.zconfig_chunk_load
  export _root_.czmq.functions.zconfig_chunk_save
  export _root_.czmq.functions.zconfig_comments
  export _root_.czmq.functions.zconfig_destroy
  export _root_.czmq.functions.zconfig_execute
  export _root_.czmq.functions.zconfig_filename
  export _root_.czmq.functions.zconfig_fprint
  export _root_.czmq.functions.zconfig_get
  export _root_.czmq.functions.zconfig_has_changed
  export _root_.czmq.functions.zconfig_load
  export _root_.czmq.functions.zconfig_loadf
  export _root_.czmq.functions.zconfig_locate
  export _root_.czmq.functions.zconfig_name
  export _root_.czmq.functions.zconfig_new
  export _root_.czmq.functions.zconfig_next
  export _root_.czmq.functions.zconfig_print
  export _root_.czmq.functions.zconfig_put
  export _root_.czmq.functions.zconfig_putf
  export _root_.czmq.functions.zconfig_reload
  export _root_.czmq.functions.zconfig_save
  export _root_.czmq.functions.zconfig_savef
  export _root_.czmq.functions.zconfig_set_comment
  export _root_.czmq.functions.zconfig_set_name
  export _root_.czmq.functions.zconfig_set_value
  export _root_.czmq.functions.zconfig_str_load
  export _root_.czmq.functions.zconfig_str_save
  export _root_.czmq.functions.zconfig_test
  export _root_.czmq.functions.zconfig_value
  export _root_.czmq.functions.zdigest_data
  export _root_.czmq.functions.zdigest_destroy
  export _root_.czmq.functions.zdigest_new
  export _root_.czmq.functions.zdigest_size
  export _root_.czmq.functions.zdigest_string
  export _root_.czmq.functions.zdigest_test
  export _root_.czmq.functions.zdigest_update
  export _root_.czmq.functions.zdir_cache
  export _root_.czmq.functions.zdir_count
  export _root_.czmq.functions.zdir_cursize
  export _root_.czmq.functions.zdir_destroy
  export _root_.czmq.functions.zdir_diff
  export _root_.czmq.functions.zdir_flatten
  export _root_.czmq.functions.zdir_flatten_free
  export _root_.czmq.functions.zdir_fprint
  export _root_.czmq.functions.zdir_list
  export _root_.czmq.functions.zdir_modified
  export _root_.czmq.functions.zdir_new
  export _root_.czmq.functions.zdir_patch_destroy
  export _root_.czmq.functions.zdir_patch_digest
  export _root_.czmq.functions.zdir_patch_digest_set
  export _root_.czmq.functions.zdir_patch_dup
  export _root_.czmq.functions.zdir_patch_file
  export _root_.czmq.functions.zdir_patch_new
  export _root_.czmq.functions.zdir_patch_op
  export _root_.czmq.functions.zdir_patch_path
  export _root_.czmq.functions.zdir_patch_test
  export _root_.czmq.functions.zdir_patch_vpath
  export _root_.czmq.functions.zdir_path
  export _root_.czmq.functions.zdir_print
  export _root_.czmq.functions.zdir_remove
  export _root_.czmq.functions.zdir_resync
  export _root_.czmq.functions.zdir_test
  export _root_.czmq.functions.zdir_watch
  export _root_.czmq.functions.zfile_close
  export _root_.czmq.functions.zfile_cursize
  export _root_.czmq.functions.zfile_delete
  export _root_.czmq.functions.zfile_destroy
  export _root_.czmq.functions.zfile_digest
  export _root_.czmq.functions.zfile_dup
  export _root_.czmq.functions.zfile_eof
  export _root_.czmq.functions.zfile_exists
  export _root_.czmq.functions.zfile_filename
  export _root_.czmq.functions.zfile_handle
  export _root_.czmq.functions.zfile_has_changed
  export _root_.czmq.functions.zfile_input
  export _root_.czmq.functions.zfile_is_directory
  export _root_.czmq.functions.zfile_is_readable
  export _root_.czmq.functions.zfile_is_regular
  export _root_.czmq.functions.zfile_is_stable
  export _root_.czmq.functions.zfile_is_writeable
  export _root_.czmq.functions.zfile_mkdir
  export _root_.czmq.functions.zfile_mode
  export _root_.czmq.functions.zfile_mode_default
  export _root_.czmq.functions.zfile_mode_private
  export _root_.czmq.functions.zfile_modified
  export _root_.czmq.functions.zfile_new
  export _root_.czmq.functions.zfile_output
  export _root_.czmq.functions.zfile_read
  export _root_.czmq.functions.zfile_readln
  export _root_.czmq.functions.zfile_remove
  export _root_.czmq.functions.zfile_restat
  export _root_.czmq.functions.zfile_rmdir
  export _root_.czmq.functions.zfile_size
  export _root_.czmq.functions.zfile_stable
  export _root_.czmq.functions.zfile_test
  export _root_.czmq.functions.zfile_write
  export _root_.czmq.functions.zframe_data
  export _root_.czmq.functions.zframe_destroy
  export _root_.czmq.functions.zframe_dup
  export _root_.czmq.functions.zframe_eq
  export _root_.czmq.functions.zframe_fprint
  export _root_.czmq.functions.zframe_from
  export _root_.czmq.functions.zframe_is
  export _root_.czmq.functions.zframe_meta
  export _root_.czmq.functions.zframe_more
  export _root_.czmq.functions.zframe_new
  export _root_.czmq.functions.zframe_new_empty
  export _root_.czmq.functions.zframe_print
  export _root_.czmq.functions.zframe_recv
  export _root_.czmq.functions.zframe_recv_nowait
  export _root_.czmq.functions.zframe_reset
  export _root_.czmq.functions.zframe_send
  export _root_.czmq.functions.zframe_set_more
  export _root_.czmq.functions.zframe_size
  export _root_.czmq.functions.zframe_strdup
  export _root_.czmq.functions.zframe_streq
  export _root_.czmq.functions.zframe_strhex
  export _root_.czmq.functions.zframe_test
  export _root_.czmq.functions.zgossip
  export _root_.czmq.functions.zgossip_test
  export _root_.czmq.functions.zhash_autofree
  export _root_.czmq.functions.zhash_comment
  export _root_.czmq.functions.zhash_cursor
  export _root_.czmq.functions.zhash_delete
  export _root_.czmq.functions.zhash_destroy
  export _root_.czmq.functions.zhash_dup
  export _root_.czmq.functions.zhash_first
  export _root_.czmq.functions.zhash_freefn
  export _root_.czmq.functions.zhash_insert
  export _root_.czmq.functions.zhash_keys
  export _root_.czmq.functions.zhash_load
  export _root_.czmq.functions.zhash_lookup
  export _root_.czmq.functions.zhash_new
  export _root_.czmq.functions.zhash_next
  export _root_.czmq.functions.zhash_pack
  export _root_.czmq.functions.zhash_refresh
  export _root_.czmq.functions.zhash_rename
  export _root_.czmq.functions.zhash_save
  export _root_.czmq.functions.zhash_size
  export _root_.czmq.functions.zhash_test
  export _root_.czmq.functions.zhash_unpack
  export _root_.czmq.functions.zhash_update
  export _root_.czmq.functions.zhashx_comment
  export _root_.czmq.functions.zhashx_cursor
  export _root_.czmq.functions.zhashx_delete
  export _root_.czmq.functions.zhashx_destroy
  export _root_.czmq.functions.zhashx_dup
  export _root_.czmq.functions.zhashx_dup_v2
  export _root_.czmq.functions.zhashx_first
  export _root_.czmq.functions.zhashx_freefn
  export _root_.czmq.functions.zhashx_insert
  export _root_.czmq.functions.zhashx_keys
  export _root_.czmq.functions.zhashx_load
  export _root_.czmq.functions.zhashx_lookup
  export _root_.czmq.functions.zhashx_new
  export _root_.czmq.functions.zhashx_next
  export _root_.czmq.functions.zhashx_pack
  export _root_.czmq.functions.zhashx_purge
  export _root_.czmq.functions.zhashx_refresh
  export _root_.czmq.functions.zhashx_rename
  export _root_.czmq.functions.zhashx_save
  export _root_.czmq.functions.zhashx_set_destructor
  export _root_.czmq.functions.zhashx_set_duplicator
  export _root_.czmq.functions.zhashx_set_key_comparator
  export _root_.czmq.functions.zhashx_set_key_destructor
  export _root_.czmq.functions.zhashx_set_key_duplicator
  export _root_.czmq.functions.zhashx_set_key_hasher
  export _root_.czmq.functions.zhashx_size
  export _root_.czmq.functions.zhashx_test
  export _root_.czmq.functions.zhashx_unpack
  export _root_.czmq.functions.zhashx_update
  export _root_.czmq.functions.zhashx_values
  export _root_.czmq.functions.ziflist_address
  export _root_.czmq.functions.ziflist_broadcast
  export _root_.czmq.functions.ziflist_destroy
  export _root_.czmq.functions.ziflist_first
  export _root_.czmq.functions.ziflist_netmask
  export _root_.czmq.functions.ziflist_new
  export _root_.czmq.functions.ziflist_next
  export _root_.czmq.functions.ziflist_print
  export _root_.czmq.functions.ziflist_reload
  export _root_.czmq.functions.ziflist_size
  export _root_.czmq.functions.ziflist_test
  export _root_.czmq.functions.zlist_append
  export _root_.czmq.functions.zlist_autofree
  export _root_.czmq.functions.zlist_comparefn
  export _root_.czmq.functions.zlist_destroy
  export _root_.czmq.functions.zlist_dup
  export _root_.czmq.functions.zlist_exists
  export _root_.czmq.functions.zlist_first
  export _root_.czmq.functions.zlist_freefn
  export _root_.czmq.functions.zlist_head
  export _root_.czmq.functions.zlist_item
  export _root_.czmq.functions.zlist_last
  export _root_.czmq.functions.zlist_new
  export _root_.czmq.functions.zlist_next
  export _root_.czmq.functions.zlist_pop
  export _root_.czmq.functions.zlist_purge
  export _root_.czmq.functions.zlist_push
  export _root_.czmq.functions.zlist_remove
  export _root_.czmq.functions.zlist_size
  export _root_.czmq.functions.zlist_sort
  export _root_.czmq.functions.zlist_tail
  export _root_.czmq.functions.zlist_test
  export _root_.czmq.functions.zlistx_add_end
  export _root_.czmq.functions.zlistx_add_start
  export _root_.czmq.functions.zlistx_cursor
  export _root_.czmq.functions.zlistx_delete
  export _root_.czmq.functions.zlistx_destroy
  export _root_.czmq.functions.zlistx_detach
  export _root_.czmq.functions.zlistx_detach_cur
  export _root_.czmq.functions.zlistx_dup
  export _root_.czmq.functions.zlistx_find
  export _root_.czmq.functions.zlistx_first
  export _root_.czmq.functions.zlistx_handle_item
  export _root_.czmq.functions.zlistx_head
  export _root_.czmq.functions.zlistx_insert
  export _root_.czmq.functions.zlistx_item
  export _root_.czmq.functions.zlistx_last
  export _root_.czmq.functions.zlistx_move_end
  export _root_.czmq.functions.zlistx_move_start
  export _root_.czmq.functions.zlistx_new
  export _root_.czmq.functions.zlistx_next
  export _root_.czmq.functions.zlistx_prev
  export _root_.czmq.functions.zlistx_purge
  export _root_.czmq.functions.zlistx_reorder
  export _root_.czmq.functions.zlistx_set_comparator
  export _root_.czmq.functions.zlistx_set_destructor
  export _root_.czmq.functions.zlistx_set_duplicator
  export _root_.czmq.functions.zlistx_size
  export _root_.czmq.functions.zlistx_sort
  export _root_.czmq.functions.zlistx_tail
  export _root_.czmq.functions.zlistx_test
  export _root_.czmq.functions.zloop_destroy
  export _root_.czmq.functions.zloop_new
  export _root_.czmq.functions.zloop_poller
  export _root_.czmq.functions.zloop_poller_end
  export _root_.czmq.functions.zloop_poller_set_tolerant
  export _root_.czmq.functions.zloop_reader
  export _root_.czmq.functions.zloop_reader_end
  export _root_.czmq.functions.zloop_reader_set_tolerant
  export _root_.czmq.functions.zloop_set_max_timers
  export _root_.czmq.functions.zloop_set_nonstop
  export _root_.czmq.functions.zloop_set_ticket_delay
  export _root_.czmq.functions.zloop_set_verbose
  export _root_.czmq.functions.zloop_start
  export _root_.czmq.functions.zloop_test
  export _root_.czmq.functions.zloop_ticket
  export _root_.czmq.functions.zloop_ticket_delete
  export _root_.czmq.functions.zloop_ticket_reset
  export _root_.czmq.functions.zloop_timer
  export _root_.czmq.functions.zloop_timer_end
  export _root_.czmq.functions.zmonitor
  export _root_.czmq.functions.zmonitor_test
  export _root_.czmq.functions.zmq_atomic_counter_dec
  export _root_.czmq.functions.zmq_atomic_counter_destroy
  export _root_.czmq.functions.zmq_atomic_counter_inc
  export _root_.czmq.functions.zmq_atomic_counter_new
  export _root_.czmq.functions.zmq_atomic_counter_set
  export _root_.czmq.functions.zmq_atomic_counter_value
  export _root_.czmq.functions.zmq_bind
  export _root_.czmq.functions.zmq_close
  export _root_.czmq.functions.zmq_connect
  export _root_.czmq.functions.zmq_ctx_destroy
  export _root_.czmq.functions.zmq_ctx_get
  export _root_.czmq.functions.zmq_ctx_new
  export _root_.czmq.functions.zmq_ctx_set
  export _root_.czmq.functions.zmq_ctx_shutdown
  export _root_.czmq.functions.zmq_ctx_term
  export _root_.czmq.functions.zmq_curve_keypair
  export _root_.czmq.functions.zmq_curve_public
  export _root_.czmq.functions.zmq_device
  export _root_.czmq.functions.zmq_disconnect
  export _root_.czmq.functions.zmq_errno
  export _root_.czmq.functions.zmq_getsockopt
  export _root_.czmq.functions.zmq_has
  export _root_.czmq.functions.zmq_init
  export _root_.czmq.functions.zmq_msg_close
  export _root_.czmq.functions.zmq_msg_copy
  export _root_.czmq.functions.zmq_msg_data
  export _root_.czmq.functions.zmq_msg_get
  export _root_.czmq.functions.zmq_msg_gets
  export _root_.czmq.functions.zmq_msg_init
  export _root_.czmq.functions.zmq_msg_init_data
  export _root_.czmq.functions.zmq_msg_init_size
  export _root_.czmq.functions.zmq_msg_more
  export _root_.czmq.functions.zmq_msg_move
  export _root_.czmq.functions.zmq_msg_recv
  export _root_.czmq.functions.zmq_msg_send
  export _root_.czmq.functions.zmq_msg_set
  export _root_.czmq.functions.zmq_msg_size
  export _root_.czmq.functions.zmq_poll
  export _root_.czmq.functions.zmq_proxy
  export _root_.czmq.functions.zmq_proxy_steerable
  export _root_.czmq.functions.zmq_recv
  export _root_.czmq.functions.zmq_recviov
  export _root_.czmq.functions.zmq_recvmsg
  export _root_.czmq.functions.zmq_send
  export _root_.czmq.functions.zmq_send_const
  export _root_.czmq.functions.zmq_sendiov
  export _root_.czmq.functions.zmq_sendmsg
  export _root_.czmq.functions.zmq_setsockopt
  export _root_.czmq.functions.zmq_sleep
  export _root_.czmq.functions.zmq_socket
  export _root_.czmq.functions.zmq_socket_monitor
  export _root_.czmq.functions.zmq_stopwatch_intermediate
  export _root_.czmq.functions.zmq_stopwatch_start
  export _root_.czmq.functions.zmq_stopwatch_stop
  export _root_.czmq.functions.zmq_strerror
  export _root_.czmq.functions.zmq_term
  export _root_.czmq.functions.zmq_threadclose
  export _root_.czmq.functions.zmq_threadstart
  export _root_.czmq.functions.zmq_timers_add
  export _root_.czmq.functions.zmq_timers_cancel
  export _root_.czmq.functions.zmq_timers_destroy
  export _root_.czmq.functions.zmq_timers_execute
  export _root_.czmq.functions.zmq_timers_new
  export _root_.czmq.functions.zmq_timers_reset
  export _root_.czmq.functions.zmq_timers_set_interval
  export _root_.czmq.functions.zmq_timers_timeout
  export _root_.czmq.functions.zmq_unbind
  export _root_.czmq.functions.zmq_version
  export _root_.czmq.functions.zmq_z85_decode
  export _root_.czmq.functions.zmq_z85_encode
  export _root_.czmq.functions.zmsg_add
  export _root_.czmq.functions.zmsg_addmem
  export _root_.czmq.functions.zmsg_addmsg
  export _root_.czmq.functions.zmsg_addstr
  export _root_.czmq.functions.zmsg_addstrf
  export _root_.czmq.functions.zmsg_append
  export _root_.czmq.functions.zmsg_content_size
  export _root_.czmq.functions.zmsg_decode
  export _root_.czmq.functions.zmsg_destroy
  export _root_.czmq.functions.zmsg_dup
  export _root_.czmq.functions.zmsg_encode
  export _root_.czmq.functions.zmsg_eq
  export _root_.czmq.functions.zmsg_first
  export _root_.czmq.functions.zmsg_fprint
  export _root_.czmq.functions.zmsg_is
  export _root_.czmq.functions.zmsg_last
  export _root_.czmq.functions.zmsg_load
  export _root_.czmq.functions.zmsg_new
  export _root_.czmq.functions.zmsg_new_signal
  export _root_.czmq.functions.zmsg_next
  export _root_.czmq.functions.zmsg_pop
  export _root_.czmq.functions.zmsg_popmsg
  export _root_.czmq.functions.zmsg_popstr
  export _root_.czmq.functions.zmsg_prepend
  export _root_.czmq.functions.zmsg_print
  export _root_.czmq.functions.zmsg_push
  export _root_.czmq.functions.zmsg_pushmem
  export _root_.czmq.functions.zmsg_pushstr
  export _root_.czmq.functions.zmsg_pushstrf
  export _root_.czmq.functions.zmsg_recv
  export _root_.czmq.functions.zmsg_recv_nowait
  export _root_.czmq.functions.zmsg_remove
  export _root_.czmq.functions.zmsg_save
  export _root_.czmq.functions.zmsg_send
  export _root_.czmq.functions.zmsg_sendm
  export _root_.czmq.functions.zmsg_signal
  export _root_.czmq.functions.zmsg_size
  export _root_.czmq.functions.zmsg_test
  export _root_.czmq.functions.zmsg_unwrap
  export _root_.czmq.functions.zmsg_wrap
  export _root_.czmq.functions.zpoller_add
  export _root_.czmq.functions.zpoller_destroy
  export _root_.czmq.functions.zpoller_expired
  export _root_.czmq.functions.zpoller_new
  export _root_.czmq.functions.zpoller_remove
  export _root_.czmq.functions.zpoller_set_nonstop
  export _root_.czmq.functions.zpoller_terminated
  export _root_.czmq.functions.zpoller_test
  export _root_.czmq.functions.zpoller_wait
  export _root_.czmq.functions.zproxy
  export _root_.czmq.functions.zproxy_test
  export _root_.czmq.functions.zrex_destroy
  export _root_.czmq.functions.zrex_eq
  export _root_.czmq.functions.zrex_fetch
  export _root_.czmq.functions.zrex_hit
  export _root_.czmq.functions.zrex_hits
  export _root_.czmq.functions.zrex_matches
  export _root_.czmq.functions.zrex_new
  export _root_.czmq.functions.zrex_strerror
  export _root_.czmq.functions.zrex_test
  export _root_.czmq.functions.zrex_valid
  export _root_.czmq.functions.zsock_affinity
  export _root_.czmq.functions.zsock_attach
  export _root_.czmq.functions.zsock_backlog
  export _root_.czmq.functions.zsock_bind
  export _root_.czmq.functions.zsock_bindtodevice
  export _root_.czmq.functions.zsock_brecv
  export _root_.czmq.functions.zsock_bsend
  export _root_.czmq.functions.zsock_connect
  export _root_.czmq.functions.zsock_connect_timeout
  export _root_.czmq.functions.zsock_curve_publickey
  export _root_.czmq.functions.zsock_curve_secretkey
  export _root_.czmq.functions.zsock_curve_server
  export _root_.czmq.functions.zsock_curve_serverkey
  export _root_.czmq.functions.zsock_destroy
  export _root_.czmq.functions.zsock_destroy_checked
  export _root_.czmq.functions.zsock_disconnect
  export _root_.czmq.functions.zsock_endpoint
  export _root_.czmq.functions.zsock_events
  export _root_.czmq.functions.zsock_fd
  export _root_.czmq.functions.zsock_flush
  export _root_.czmq.functions.zsock_gssapi_plaintext
  export _root_.czmq.functions.zsock_gssapi_principal
  export _root_.czmq.functions.zsock_gssapi_principal_nametype
  export _root_.czmq.functions.zsock_gssapi_server
  export _root_.czmq.functions.zsock_gssapi_service_principal
  export _root_.czmq.functions.zsock_gssapi_service_principal_nametype
  export _root_.czmq.functions.zsock_handshake_ivl
  export _root_.czmq.functions.zsock_heartbeat_ivl
  export _root_.czmq.functions.zsock_heartbeat_timeout
  export _root_.czmq.functions.zsock_heartbeat_ttl
  export _root_.czmq.functions.zsock_hwm
  export _root_.czmq.functions.zsock_identity
  export _root_.czmq.functions.zsock_immediate
  export _root_.czmq.functions.zsock_in_batch_size
  export _root_.czmq.functions.zsock_invert_matching
  export _root_.czmq.functions.zsock_ipv4only
  export _root_.czmq.functions.zsock_ipv6
  export _root_.czmq.functions.zsock_is
  export _root_.czmq.functions.zsock_last_endpoint
  export _root_.czmq.functions.zsock_linger
  export _root_.czmq.functions.zsock_loopback_fastpath
  export _root_.czmq.functions.zsock_maxmsgsize
  export _root_.czmq.functions.zsock_mcast_loop
  export _root_.czmq.functions.zsock_mechanism
  export _root_.czmq.functions.zsock_metadata
  export _root_.czmq.functions.zsock_multicast_hops
  export _root_.czmq.functions.zsock_multicast_loop
  export _root_.czmq.functions.zsock_multicast_maxtpdu
  export _root_.czmq.functions.zsock_new
  export _root_.czmq.functions.zsock_new_checked
  export _root_.czmq.functions.zsock_new_dealer
  export _root_.czmq.functions.zsock_new_dealer_checked
  export _root_.czmq.functions.zsock_new_pair
  export _root_.czmq.functions.zsock_new_pair_checked
  export _root_.czmq.functions.zsock_new_pub
  export _root_.czmq.functions.zsock_new_pub_checked
  export _root_.czmq.functions.zsock_new_pull
  export _root_.czmq.functions.zsock_new_pull_checked
  export _root_.czmq.functions.zsock_new_push
  export _root_.czmq.functions.zsock_new_push_checked
  export _root_.czmq.functions.zsock_new_rep
  export _root_.czmq.functions.zsock_new_rep_checked
  export _root_.czmq.functions.zsock_new_req
  export _root_.czmq.functions.zsock_new_req_checked
  export _root_.czmq.functions.zsock_new_router
  export _root_.czmq.functions.zsock_new_router_checked
  export _root_.czmq.functions.zsock_new_stream
  export _root_.czmq.functions.zsock_new_stream_checked
  export _root_.czmq.functions.zsock_new_sub
  export _root_.czmq.functions.zsock_new_sub_checked
  export _root_.czmq.functions.zsock_new_xpub
  export _root_.czmq.functions.zsock_new_xpub_checked
  export _root_.czmq.functions.zsock_new_xsub
  export _root_.czmq.functions.zsock_new_xsub_checked
  export _root_.czmq.functions.zsock_out_batch_size
  export _root_.czmq.functions.zsock_plain_password
  export _root_.czmq.functions.zsock_plain_server
  export _root_.czmq.functions.zsock_plain_username
  export _root_.czmq.functions.zsock_priority
  export _root_.czmq.functions.zsock_rate
  export _root_.czmq.functions.zsock_rcvbuf
  export _root_.czmq.functions.zsock_rcvhwm
  export _root_.czmq.functions.zsock_rcvmore
  export _root_.czmq.functions.zsock_rcvtimeo
  export _root_.czmq.functions.zsock_reconnect_ivl
  export _root_.czmq.functions.zsock_reconnect_ivl_max
  export _root_.czmq.functions.zsock_reconnect_stop
  export _root_.czmq.functions.zsock_recovery_ivl
  export _root_.czmq.functions.zsock_recovery_ivl_msec
  export _root_.czmq.functions.zsock_recv
  export _root_.czmq.functions.zsock_resolve
  export _root_.czmq.functions.zsock_router_notify
  export _root_.czmq.functions.zsock_send
  export _root_.czmq.functions.zsock_set_affinity
  export _root_.czmq.functions.zsock_set_backlog
  export _root_.czmq.functions.zsock_set_bindtodevice
  export _root_.czmq.functions.zsock_set_conflate
  export _root_.czmq.functions.zsock_set_connect_rid
  export _root_.czmq.functions.zsock_set_connect_rid_bin
  export _root_.czmq.functions.zsock_set_connect_timeout
  export _root_.czmq.functions.zsock_set_curve_publickey
  export _root_.czmq.functions.zsock_set_curve_publickey_bin
  export _root_.czmq.functions.zsock_set_curve_secretkey
  export _root_.czmq.functions.zsock_set_curve_secretkey_bin
  export _root_.czmq.functions.zsock_set_curve_server
  export _root_.czmq.functions.zsock_set_curve_serverkey
  export _root_.czmq.functions.zsock_set_curve_serverkey_bin
  export _root_.czmq.functions.zsock_set_delay_attach_on_connect
  export _root_.czmq.functions.zsock_set_disconnect_msg
  export _root_.czmq.functions.zsock_set_gssapi_plaintext
  export _root_.czmq.functions.zsock_set_gssapi_principal
  export _root_.czmq.functions.zsock_set_gssapi_principal_nametype
  export _root_.czmq.functions.zsock_set_gssapi_server
  export _root_.czmq.functions.zsock_set_gssapi_service_principal
  export _root_.czmq.functions.zsock_set_gssapi_service_principal_nametype
  export _root_.czmq.functions.zsock_set_handshake_ivl
  export _root_.czmq.functions.zsock_set_heartbeat_ivl
  export _root_.czmq.functions.zsock_set_heartbeat_timeout
  export _root_.czmq.functions.zsock_set_heartbeat_ttl
  export _root_.czmq.functions.zsock_set_hello_msg
  export _root_.czmq.functions.zsock_set_hwm
  export _root_.czmq.functions.zsock_set_identity
  export _root_.czmq.functions.zsock_set_immediate
  export _root_.czmq.functions.zsock_set_in_batch_size
  export _root_.czmq.functions.zsock_set_invert_matching
  export _root_.czmq.functions.zsock_set_ipv4only
  export _root_.czmq.functions.zsock_set_ipv6
  export _root_.czmq.functions.zsock_set_linger
  export _root_.czmq.functions.zsock_set_loopback_fastpath
  export _root_.czmq.functions.zsock_set_maxmsgsize
  export _root_.czmq.functions.zsock_set_mcast_loop
  export _root_.czmq.functions.zsock_set_metadata
  export _root_.czmq.functions.zsock_set_multicast_hops
  export _root_.czmq.functions.zsock_set_multicast_loop
  export _root_.czmq.functions.zsock_set_multicast_maxtpdu
  export _root_.czmq.functions.zsock_set_only_first_subscribe
  export _root_.czmq.functions.zsock_set_out_batch_size
  export _root_.czmq.functions.zsock_set_plain_password
  export _root_.czmq.functions.zsock_set_plain_server
  export _root_.czmq.functions.zsock_set_plain_username
  export _root_.czmq.functions.zsock_set_priority
  export _root_.czmq.functions.zsock_set_probe_router
  export _root_.czmq.functions.zsock_set_rate
  export _root_.czmq.functions.zsock_set_rcvbuf
  export _root_.czmq.functions.zsock_set_rcvhwm
  export _root_.czmq.functions.zsock_set_rcvtimeo
  export _root_.czmq.functions.zsock_set_reconnect_ivl
  export _root_.czmq.functions.zsock_set_reconnect_ivl_max
  export _root_.czmq.functions.zsock_set_reconnect_stop
  export _root_.czmq.functions.zsock_set_recovery_ivl
  export _root_.czmq.functions.zsock_set_recovery_ivl_msec
  export _root_.czmq.functions.zsock_set_req_correlate
  export _root_.czmq.functions.zsock_set_req_relaxed
  export _root_.czmq.functions.zsock_set_router_handover
  export _root_.czmq.functions.zsock_set_router_mandatory
  export _root_.czmq.functions.zsock_set_router_notify
  export _root_.czmq.functions.zsock_set_router_raw
  export _root_.czmq.functions.zsock_set_sndbuf
  export _root_.czmq.functions.zsock_set_sndhwm
  export _root_.czmq.functions.zsock_set_sndtimeo
  export _root_.czmq.functions.zsock_set_socks_password
  export _root_.czmq.functions.zsock_set_socks_proxy
  export _root_.czmq.functions.zsock_set_socks_username
  export _root_.czmq.functions.zsock_set_stream_notify
  export _root_.czmq.functions.zsock_set_subscribe
  export _root_.czmq.functions.zsock_set_swap
  export _root_.czmq.functions.zsock_set_tcp_accept_filter
  export _root_.czmq.functions.zsock_set_tcp_keepalive
  export _root_.czmq.functions.zsock_set_tcp_keepalive_cnt
  export _root_.czmq.functions.zsock_set_tcp_keepalive_idle
  export _root_.czmq.functions.zsock_set_tcp_keepalive_intvl
  export _root_.czmq.functions.zsock_set_tcp_maxrt
  export _root_.czmq.functions.zsock_set_tos
  export _root_.czmq.functions.zsock_set_unbounded
  export _root_.czmq.functions.zsock_set_unsubscribe
  export _root_.czmq.functions.zsock_set_use_fd
  export _root_.czmq.functions.zsock_set_vmci_buffer_max_size
  export _root_.czmq.functions.zsock_set_vmci_buffer_min_size
  export _root_.czmq.functions.zsock_set_vmci_buffer_size
  export _root_.czmq.functions.zsock_set_vmci_connect_timeout
  export _root_.czmq.functions.zsock_set_wss_cert_pem
  export _root_.czmq.functions.zsock_set_wss_hostname
  export _root_.czmq.functions.zsock_set_wss_key_pem
  export _root_.czmq.functions.zsock_set_wss_trust_pem
  export _root_.czmq.functions.zsock_set_wss_trust_system
  export _root_.czmq.functions.zsock_set_xpub_manual
  export _root_.czmq.functions.zsock_set_xpub_manual_last_value
  export _root_.czmq.functions.zsock_set_xpub_nodrop
  export _root_.czmq.functions.zsock_set_xpub_verbose
  export _root_.czmq.functions.zsock_set_xpub_verboser
  export _root_.czmq.functions.zsock_set_xpub_welcome_msg
  export _root_.czmq.functions.zsock_set_zap_domain
  export _root_.czmq.functions.zsock_set_zap_enforce_domain
  export _root_.czmq.functions.zsock_signal
  export _root_.czmq.functions.zsock_sndbuf
  export _root_.czmq.functions.zsock_sndhwm
  export _root_.czmq.functions.zsock_sndtimeo
  export _root_.czmq.functions.zsock_socks_password
  export _root_.czmq.functions.zsock_socks_proxy
  export _root_.czmq.functions.zsock_socks_username
  export _root_.czmq.functions.zsock_swap
  export _root_.czmq.functions.zsock_tcp_accept_filter
  export _root_.czmq.functions.zsock_tcp_keepalive
  export _root_.czmq.functions.zsock_tcp_keepalive_cnt
  export _root_.czmq.functions.zsock_tcp_keepalive_idle
  export _root_.czmq.functions.zsock_tcp_keepalive_intvl
  export _root_.czmq.functions.zsock_tcp_maxrt
  export _root_.czmq.functions.zsock_test
  export _root_.czmq.functions.zsock_thread_safe
  export _root_.czmq.functions.zsock_tos
  export _root_.czmq.functions.zsock_type
  export _root_.czmq.functions.zsock_type_str
  export _root_.czmq.functions.zsock_unbind
  export _root_.czmq.functions.zsock_use_fd
  export _root_.czmq.functions.zsock_vmci_buffer_max_size
  export _root_.czmq.functions.zsock_vmci_buffer_min_size
  export _root_.czmq.functions.zsock_vmci_buffer_size
  export _root_.czmq.functions.zsock_vmci_connect_timeout
  export _root_.czmq.functions.zsock_vrecv
  export _root_.czmq.functions.zsock_vsend
  export _root_.czmq.functions.zsock_wait
  export _root_.czmq.functions.zsock_zap_domain
  export _root_.czmq.functions.zsock_zap_enforce_domain
  export _root_.czmq.functions.zstr_free
  export _root_.czmq.functions.zstr_recv
  export _root_.czmq.functions.zstr_recv_nowait
  export _root_.czmq.functions.zstr_recvx
  export _root_.czmq.functions.zstr_send
  export _root_.czmq.functions.zstr_sendf
  export _root_.czmq.functions.zstr_sendfm
  export _root_.czmq.functions.zstr_sendm
  export _root_.czmq.functions.zstr_sendx
  export _root_.czmq.functions.zstr_test
  export _root_.czmq.functions.zsys_auto_use_fd
  export _root_.czmq.functions.zsys_catch_interrupts
  export _root_.czmq.functions.zsys_close
  export _root_.czmq.functions.zsys_create_pipe
  export _root_.czmq.functions.zsys_daemonize
  export _root_.czmq.functions.zsys_debug
  export _root_.czmq.functions.zsys_dir_change
  export _root_.czmq.functions.zsys_dir_create
  export _root_.czmq.functions.zsys_dir_delete
  export _root_.czmq.functions.zsys_error
  export _root_.czmq.functions.zsys_file_delete
  export _root_.czmq.functions.zsys_file_exists
  export _root_.czmq.functions.zsys_file_mode
  export _root_.czmq.functions.zsys_file_mode_default
  export _root_.czmq.functions.zsys_file_mode_private
  export _root_.czmq.functions.zsys_file_modified
  export _root_.czmq.functions.zsys_file_size
  export _root_.czmq.functions.zsys_file_stable
  export _root_.czmq.functions.zsys_handler_reset
  export _root_.czmq.functions.zsys_handler_set
  export _root_.czmq.functions.zsys_has_curve
  export _root_.czmq.functions.zsys_hostname
  export _root_.czmq.functions.zsys_info
  export _root_.czmq.functions.zsys_init
  export _root_.czmq.functions.zsys_interface
  export _root_.czmq.functions.zsys_ipv6
  export _root_.czmq.functions.zsys_ipv6_address
  export _root_.czmq.functions.zsys_ipv6_mcast_address
  export _root_.czmq.functions.zsys_max_msgsz
  export _root_.czmq.functions.zsys_notice
  export _root_.czmq.functions.zsys_pipehwm
  export _root_.czmq.functions.zsys_run_as
  export _root_.czmq.functions.zsys_set_auto_use_fd
  export _root_.czmq.functions.zsys_set_interface
  export _root_.czmq.functions.zsys_set_io_threads
  export _root_.czmq.functions.zsys_set_ipv6
  export _root_.czmq.functions.zsys_set_ipv6_address
  export _root_.czmq.functions.zsys_set_ipv6_mcast_address
  export _root_.czmq.functions.zsys_set_linger
  export _root_.czmq.functions.zsys_set_logident
  export _root_.czmq.functions.zsys_set_logsender
  export _root_.czmq.functions.zsys_set_logstream
  export _root_.czmq.functions.zsys_set_logsystem
  export _root_.czmq.functions.zsys_set_max_msgsz
  export _root_.czmq.functions.zsys_set_max_sockets
  export _root_.czmq.functions.zsys_set_pipehwm
  export _root_.czmq.functions.zsys_set_rcvhwm
  export _root_.czmq.functions.zsys_set_sndhwm
  export _root_.czmq.functions.zsys_set_thread_name_prefix
  export _root_.czmq.functions.zsys_set_thread_priority
  export _root_.czmq.functions.zsys_set_thread_sched_policy
  export _root_.czmq.functions.zsys_shutdown
  export _root_.czmq.functions.zsys_socket
  export _root_.czmq.functions.zsys_socket_error
  export _root_.czmq.functions.zsys_socket_limit
  export _root_.czmq.functions.zsys_sockname
  export _root_.czmq.functions.zsys_sprintf
  export _root_.czmq.functions.zsys_test
  export _root_.czmq.functions.zsys_thread_affinity_cpu_add
  export _root_.czmq.functions.zsys_thread_affinity_cpu_remove
  export _root_.czmq.functions.zsys_thread_name_prefix
  export _root_.czmq.functions.zsys_udp_close
  export _root_.czmq.functions.zsys_udp_new
  export _root_.czmq.functions.zsys_udp_recv
  export _root_.czmq.functions.zsys_udp_send
  export _root_.czmq.functions.zsys_version
  export _root_.czmq.functions.zsys_vprintf
  export _root_.czmq.functions.zsys_warning
  export _root_.czmq.functions.zuuid_data
  export _root_.czmq.functions.zuuid_destroy
  export _root_.czmq.functions.zuuid_dup
  export _root_.czmq.functions.zuuid_eq
  export _root_.czmq.functions.zuuid_export
  export _root_.czmq.functions.zuuid_neq
  export _root_.czmq.functions.zuuid_new
  export _root_.czmq.functions.zuuid_new_from
  export _root_.czmq.functions.zuuid_set
  export _root_.czmq.functions.zuuid_set_str
  export _root_.czmq.functions.zuuid_size
  export _root_.czmq.functions.zuuid_str
  export _root_.czmq.functions.zuuid_str_canonical
  export _root_.czmq.functions.zuuid_test
