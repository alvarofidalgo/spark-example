package big.data.study.consumer

import java.nio.ByteBuffer
import java.util.{TimerTask,Timer, Date, Properties}


import scala.concurrent.ExecutionContext.Implicits._

import kafka.consumer.{KafkaStream, ConsumerConfig, Consumer}

import scala.concurrent._




class ConsumerToTest {

  val props = new Properties()

  props.put("zookeeper.connect", "192.168.99.100:2181")
  props.put("group.id", "migrupo")
  props.put("auto.offset.reset", "largest")
  props.put("zookeeper.session.timeout.ms", "400")
  props.put("zookeeper.sync.time.ms", "200")
  props.put("auto.commit.interval.ms", "1000")
  val config = new ConsumerConfig(props)
  val consumer = Consumer.create(config)
  val timer = new Timer


  def consume(key:Date): Future[String] = {
    val first = 0
    val id = 1
    val topicCountMap = Map("whiteTeam" -> id)
    val consumerMap = consumer.createMessageStreams(topicCountMap)
    val streams = consumerMap.get("whiteTeam").get
    Future {
      val stream  = streams.toList(first)
      val it = stream.iterator()
      while (it.hasNext()) {
        val next = it.next()
        if (isEquals(next.key(),key)) {
          new String(next.message())
        }else{
          ""
        }

      }
      ""
    }
  }


  def isEquals(bytes: Array[Byte],date:Date):Boolean ={
    val a = ByteBuffer.allocate(64).putLong(date.getTime).array()
    a == bytes
  }

}
