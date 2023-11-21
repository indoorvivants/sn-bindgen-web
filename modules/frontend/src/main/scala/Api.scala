package bindgen.web.frontend

import bindgen.web.api.BindgenService
import bindgen.web.domain.Status.CompletedCase
import bindgen.web.domain.Status.FailedCase
import bindgen.web.domain.Status.ProcessingCase
import bindgen.web.domain.*
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.raquo.laminar.api.L.*
import com.raquo.waypoint.*
import org.http4s.Uri
import org.http4s.dom.FetchClientBuilder
import org.scalajs.dom
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*
import smithy4s.http4s.SimpleRestJsonBuilder

import java.util.UUID
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.annotation.JSImport

def api(using a: Api): Api = a

class Api private (
    val users: BindgenService[IO]
):
  import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*
  def future[A](a: Api => IO[A]): Future[A] =
    a(this).unsafeToFuture()

  def stream[A](a: Api => IO[A]): EventStream[A] =
    EventStream.fromFuture(future(a))

  def signal[A](a: Api => IO[A]): Signal[Option[A]] =
    Signal.fromFuture(future(a))
end Api

object Api:
  def create(location: String = dom.window.location.origin) =
    val uri = Uri.unsafeFromString(location)

    val client = FetchClientBuilder[IO].create

    Api(
      SimpleRestJsonBuilder(BindgenService)
        .client(client)
        .uri(uri)
        .make
        .fold(throw _, identity)
    )
  end create
end Api
