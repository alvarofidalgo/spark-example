package big.data.study.persist

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ShouldMatchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class PersistFactoryTest extends WordSpec with ShouldMatchers{

  private  val firstElement = 0
  private  val whitePersist =  "whiteTeam"
  private  val blueGarnetTeam =  "blueGarnet"
  private def seq = Seq(new PersistStrategy(whitePersist,"realmadrid"),
                        new PersistStrategy(blueGarnetTeam,"barcelona"))
                    //    new PersistStrategy(PersistPriority(2,new AllTeamsPersist),"realmadrid","barcelona"),
                    //     new PersistStrategy(PersistPriority(0,new NotDefinedPersist)))
  private val factory = new PersistFactory(seq)

  " When text contain only element search result " should {


      " be whiteTeam topic  of KafkaPesist selected when is Real Madrid" in {
        val message = "Real Madrid"
        factory.getPersist(message)(firstElement) shouldBe whitePersist
      }

      " be blueGarnet topic  of KafkaPesist selected  when is Barcelona " in  {
        factory.getPersist("Barcelona")(firstElement) shouldBe blueGarnetTeam
      }

      " be whiteTeam topic  of KafkaPesist when is Real madrid " in {
        val message = "Real madrid"
        factory.getPersist(message)(firstElement) shouldBe whitePersist
      }

      " be whiteTeam topic  of KafkaPesist when is Real     Madrid" in  {
        val message = "Real     Madrid"
        factory.getPersist(message)(firstElement) shouldBe whitePersist
      }
  }

  " When text contain more elements then searcher result " should {

      " be whiteTeam topic  of KafkaPesist when is In Real Madrid More" in {
        val message = "In Real Madrid More"
        factory.getPersist(message)(firstElement) shouldBe whitePersist
      }

      " be AllTeamsPersist instance when is Real Madrid versus Barcelona very good" in {
        val message = "Real Madrid versus Barcelona very good"
        factory.getPersist(message)(firstElement) shouldBe an[AllTeamsPersist]
      }

      " Be NotDefinedPersist when instance is any word not reistered " in {
        val message ="any word not reistered "
        factory.getPersist(message)(firstElement) shouldBe an[NotDefinedPersist]
      }
  }

}
