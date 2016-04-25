package big.data.study.IntegrationTest


import java.util.Date

import big.data.study.Ingestor
import big.data.study.consumer.ConsumerToTest
import big.data.study.doubles.StatusDouble
import big.data.study.fakes.{DateFake, StreamingContextFake, ClockWrapper}
import big.data.study.queues.KafkaPersistBuilder
import org.junit.runner.RunWith
import org.scalatest.concurrent.Eventually
import org.scalatest.junit.JUnitRunner
import org.scalatest.time.{Millis, Span}
import org.scalatest.{BeforeAndAfterAll, ShouldMatchers, WordSpec}
import twitter4j.Status

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class IngestorSpec extends WordSpec with ShouldMatchers with BeforeAndAfterAll with Eventually {

  implicit override val patienceConfig = PatienceConfig(timeout = scaled(Span(1200, Millis)))
  private val idOne= 1
  private val idTwo=2
  private val whiteTeamTopicName ="whiteTeam"
  private val blueGarnetTeamTopic = "blueGarnetTeam"
  private val sc = new StreamingContextFake[Status]("org.apache.spark.util.ManualClock")
  private val clock = new ClockWrapper(sc)
  private val advance = 1
  private val consumer = new ConsumerToTest(Map((blueGarnetTeamTopic,idOne),(whiteTeamTopicName,idTwo)))

  override  def beforeAll(): Unit ={
    new Ingestor(new KafkaPersistBuilder()).ingest(sc.createDStream)
    sc.start()

  }

  override def afterAll():Unit={
      sc.stop()
  }

  "When we receive any message result  " should {

    val format ="MM/dd/yy"
    " be  WhiteTeam topic is filled if message contain Real Madrid" in {

      val message ="Real Madrid in message"
      val sDate ="01/01/24"
      val result = runMicroBatch(message,whiteTeamTopicName,sDate)
      Await.result(result,Duration.Inf)
      result.value.get.get._1 shouldBe DateFake.toDate(format,sDate)
      result.value.get.get._2 shouldBe message

    }

    "be blueGarnetTeam topic is filled if message containf barcelona " in {

      val message ="barcelona in message"
      val sDate ="03/03/22"
      val result = runMicroBatch(message,blueGarnetTeamTopic,sDate)
      Await.result(result,Duration.Inf)
      result.value.get.get._1 shouldBe DateFake.toDate(format,sDate)
      result.value.get.get._2 shouldBe message

    }

  }

  private def runMicroBatch(message:String,topicName:String,sDate:String):Future[(Date,String)]={
    val status = new StatusDouble(message,"MM/dd/yy",sDate)
    val date = DateFake.toDate("MM/dd/yy",sDate)
    val result = consumer.consume(date,topicName)
    sc.addDataInRDD(Seq(status))
    clock.advance(advance)
    result
  }

}
