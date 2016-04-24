package big.data.study.queues

import big.data.study.persist.PersistStrategy


class TopicNameBuilder (strategySeq:Seq[PersistStrategy[String]],defaultNameTopic:String){

  def topicName(message: String):String = {
    val regexWhiteSpace = "\\s+"
    val emptyChar = ""
    val joinMessageLowerCase = message.replaceAll(regexWhiteSpace, emptyChar).toLowerCase
    val topicName =strategySeq
                  .foldLeft(Seq.empty[String]) ((all,actual)=> actual.persistOf(joinMessageLowerCase,all))
                   .mkString(emptyChar)
    selectTopicName(topicName)

  }

  private def selectTopicName(topicName:String):String ={
    topicName.size match {
      case 0 => defaultNameTopic
      case _=>  topicName
    }
  }

}
