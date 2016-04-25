package big.data.study.persist

import big.data.study.doubles.{DefaultPersist, RegisteredPersist}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ShouldMatchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class PersistStrategyTest extends WordSpec with ShouldMatchers{

  private val firstCondition ="first"
  private val secondCondition = "second"
  private val namePersist ="namePersist"
  private val strategy = new PersistStrategy(namePersist, firstCondition,secondCondition)


  "We can concatenate Persist and result " should {
    val existPersist = Seq("existPersist")

     " be sequence with registered persist and exist persist when complain all conditions" in {

         val expected = Seq(namePersist) ++ existPersist
         val persistSeq = strategy.persistOf(firstCondition.concat(secondCondition),existPersist)
         persistSeq shouldBe  expected
     }

    " be sequence with exist persist when only complain one condition " in  {
      val expected = existPersist
      val persistSeq = strategy.persistOf(firstCondition,existPersist)
      persistSeq shouldBe expected
    }

     " be sequence with exist persist when  not complain all conditions " in  {
        val notMatch = "notMatch"
       val expected = existPersist
       val persistSeq = strategy.persistOf(notMatch,existPersist)
       persistSeq shouldBe expected
     }
  }



}
