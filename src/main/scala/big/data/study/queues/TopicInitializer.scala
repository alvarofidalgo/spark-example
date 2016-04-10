package big.data.study.queues

import java.util.Properties

import big.data.study.config.Config
import kafka.admin.AdminUtils
import kafka.common.TopicExistsException
import kafka.utils.ZKStringSerializer
import org.I0Itec.zkclient.ZkClient

import scala.util.{Success, Try, Failure}


class TopicInitializer (zkClient:ZkClient){

  def initTopic(topic:String):Unit ={

    Try(AdminUtils.createTopic(zkClient, topic, 10, 1, new Properties())) match {
      case Success(_)=>
      case Failure(ex:TopicExistsException)=>
      case Failure(ex) => throw new RuntimeException("unexpected error")
    }
  }
}


object TopicInitializer extends Config{

  private lazy val zkClient = new ZkClient(conf.getString("kafka.zookeperHosts"),
                                           conf.getInt("kafka.zookeperConnectionTimeOut"),
                                           conf.getInt("kafka.zookeperSessionTimeout"),
                                           ZKStringSerializer)

  def apply(): TopicInitializer ={
      new TopicInitializer(zkClient)
  }
}
