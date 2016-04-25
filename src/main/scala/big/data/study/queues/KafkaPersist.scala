package big.data.study.queues

import java.util.{Date, Properties}

import big.data.study.persist.Persist
import big.data.study.queues.serializers.{TwitDateSerializer, TwitsSerializer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

class KafkaPersist (topic:String,
                    producer:KafkaProducer[Date,(String,Date)],
                    topicInitializer:TopicInitializer) extends Persist{

  override def insert(tuple: (String, Date)): Unit = {

    topicInitializer.initTopic(topic)
    val producerRecord = new ProducerRecord[Date,(String,Date)](topic, tuple._2, tuple)

    producer.send(producerRecord)
  }
}

object KafkaPersist{
  private lazy val props = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.99.100:9092")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[TwitsSerializer].getName)
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[TwitDateSerializer].getName)
  props.put("kafka.serializer.encoding","UTF-8")
  private lazy val producer = new KafkaProducer[Date,(String,Date)](props)
  private lazy val topicInicializer = TopicInitializer()

  def apply(topic:String):KafkaPersist={
      new KafkaPersist(topic,producer,topicInicializer)
  }
}
