package bindgen.web.internal.jobs

import java.util.UUID
import opaque_newtypes.TotalWrapper
import cats.effect.std.*
import cats.effect.*
import bindgen.web.domain.JobId
import concurrent.duration.*

opaque type WorkerId = UUID
object WorkerId extends TotalWrapper[WorkerId, UUID]

class Worker private (id: WorkerId, store: Store):
  def process =
    Queue.bounded[IO, JobId](100).toResource.flatMap { q =>
      val normalProcess =
        fs2.Stream
          .repeatEval(q.tryTake)
          .meteredStartImmediately(500.millis)
          .flatMap {
            case None =>
              val unprocessed = store
                .createLeases(id, 5)
                .evalTap(jobId => Log.info(s"Worker $id is leasing job $jobId"))

              val stolen = store
                .workSteal(id)
                .evalTap(jobId =>
                  Log.info(s"Worker $id is stealing old job $jobId")
                )

              (unprocessed ++ stolen).attempt
                .collect { case Right(jid) => jid }
                .evalMap(q.offer)

            case Some(jobId) =>
              fs2.Stream.eval {
                Log.info(s"Processing $jobId (3 seconds)") *>
                  store
                    .complete(jobId)
                    .delayBy(3.seconds)
                    .handleErrorWith(exc =>
                      Log.error(s"Failed to complete $jobId", exc)
                    ) *>
                  store
                    .removeLease(id, jobId)
                    .handleErrorWith(exc =>
                      Log.error(
                        s"Failed to remove lease for job $jobId, held by worker $id",
                        exc
                      )
                    )
              }

          }

      normalProcess.compile.drain.background
    }
end Worker

object Worker:
  def create(store: Store): IO[Worker] =
    UUIDGen[IO].randomUUID.map(WorkerId(_)).map(Worker(_, store))
