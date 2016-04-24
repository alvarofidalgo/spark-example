package big.data.study


import big.data.study.persist.PersistBuilder
import org.apache.spark.streaming.dstream.DStream
import twitter4j.Status


class Ingestor (persistFactory:PersistBuilder) {


  def ingest(dStream: DStream[Status]): Unit = {
    dStream.foreachRDD(status =>{
                         status.collect().foreach({
                              status =>insertTwit(status)
                          }
                   )
           })
  }

  private def insertTwit(twit:Status) : Unit = {
    val tupleInsert = (twit.getText,twit.getCreatedAt)
    val persist =   persistFactory.build(tupleInsert._1)
    persist.insert(tupleInsert)
  }

}
