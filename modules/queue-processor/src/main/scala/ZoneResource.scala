package bindgen.web.internal.jobs

import scala.scalanative.unsafe.*

import cats.effect.*
import porcupine.*
import bindgen.web.domain.BindingSpec
import cats.effect.std.UUIDGen
import bindgen.web.domain.JobId

val ZoneResource: Resource[IO, Zone] =
  Resource.make(IO(Zone.open()))(zone => IO(zone.close()))

extension (r: Resource[IO, Zone])
  def useI[A](f: Zone ?=> IO[A]): IO[A] =
    r.use(zone => f(using zone))
  def flatMapI[A](f: Zone ?=> Resource[IO, A]): Resource[IO, A] =
    r.flatMap(zone => f(using zone))
