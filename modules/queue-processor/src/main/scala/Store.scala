package bindgen.web.internal.jobs

import bindgen.web.domain.BindingSpec
import bindgen.web.domain.JobId
import cats.effect.*
import cats.effect.std.UUIDGen
import porcupine.*
import cats.syntax.all.*

import scala.scalanative.unsafe.*
import scodec.bits.ByteVector
import bindgen.web.domain.HeaderCode
import java.util.UUID

case class Job(id: JobId, code: HeaderCode)

class Store private (db: Database[IO]):
  import Codec.*
  def record(spec: BindingSpec): IO[JobId] =
    IO.realTimeInstant.flatMap { instant =>
      UUIDGen.randomUUID[IO].flatMap { id =>
        db.execute(
          sql"insert into bindings (id, added, header_code) values ($text, $integer, $blob)".command,
          (
            id.toString,
            instant.getEpochSecond(),
            ByteVector(Compression.compressString(spec.headerCode.value))
          )
        ).as(JobId(id))
      }
    }

  def getSpec(id: JobId): IO[Option[Job]] =
    val jobCodec =
      (
        text.asDecoder.map(UUID.fromString(_)).map(JobId.apply),
        blob.asDecoder.map(a => new String(a.toArray)).map(HeaderCode.apply)
      ).mapN(Job.apply)

    db.option(
      sql"select id, header_code from bindings where id = $text".query(
        jobCodec
      ),
      (id.value.toString)
    )
  end getSpec

end Store

object Store:
  def open(filename: String): Resource[cats.effect.IO, Store] =
    Database
      .open[IO](filename)
      .evalTap { db =>
        val migrations = List(
          sql"""
           |create table bindings(
           |  id text primary key not null,
           |  added int not null,
           |  completed int,
           |  completed_version text,
           |  header_code blob not null,
           |  scala_code blob,
           |  glue_code blob 
           |);""".stripMargin,
          sql"""
           |create table leases(
           |  binding_id text not null,
           |  worker_id text not null,
           |  checked_in_at int not null
           |);
         """.stripMargin
        )
        migrations.map(_.command).traverse(db.execute(_))
      }
      .map(Store(_))
end Store
