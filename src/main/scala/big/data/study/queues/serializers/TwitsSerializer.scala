package big.data.study.queues.serializers

import java.util
import java.util.Date


import org.apache.kafka.common.serialization.Serializer


class TwitsSerializer extends Serializer[(String,Date)]{

  private val encoder =  collection.mutable.Seq("")

  override def configure(map: util.Map[String, _], b: Boolean): Unit = {
    val head = 0
    val encoding = map.get("kafka.serializer.encoding").asInstanceOf[String]
    encoder(head)=encoding

  }

  override def serialize(topic: String, twit: (String, Date)): Array[Byte] = {
    val encoding = encoder.head
    val message = twit._1
    val date = twit._2
    date.getTime.toString.getBytes(encoding) ++ message.getBytes(encoding)
  }

  override def close(): Unit = {}
}
