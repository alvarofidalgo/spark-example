package big.data.study.persist

import java.util.{Properties, Date}

import big.data.study.queues.TopicInitializer
import big.data.study.queues.serializers.{TwitDateSerializer, TwitsSerializer}
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord

class WhiteTeam extends Persist{

  private val props = new Properties()
  private val topicInitializer = TopicInitializer()

  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.99.100:9092")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[TwitsSerializer].getName)
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[TwitDateSerializer].getName)
  props.put(ProducerConfig.ACKS_CONFIG,"1")
  props.put("kafka.serializer.encoding","UTF-8")
  val producer = new KafkaProducer[Date,(String,Date)](props)


  override def insert(tuple: (String, Date)): Unit = {
    topicInitializer.initTopic("whiteTeam")
    val producerRecord = new ProducerRecord[Date,(String,Date)]("whiteTeam", tuple._2, tuple)

    producer.send(producerRecord)
    producer.close()
  }
}
