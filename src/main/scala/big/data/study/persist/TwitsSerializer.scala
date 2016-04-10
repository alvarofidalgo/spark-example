package big.data.study.persist

import java.util
import java.util.Date

import big.data.study.config.Config
import org.apache.kafka.common.serialization.Serializer


class TwitsSerializer extends Serializer[(String,Date)] with Config{



  override def configure(map: util.Map[String, _], b: Boolean): Unit = {}

  override def serialize(topic: String, twit: (String, Date)): Array[Byte] = {
    val encoding = conf.getString("kafka.serializer.encoding")
    val message = twit._1
    val date = twit._2
    date.getTime.toString.getBytes(encoding) ++ message.getBytes(encoding)
  }

  override def close(): Unit = {}
}
