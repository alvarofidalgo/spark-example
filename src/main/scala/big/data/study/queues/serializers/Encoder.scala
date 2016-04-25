package big.data.study.queues.serializers

import java.nio.ByteBuffer
import java.util.Date


object Encoder {

  def toByteArray(date:Date):Array[Byte] = ByteBuffer.allocate(64).putLong(date.getTime).array()

}
