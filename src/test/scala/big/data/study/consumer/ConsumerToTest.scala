package big.data.study.consumer

import java.util.{Date, Properties}

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import scala.concurrent.Future


class ConsumerToTest {

 val props = new Properties()
  props.put("bootstrap.servers", "192.168.99.100:9092")
  props.put("group.id", "consumer-tutorial")
  props.put("key.deserializer", classOf[StringDeserializer].getName)
  props.put("value.deserializer", classOf[StringDeserializer].getName)
  props.put("partition.assignment.strategy", "range");
  val consumer = new KafkaConsumer(props)
 consumer.subscribe("whiteTeam")


  def consumerOne : Future [(String,Date)] = {

      null
  

  }


}
