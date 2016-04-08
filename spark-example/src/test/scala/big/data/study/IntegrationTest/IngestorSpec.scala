package big.data.study.IntegrationTest


import big.data.study.Ingestor
import big.data.study.doubles.StatusDouble
import big.data.study.fakes.{StreamingContextFake, ClockWrapper}
import big.data.study.persist.PersistFactory
import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Millis, Span}
import org.scalatest.{ShouldMatchers, WordSpec}
import twitter4j.Status

//TODO : MAKE CONSUMER TO TEST
//TODO : MEKE PRODUCER IN TEST
class IngestorSpec extends WordSpec with ShouldMatchers with  Eventually {

  implicit override val patienceConfig = PatienceConfig(timeout = scaled(Span(1200, Millis)))

  "When we receive any message result  " should {

    val sc = new StreamingContextFake[Status]("org.apache.spark.util.ManualClock")
    val clock = new ClockWrapper(sc)


    new Ingestor(PersistFactory()).ingest(sc.createDStream)

    " be that WhiteTeam topic is filled " in {
      sc.start()
      sc.addDataInRDD(Seq(new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")))
      clock.advance(1)
      eventually{
        val status = new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")

      }
      sc.stop()
    }
  }

}
