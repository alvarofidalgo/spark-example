package big.data.study.queues.serializers


import java.util
import java.util.Date

import org.apache.kafka.common.serialization.Serializer


class TwitDateSerializer extends Serializer[Date]{
  override def configure(map: util.Map[String, _], b: Boolean): Unit = {}

  override def serialize(topic: String, date: Date): Array[Byte] = {
    Encoder.toByteArray(date)
  }

  override def close(): Unit = {}
}
