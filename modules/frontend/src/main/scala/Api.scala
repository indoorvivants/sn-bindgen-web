package bindgen.web.frontend

import bindgen.web.api.BindgenService
import com.raquo.laminar.api.L.*
import org.scalajs.dom
import smithy4s_fetch.*

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.Thenable.Implicits.*
import scala.util.Failure
import scala.util.Success

def api(using a: Api): Api = a

class Api private (
    val users: BindgenService[Promise]
):
  import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.*
  def future[A](a: Api => Promise[A]): Future[A] =
    a(this)

  def futureAttempt[A](a: Api => Promise[A]): Future[Either[Throwable, A]] =
    a(this).transform:
      case Success(value)     => Success(Right(value))
      case Failure(exception) => Success(Left(exception))

  def stream[A](a: Api => Promise[A]): EventStream[A] =
    EventStream.fromJsPromise(a(this))

  def signal[A](a: Api => Promise[A]): Signal[Option[A]] =
    Signal.fromJsPromise(a(this))
end Api

object Api:
  def create(location: String = dom.window.location.origin) =

    val client =
      SimpleRestJsonFetchClient(BindgenService, location).make

    Api(client)

  end create
end Api
