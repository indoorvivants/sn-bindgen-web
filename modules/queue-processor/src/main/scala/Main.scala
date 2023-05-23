// import czmq.all.*
// import fs2.*
// import cats.effect.*

// import scala.concurrent.duration.*
// import scala.scalanative.unsafe.*
// import scala.scalanative.unsigned.*
// import cats.syntax.all.*
// import opaque_newtypes.TotalWrapper
// import ZeroMQ.Socket
// import scala.scalanative.posix.errno

// object Main extends IOApp:
//   import ZeroMQ.*

//   def consumer(sock: Socket) =
//     ZoneResource.useI {
//       IO {
//         val timeout_ms = stackalloc[Int]()
//         val size       = stackalloc[size_t]()
//         !timeout_ms = 1000
//         zmq_setsockopt(
//           sock.value,
//           ZMQ_RCVTIMEO,
//           timeout_ms.asInstanceOf[Ptr[Byte]],
//           size_t(sizeof[Int])
//         );

//         val msg = stackalloc[zmq_msg_t]()
//         zmq_msg_init(msg)
//         val recv = zmq_recvmsg(sock.value, msg, 0)

//         if recv == -1 then
//           // println(s"Error: ${fromCString(zmq_strerror(zmq_errno()))}")
//           zmq_msg_close(msg)
//         else 
//           println(s"Size: ${zmq_msg_size(msg)}")
//           zmq_msg_send("")
//       }
//       // emptyMessage
//       //   .flatMap { msg =>
//       //     println("starting")
//       //     val recv = zmq_recvmsg(sock.value, msg, 0)
//       //     println(recv)
//       //     zmq_sendmsg(sock.value, message("ack"), 0)
//       //     checkCodeIO("zmq_recvmsg")(recv) *> IO {
//       //       println(recv)
//       //       println(fromCString(zmq_msg_data(msg)))
//       //     }.guarantee(IO(zmq_msg_close(msg)))
//       //   }
//       //   .attempt
//       //   .flatTap(IO.println)

//     }

//   def sender(sock: Socket) =
//     ZoneResource.useI {
//       val msg = message("hello world!!")
//       IO(zmq_msg_send(msg, sock.value, 0)).flatTap(checkCodeIO("zmq_msg_send"))
//     }

//   def run(args: List[String]) =
//     val isServer = args.headOption match
//       case Some(s) if s.equalsIgnoreCase("server") => true
//       case Some(s) if s.equalsIgnoreCase("client") => false
//       case _ => sys.error("Mode has to be one of server or client")

//     val loop =
//       if isServer then
//         fs2.Stream.resource(ZBind).flatMap { sock =>
//           fs2.Stream.repeatEval(consumer(sock))
//         }
//       else
//         fs2.Stream.resource(ZConnect).flatMap { sock =>
//           fs2.Stream.repeatEval(sender(sock)).metered(1.second).take(5)
//         }

//     loop.compile.drain.as(ExitCode.Success)
//   end run
// end Main

// val ZoneResource: Resource[IO, Zone] =
//   Resource.make(IO(Zone.open()))(zone => IO(zone.close()))

// extension (r: Resource[IO, Zone])
//   def useI[A](f: Zone ?=> IO[A]): IO[A] =
//     r.use(zone => f(using zone))

// object ZeroMQ:
//   private object types:
//     opaque type Socket = Ptr[Byte]
//     object Socket extends TotalWrapper[Socket, Ptr[Byte]]

//   import types.*
//   export types.*

//   def checkCode(name: String, expected: Int | Int => Boolean = _ == 0)(i: Int) =
//     val errno  = zmq_errno()
//     val errstr = fromCString(zmq_strerror(errno))
//     assert(
//       expected match
//         case test: (Int => Boolean) => test(i)
//         case value                  => value == expected
//       ,
//       s"$name failed with code $i, errno = $errno, error = `$errstr`"
//     )
//     i
//   end checkCode

//   def checkCodeIO(name: String, expected: Int | Int => Boolean = _ == 0)(
//       i: Int
//   ): IO[Int] =
//     IO(checkCode(name, expected)(i))

//   val ZContext =
//     Resource.make(IO(zmq_ctx_new()))(ctx => IO.unit) // (zmq_ctx_destroy(ctx)))

//   def ZSocket(tpe: Int) =
//     ZContext.map(zmq_socket(_, tpe)).map(Socket.apply(_))

//   val ZBind =
//     ZSocket(ZMQ_REP)
//       .evalTap(sock =>
//         IO(zmq_bind(sock.value, c"tcp://*:5555"))
//           .flatMap(checkCodeIO("zmq_bind"))
//       )

//   val ZConnect =
//     ZSocket(ZMQ_REQ)
//       .evalTap(sock =>
//         IO(zmq_connect(sock.value, c"tcp://localhost:5555"))
//           .flatMap(checkCodeIO("zmq_connect"))
//       )

//   def emptyMessage(using Zone): IO[Ptr[zmq_msg_t]] =
//     IO {
//       val msg = alloc[zmq_msg_t](1)
//       checkCode("zmq_msg_init")(zmq_msg_init(msg))
//       msg
//     }
//   end emptyMessage

//   def message(s: String)(using Zone): Ptr[zmq_msg_t] =
//     val msg = alloc[zmq_msg_t](1)
//     checkCode("zmq_msg_init")(zmq_msg_init(msg))
//     val bytes = s.getBytes()
//     val err = zmq_msg_init_data(
//       msg,
//       bytes.at(0),
//       size_t(bytes.size.toULong),
//       null,
//       null
//     )
//     // assert(err == 0, s"zmq_msg_init_data failed with code $err")
//     checkCode("zmq_msg_init_data")(err)
//     msg
//   end message

//   inline val ZMQ_REP      = 4
//   inline val ZMQ_REQ      = 3
//   inline val ZMQ_NOBLOCK  = 1
//   inline val ZMQ_DONTWAIT = ZMQ_NOBLOCK
//   inline val ZMQ_RCVTIMEO = 27
// end ZeroMQ
