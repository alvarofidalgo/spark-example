package big.data.study.queues.serializers


import big.data.study.fakes.DateFake
import java.util.HashMap
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.junit.runner.RunWith

import org.scalatest.ShouldMatchers
import org.scalatest.WordSpec



@RunWith(classOf[JUnitRunner])
class TwitsSerializerTest extends WordSpec with ShouldMatchers with MockitoSugar{

  " We want serialize tuple to bytes and result " should {

     " be bytes with serializer " in {
       val False = false
       val date = DateFake.toDate("MM/dd/yy","01/01/16")
       val message ="this is twit"
       val expected = Encoder.toByteArray(date) ++ message.getBytes("UTF-8")
       val serializer  = new TwitsSerializer
       serializer.configure(addEncoding("UTF-8"),False)
       serializer.serialize("",(message,date)) shouldBe expected
     }
  }

  private def addEncoding(enconding:String) :HashMap[String,Object]= {
    val props = new HashMap[String,Object]
    props.put("kafka.serializer.encoding",enconding)
    props
  }
}
