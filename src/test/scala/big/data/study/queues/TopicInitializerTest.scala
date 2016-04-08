package big.data.study.queues

import big.data.study.exceptions.TopicException
import com.typesafe.config.ConfigFactory
import kafka.utils.{ZkUtils, ZKStringSerializer}
import org.I0Itec.zkclient.ZkClient
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ShouldMatchers, WordSpec}

import scala.util.{Try,Success,Failure}

@RunWith(classOf[JUnitRunner])
class TopicInitializerTest extends WordSpec with ShouldMatchers {



  val conf = ConfigFactory.load()
  private val zkClient = new ZkClient(conf.getString("kafka.zookeperHosts"),
                                      conf.getInt("kafka.zookeperConnectionTimeOut"),
                                      conf.getInt("kafka.zookeperSessionTimeout"),
                                      ZKStringSerializer)

  private val initializer = new TopicInitializer(zkClient)
  private val topic = "topic"



  " We want to create topic and result  " should {

      " be nothing if topic exist " in {
         zkClient.deleteRecursive(ZkUtils.getTopicPath(topic))
         initializer.initTopic (topic)
         Try(initializer.initTopic (topic)) match{
            case Success(_)=>
            case Failure(ex:TopicException) => fail(" when exist exeption then fail ")
            case Failure(ex) =>  fail(" when exist exeption then fail ")
        }
      }

      " be process finish if not exist topic " in {

           zkClient.deleteRecursive(ZkUtils.getTopicPath(topic))
           Try(initializer.initTopic (topic)) match{
             case Success(_)=>
             case Failure(ex:TopicException) =>  fail(" when exist exeption then fail ")
             case Failure(ex) =>  fail(" when exist exeption then fail ")
           }
      }
  }

  private def

}
