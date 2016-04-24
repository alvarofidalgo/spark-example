package big.data.study.IntegrationTest


import big.data.study.Ingestor
import big.data.study.doubles.StatusDouble
import big.data.study.fakes.{StreamingContextFake, ClockWrapper}
import big.data.study.queues.KafkaPersistBuilder
import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Millis, Span}
import org.scalatest.{ShouldMatchers, WordSpec}
import twitter4j.Status


class IngestorSpec extends WordSpec with ShouldMatchers with  Eventually {

  implicit override val patienceConfig = PatienceConfig(timeout = scaled(Span(1200, Millis)))

  "When we receive any message result  " should {

    val sc = new StreamingContextFake[Status]("org.apache.spark.util.ManualClock")
    val clock = new ClockWrapper(sc)
    val advance = 1


    new Ingestor(new KafkaPersistBuilder()).ingest(sc.createDStream)

    " be that WhiteTeam topic is filled if message contain Real Madrid" in {
      val status = new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")
      sc.start()
      sc.addDataInRDD(Seq(status))
      clock.advance(advance)
      eventually{
        val status = new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")

      }
      sc.stop()
    }
  }

}
