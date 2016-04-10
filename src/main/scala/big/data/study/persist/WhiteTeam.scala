package big.data.study.persist

import java.util.{Properties, Date}

import big.data.study.queues.TopicInitializer
import big.data.study.queues.serializers.TwitsSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer


class WhiteTeam extends Persist{

  private val props = new Properties()
  private val topicInitializer = TopicInitializer()

  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.99.100:9092")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[TwitsSerializer].getName)
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer].getName)
  props.put(ProducerConfig.ACKS_CONFIG,"1")


  override def insert(tuple: (String, Date)): Unit = {

    topicInitializer.initTopic("whiteTeam")
    val producer = new KafkaProducer[String,(String,Date)](props)

    val key = "mykey1"
    val producerRecord = new ProducerRecord[String,(String,Date)]("whiteTeam", key, tuple)
    producer.send(producerRecord)

    producer.close()
  }
}
