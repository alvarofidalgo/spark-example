package big.data.study.queues

import java.util.Date

import big.data.study.consumer.ConsumerToTest
import org.junit.runner.RunWith
import org.scalatest.concurrent.Eventually
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ShouldMatchers, WordSpec}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class KafkaPersistTest extends WordSpec with ShouldMatchers with Eventually{

  private val consumer = new ConsumerToTest(Map(("whiteTeam",1),("blueGarnetTeam",2)))

  " We can insert data in WhiteTeam topic and result " should {

      "Be message is sended " in  {
         val date = new Date()
         val persist = KafkaPersist("whiteTeam")
         val result = consumer.consume(date,"whiteTeam")
         persist.insert(("Real Madrid",date))
         Await.result(result,Duration.Inf)
         result.value.get.get._1 shouldBe date
         result.value.get.get._2 shouldBe "Real Madrid"
      }

    "be other " in {
      val date = new Date()
      val persist = KafkaPersist("blueGarnetTeam")
      val result = consumer.consume(date,"blueGarnetTeam")
      persist.insert(("Barcelona",date))
      eventually {
        result.value.get.get._1 shouldBe date
        result.value.get.get._2 shouldBe "Barcelona"
      }
    }
  }

}
