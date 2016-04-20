package big.data.study.persist


import java.util.Date

import big.data.study.consumer.ConsumerToTest
import org.scalatest.{ShouldMatchers, WordSpec}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class WhiteTeamTest extends WordSpec with ShouldMatchers{

  private val consumer = new ConsumerToTest

  " We can insert data in WhiteTeam topic and result " should {

      "Be message is sended " in  {
         val date = new Date()
         val persist = new WhiteTeam()
         val result = consumer.consume(date)
         persist.insert(("Real Madrid",date))
         Await.result(result,Duration.Inf)
         result.value.get.get._2 shouldBe "Real Madrid"


      }
  }

}
