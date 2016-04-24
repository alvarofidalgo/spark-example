package big.data.study.queues

import big.data.study.persist.PersistStrategy
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.ShouldMatchers
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TopicNameBuilderTest extends WordSpec with ShouldMatchers{

  private val whiteTeam = "whiteTeam"
  private val blueGarnetTeam ="blauGarnetTeam"
  private val defaultNameTopic ="notRecognize"
  private val topicNameBuilder = new TopicNameBuilder(Seq(new PersistStrategy(whiteTeam,"realmadrid"),
                                                          new PersistStrategy(blueGarnetTeam,"barcelona")),
                                                      defaultNameTopic)

  "When message contain one word result " should {


    "  be topic relationed with this word" in {
      val message ="Real Madrid"
      val expected =whiteTeam
      val result = topicNameBuilder.topicName(message)
      result shouldBe expected

    }

    " be other topic relationed with this word if word change " in {
      val message ="Barcelona"
      val expected =blueGarnetTeam
      val result = topicNameBuilder.topicName(message)
      result shouldBe expected
    }

    "be topic relationed when contain word in any form " in {
      val message ="rEa l            madrid"
      val expected =whiteTeam
      val result = topicNameBuilder.topicName(message)
      result shouldBe expected
    }
  }

  " When message contain a lot of words result " should {
    "be new concatenate topic when contains all words " in  {
      val message ="rEa l            madrid       barcelonA"
      val expected =blueGarnetTeam.concat(whiteTeam)
      val result = topicNameBuilder.topicName(message)
      result shouldBe expected
    }
  }

  " when message not contains conditions result " should {

      " be default name topic topic " in {
        val message ="not recognize"
        val expected =defaultNameTopic
        val result = topicNameBuilder.topicName(message)
        result shouldBe expected
      }

  }
}
