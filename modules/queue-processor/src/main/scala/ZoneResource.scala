package bindgen.web.internal.jobs

import cats.effect.*

import scala.scalanative.unsafe.*

val ZoneResource: Resource[IO, Zone] =
  Resource.make(IO(Zone.open()))(zone => IO(zone.close()))

extension (r: Resource[IO, Zone])
  def useI[A](f: Zone ?=> IO[A]): IO[A] =
    r.use(zone => f(using zone))
  def flatMapI[A](f: Zone ?=> Resource[IO, A]): Resource[IO, A] =
    r.flatMap(zone => f(using zone))
