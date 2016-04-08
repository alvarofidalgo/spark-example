package big.data.study.queues

import big.data.study.exceptions.TopicException
import kafka.utils.{ZkUtils, ZKStringSerializer}
import org.I0Itec.zkclient.ZkClient
import org.scalatest.mock.MockitoSugar
import org.scalatest.{ShouldMatchers, WordSpec}

import scala.util.{Try,Success,Failure}


class TopicInitializerTest extends WordSpec with ShouldMatchers with MockitoSugar{


  private val sessionTimeoutMs = 10000
  private val connectionTimeoutMs = 10000
  private val  zkClient = new ZkClient("192.168.99.100:2181", sessionTimeoutMs, connectionTimeoutMs,ZKStringSerializer)
  private val initializer = new TopicInitializer(zkClient)
  private val topic = "topic"

  " We want to create topic and result  " should {

      " be TopicExistException throwed if topic exist " in {
         zkClient.deleteRecursive(ZkUtils.getTopicPath(topic))
         initializer.initTopic (topic)
         an [TopicException] should be thrownBy initializer.initTopic("topic")
      }

      " be process finish if not exist topic " in {

           zkClient.deleteRecursive(ZkUtils.getTopicPath(topic))
           Try(initializer.initTopic (topic)) match{
             case Success(_)=>
             case Failure(ex:TopicException) => fail(" when exist exeption then ")
           }
      }
  }

}
