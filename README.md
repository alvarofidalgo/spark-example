## MOTIVATION

 This is a big data exercise to learn and practice with different Big Data technologies to Stratio Big Data Study Group
 principal of this exercise is show how we can ingest data from twiter and send to different Kafka topics.
 

## EXPLAIN
 
 In first iteration we want to retrieve all twits with "Real Madrid" or "Barcelona" on message. To ingest all messages
 we will use Spark Streaming . After data will be ingested then we will develop different Kafka producers to Kafka 
 topics.
 
## HOW RUN TEST 
 
 To run test you need to have installed Kafka or Docker Machine with Kafka . We have used this image
 
 https://hub.docker.com/r/spotify/kafka/
 
 Change test/resources/application.conf and configure this parameters :
 
 Authorization with twiter
 -------------------------------
 
 To retrieve this parameter values you need register App in Twiter (https://apps.twitter.com/)
 
 authKeys.consumerKey
 authKeys.consumerSecret
 authKeys.accessToken
 authKeys.tokenSecret

 Zookeper connection
 -----------------------------
 
 kafka.zookeperHosts : String with "host1:port1,...., hostn:portn" . In this zookeper we save all kafka topic configuration
 kafka.zookeperConnectionTimeOut : Integer value that represent timeout with zookeper in milliseconds.
 kafka.zookeperSessionTimeout : Integer value that represent session timeout with zookeper.
 
 

## STEPS TO DEVELOP

 In first time we will need our application can be tested how this application ingest data from twiter exist an extra 
 difficult to test however we could simulate an external clock and simulate external message. If you like understand how 
 we wrote this test go to this class please :
 
 **org.apache.spark.streaming.StreamingContextWrapper.scala**
 -------------------------------------------------------------------------------------
  
 