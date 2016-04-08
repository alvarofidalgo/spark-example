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
 
 Change test/resources/application.conf and configure this parameters.
 
 CUANDO TENGA LA CONEXIÓN A KAFKA PONER LOS PARÁMETROS QUE NECESITO PARA QUE SE CONECTE AL KAFKA
 
 
## STEPS TO DEVELOP

 In first time we will need our application can be tested how this application ingest data from twiter exist an extra 
 difficult to test however we could simulate an external clock and simulate external message. If you like understand how 
 we wrote this test go to this class please :
 
 **org.apache.spark.streaming.StreamingContextWrapper.scala**
 -------------------------------------------------------------------------------------
  
 