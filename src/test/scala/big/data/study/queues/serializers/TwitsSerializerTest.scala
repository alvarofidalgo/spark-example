package big.data.study.queues.serializers


import java.util

import big.data.study.fakes.DateFake
import org.scalatest.mock.MockitoSugar
import org.scalatest.{ShouldMatchers, WordSpec}

import java.util.{Properties, HashMap}
import scala.collection.JavaConversions._


class TwitsSerializerTest extends WordSpec with ShouldMatchers with MockitoSugar{

  " We want serialize tuple to bytes and result " should {

     " be bytes with serializer " in {
          val serializer  = new TwitsSerializer
          val date = DateFake.toDate("MM/dd/yy","01/01/16")
          val message ="this is twit"
          val props = new java.util.HashMap[String,Object]
          props.put("kafka.serializer.encoding","UTF-8")
          serializer.configure(props,false)
          val expected = date.getTime.toString.getBytes("UTF-8") ++ message.getBytes("UTF-8")
          serializer.serialize("",(message,date)) shouldBe expected
     }
  }
}
