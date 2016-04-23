package big.data.study.queues.serializers

import java.nio.ByteBuffer

import big.data.study.fakes.DateFake
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ShouldMatchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class TwitDateSerializerTest extends WordSpec with ShouldMatchers{

  " We want serialize tuple to bytes and result " should {

    " be bytes with serializer " in {

      val serializer  = new TwitDateSerializer
      val date = DateFake.toDate("MM/dd/yy","01/01/16")
      val expected =
        ByteBuffer.allocate(64).putLong(date.getTime).array()
      serializer.serialize("",date) shouldBe expected
    }
  }

}
