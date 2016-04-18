package big.data.study.consumer

import java.nio.ByteBuffer
import java.util.{Timer, Date, Properties}


import scala.concurrent.ExecutionContext.Implicits._

import kafka.consumer.{ConsumerConfig, Consumer}

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
      val result = it.takeWhile(a=>isEquals(a.key(),key)).map(b=> new String(b.message()))
      result.next()
    }
  }


  def isEquals(bytes: Array[Byte],date:Date):Boolean ={
    ByteBuffer.allocate(64).putLong(date.getTime).array().deep == bytes.deep
  }

}
