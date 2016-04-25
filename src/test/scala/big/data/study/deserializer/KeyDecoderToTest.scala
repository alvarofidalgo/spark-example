package big.data.study.deserializer

import java.util.Date

import com.google.common.primitives.Longs
import kafka.serializer.Decoder


class KeyDecoderToTest extends Decoder[Date]{

  override def fromBytes(bytes: Array[Byte]): Date = {
    val time = Longs.fromByteArray(bytes)
    val date =new Date(time)
    date
  }
}
