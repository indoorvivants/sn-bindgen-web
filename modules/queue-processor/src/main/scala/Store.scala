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
          sql"insert into bindings (id, added, header_code) values ($text, $integer, $blob);".command,
          (
            id.toString,
            instant.getEpochSecond(),
            ByteVector(Compression.compressString(spec.headerCode.value))
          )
        ).as(JobId(id))
      }
    }

  val jobIdCodec =
    text.imap[JobId](t => JobId(UUID.fromString(t)))(_.value.toString)

  val workerIdCodec =
    text.imap[WorkerId](t => WorkerId(UUID.fromString(t)))(_.value.toString)

  def complete(id: JobId) =
    IO.realTimeInstant.map(_.getEpochSecond()).flatMap { instant =>
      db.execute(
        sql"update bindings set completed = $integer where id = $jobIdCodec;".command,
        (instant, id)
      )
    }

  def removeLease(workerId: WorkerId, jid: JobId) =
    db.execute(
      sql"delete from leases where binding_id = $jobIdCodec;".command,
      jid
    )

  def isCompleted(jid: JobId): IO[Option[JobId]] =
    db.option(
      sql"select id from bindings where completed is not null".query(
        jobIdCodec
      )
    )

  val timeStream = fs2.Stream.eval(IO.realTimeInstant).map(_.getEpochSecond())

  def workSteal(workerId: WorkerId): fs2.Stream[IO, JobId] =
    val q = sql"""
    | update leases set worker_id = $workerIdCodec where binding_id 
    |  in (select binding_id from leases where ($integer - checked_in_at > 60) limit 5)
    |  returning binding_id
    """.stripMargin.query(jobIdCodec)
    timeStream.flatMap { instant =>
      db.stream(q, (workerId, instant), 1024)
    }
  end workSteal

  def createLeases(workerId: WorkerId, limit: Int): fs2.Stream[IO, JobId] =
    fs2.Stream.eval(IO.realTimeInstant.map(_.getEpochSecond())).flatMap {
      inst =>
        db.stream(
          sql"""
            |insert into leases 
            |  select 
            |    b.id, ${workerIdCodec}, ${integer} 
            |  from 
            |    bindings b left join leases l on l.binding_id = b.id 
            |  where 
            |    l.worker_id is null 
            |    and b.completed is null 
            |  limit ${integer} 
            |returning binding_id;""".stripMargin.query(jobIdCodec),
          (workerId, inst, limit),
          limit.min(100)
        )
    }

  def getOrdering(): IO[Map[JobId.Type, Int]] =
    db.stream(
      sql"""
     |select 
     |  id, row_number() over (order by added) 
     |from 
     |  bindings 
     |where 
     |  completed is null;""".stripMargin.query(
        jobIdCodec.product(integer.imap(_.toInt)(_.toLong))
      ),
      (),
      1024
    ).compile
      .toVector
      .map(_.toMap)

  def getSpec(id: JobId): IO[Option[Job]] =
    val jobCodec =
      (
        jobIdCodec,
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
           |create table if not exists bindings(
           |  id text primary key not null,
           |  added int not null,
           |  completed int,
           |  completed_version text,
           |  header_code blob not null,
           |  scala_code blob,
           |  glue_code blob 
           |);""".stripMargin,
          sql"""
           |create table if not exists leases(
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
