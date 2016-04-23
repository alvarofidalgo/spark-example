package big.data.study.consumer

import big.data.study.deserializer.KeyDecoderToTest
import big.data.study.deserializer.TupleDecoderToTest

import java.util.Date
import java.util.Properties

import kafka.consumer.Consumer
import kafka.consumer.ConsumerConfig

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class ConsumerToTest {

  val props = new Properties()

  props.put("zookeeper.connect", "192.168.99.100:2181")
  props.put("group.id", "migrupo")
  props.put("auto.offset.reset", "largest")
  props.put("zookeeper.session.timeout.ms", "400")
  props.put("zookeeper.sync.time.ms", "200")
  props.put("auto.commit.interval.ms", "1000")
  private val config = new ConsumerConfig(props)
  private val consumer = Consumer.create(config)

  def consume(key:Date): Future[(Date,String)] = {
    val id = 1
    val consumerMap = consumer.createMessageStreams(Map(("whiteTeam",id)),new KeyDecoderToTest,new TupleDecoderToTest)
    val stream =consumerMap.get("whiteTeam").get.head
    Future {
     stream.iterator()
            .filter(a=> isEquals(a.key(),key))
            .map(streamMessage=>streamMessage.message())
            .next()


    }
  }

  def isEquals(date:Date,key:Date):Boolean ={
    date.equals(key)
  }

}
