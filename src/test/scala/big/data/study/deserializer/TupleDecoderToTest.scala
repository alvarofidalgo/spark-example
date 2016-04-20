package big.data.study.deserializer

import java.util.Date

import com.google.common.primitives.Longs
import kafka.serializer.Decoder


class TupleDecoderToTest extends Decoder[(Date,String)]{

  override def fromBytes(bytes: Array[Byte]): (Date,String) = {
     val from = 0
     val to = 9
     val time = Longs.fromByteArray(bytes.slice(from,to))
     val message = bytes.slice(to,bytes.length)
     (new Date(time),new String(message))
  }
}
