package big.data.study.queues

import java.util.Properties

import big.data.study.exceptions.TopicException
import kafka.admin.AdminUtils
import kafka.common.TopicExistsException
import kafka.utils.ZKStringSerializer
import org.I0Itec.zkclient.ZkClient

import scala.util.{Success, Try, Failure}


class TopicInitializer (zkClient:ZkClient){

  def initTopic(topic:String):Unit ={

    Try(AdminUtils.createTopic(zkClient, topic, 10, 1, new Properties())) match {
      case  Failure(ex:TopicExistsException)=>throw new TopicException("Exist a topic with this name")
      case Failure(ex) => throw new RuntimeException("unexpected error")
      case Success(_)=>
    }
  }
}

//TODO : include typesafe
object TopicInitializer {

  private val sessionTimeoutMs = 10000
  private val connectionTimeoutMs = 10000
  private val zkClient = new ZkClient("192.168.99.100:2181", sessionTimeoutMs, connectionTimeoutMs,ZKStringSerializer)

  def apply(): Unit ={
      new TopicInitializer(zkClient)
  }
}
