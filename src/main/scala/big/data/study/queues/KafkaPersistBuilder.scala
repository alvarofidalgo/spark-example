package big.data.study.queues

import big.data.study.persist.{PersistStrategy, Persist, PersistBuilder}


class KafkaPersistBuilder extends PersistBuilder{


  override def build(message:String): Persist = {
    val whiteTeam = "whiteTeam"
    val blueGarnetTeam ="blueGarnetTeam"
    val defaultNameTopic ="notRecognize"
    val nameTopic =new TopicNameBuilder(Seq(new PersistStrategy(blueGarnetTeam,"barcelona"),
                                            new PersistStrategy(whiteTeam,"realmadrid")
                                            ),
                         defaultNameTopic).topicName(message)
    KafkaPersist(nameTopic)
  }
}
