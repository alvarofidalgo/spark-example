package big.data.study




import big.data.study.doubles.StatusDouble
import big.data.study.matchers.TupleMatcher
import big.data.study.mocks.PersistBuilderMock
import big.data.study.persist.Persist
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


import org.scalatest.{BeforeAndAfterAll, ShouldMatchers, WordSpec}
import big.data.study.fakes.{StreamingContextFake, ClockWrapper}

import org.mockito.Matchers.argThat
import org.mockito.Mockito.verify

import org.scalatest.concurrent.Eventually
import org.scalatest.time.Millis
import org.scalatest.time.Span
import twitter4j.Status


@RunWith(classOf[JUnitRunner])
class IngestorTest extends WordSpec  with PersistBuilderMock
                                     with ShouldMatchers
                                     with BeforeAndAfterAll
                                     with  Eventually {


  implicit override val patienceConfig = PatienceConfig(timeout = scaled(Span(1200, Millis)))

  "When " should {

    val sc = new StreamingContextFake[Status]("org.apache.spark.util.ManualClock")
    val clock = new ClockWrapper(sc)
    val persist = mock[Persist]
    val persistBuilder = mockBuilder("Real Madrid",persist)

    new Ingestor(persistBuilder).ingest(sc.createDStream)

    " be send one element to persist " in {
      sc.start()
      sc.addDataInRDD(Seq(new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")))
      clock.advance(1)
      eventually{
        val status = new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")
        verify(persist).insert(argThat(new TupleMatcher(status.getText,status.getCreatedAt)))
      }
      sc.stop()
    }
  }
}


