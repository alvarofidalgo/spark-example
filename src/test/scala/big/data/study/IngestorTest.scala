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
import org.mockito.Mockito.times

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
  private val sc = new StreamingContextFake[Status]("org.apache.spark.util.ManualClock")
  private val clock = new ClockWrapper(sc)
  private val persist = mock[Persist]
  private val advance = 1
  private val persistBuilder = mockBuilder(persist)

  override  def beforeAll(): Unit ={
    new Ingestor(persistBuilder).ingest(sc.createDStream)
    sc.start()

  }

  override def afterAll():Unit={
    sc.stop()
  }

  "We want to send tweet to persist and result  " should {


    " be called one time with one twit in window " in {

      sc.addDataInRDD(Seq(new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")))
      clock.advance(advance)
      eventually{
        val status = new StatusDouble("Real Madrid","MM/dd/yy","01/01/16")
        verify(persist).insert(argThat(new TupleMatcher(status.getText,status.getCreatedAt)))
      }
    }

    " be called more times with more twits in window " in {
      sc.addDataInRDD(Seq(new StatusDouble("Bremem","MM/dd/yy","01/01/16"),
                           new StatusDouble("Bremem","MM/dd/yy","01/01/16")))
      clock.advance(advance)
      eventually{
        val status = new StatusDouble("Bremem","MM/dd/yy","01/01/16")
        verify(persist,times(2)).insert(argThat(new TupleMatcher(status.getText,status.getCreatedAt)))
      }
    }
  }

}


