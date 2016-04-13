package big.data.study.persist

import java.util.Date

import big.data.study.consumer.ConsumerToTest
import org.scalatest.{ShouldMatchers, WordSpec}

//TODO : TEST VALUE OF TOPIC
class WhiteTeamTest extends WordSpec with ShouldMatchers{

  private val consumer = new ConsumerToTest

  " We can insert data in WhiteTeam topic and result " should {


      "Be message is sended " in  {

         val persist = new WhiteTeam()
         persist.insert(("Real Madrid",new Date()))
         consumer.consumerOne
      }
  }

}
