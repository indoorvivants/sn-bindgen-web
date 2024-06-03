package bindgen.web.internal.jobs

import cats.effect.*, std.Env
import cats.syntax.all.*
import scala.concurrent.duration.*

// TODO: integrate
case class RuntimeConfig(
    workStealLimit: Int,
    leaseLimit: Int,
    workerQueueSize: Int,
    workerPulse: FiniteDuration,
    jobStaleness: FiniteDuration
)

object RuntimeConfig:
  def fromEnv: IO[RuntimeConfig] =
    val env = Env[IO]

    def intOption(name: String, default: Int) =
      env.get(name).map(_.flatMap(_.toIntOption).getOrElse(default))

    def durationMSOption(name: String, default: FiniteDuration) =
      env
        .get(name)
        .map(_.flatMap(_.toIntOption).map(_.millis).getOrElse(default))

    val workStealLimit =
      intOption("BINDGEN_WORKER_WORK_STEAL_LIMIT", 5)

    val leaseLimit =
      intOption("BINDGEN_WORKER_LEASE_LIMIT", 5)

    val workerQueueSize =
      intOption("BINDGEN_WORKER_QUEUE_SIZE", 100)

    val workerPulseDuration =
      durationMSOption("BINDGEN_WORKER_PULSE_DURATION_MS", 1.second)

    val jobStalenessDuration =
      durationMSOption("BINDGEN_WORKER_STALE_DURATION_MS", 60.seconds)

    (
      workStealLimit,
      leaseLimit,
      workerQueueSize,
      workerPulseDuration,
      jobStalenessDuration
    ).parMapN(
      RuntimeConfig.apply
    )
  end fromEnv
end RuntimeConfig
