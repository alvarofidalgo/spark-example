package big.data.study.persist

import java.util.{Properties, Date}

import kafka.utils.ZKStringSerializer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.I0Itec.zkclient.ZkClient
import org.apache.kafka.common.serialization.StringSerializer


class WhiteTeam extends Persist{

  private val props = new Properties()

  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.99.100:9092")
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer].getName)
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,classOf[StringSerializer].getName)
  props.put(ProducerConfig.ACKS_CONFIG,"1")


  override def insert(tuple: (String, Date)): Unit = {
    val sessionTimeoutMs = 10000
    val connectionTimeoutMs = 10000
    val zkClient = new ZkClient("192.168.99.100:2181", sessionTimeoutMs, connectionTimeoutMs,ZKStringSerializer)
    //AdminUtils.createTopic(zkClient, "whiteTeam", 10, 1, new Properties())
    val producer = new KafkaProducer[String,String](props)

    val key = "mykey";
    val value = "myvalue";
    val producerRecord = new ProducerRecord[String,String]("whiteTeam", key, value);

    producer.send(producerRecord)

    producer.close()
  }
}
