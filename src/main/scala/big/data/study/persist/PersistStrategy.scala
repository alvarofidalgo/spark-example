package big.data.study.persist


class PersistStrategy [T](registered:T,conditions:String*){

  def persistOf(message: String,last:Seq[T]) : Seq[T] =
    conditions.forall(condition => message.contains(condition)) match {
        case true => Seq(registered) ++ last
        case false =>  last
     }



}
