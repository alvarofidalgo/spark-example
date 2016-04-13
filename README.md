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
 
 To run : docker run  -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=192.168.99.100 spotify/kafka
 
 Change test/resources/application.conf and configure this parameters :
 
 **Authorization with twiter**
 -------------------------------
 
 To retrieve this parameter values you need register App in Twiter (https://apps.twitter.com/)
 
 authKeys.consumerKey</br>
 authKeys.consumerSecret</br>
 authKeys.accessToken</br>
 authKeys.tokenSecret</br>

 **Zookeper connection**
 -----------------------------
 
 kafka.zookeperHosts : String with "host1:port1,...., hostn:portn" . In this zookeper we save all kafka topic configuration</br>
 kafka.zookeperConnectionTimeOut : Integer value that represent timeout with zookeper in milliseconds.</br>
 kafka.zookeperSessionTimeout : Integer value that represent session timeout with zookeper.</br>
 
 

## STEPS TO DEVELOP

 In first time we will need our application can be tested how this application ingest data from twiter exist an extra 
 difficult to test however we could simulate an external clock and simulate external message. If you like understand how 
 we wrote this test go to this class please :
 
 **org.apache.spark.streaming.StreamingContextWrapper.scala**
 -------------------------------------------------------------------------------------
 
 If we want to test a streaming aplication we will need a synchronous clock , as org.apache.spark.util.ManualClock is 
 protected spark class we had to implement this class in override package.
 
 In this class we assign manual clock to any StreamingContext. We could use this manual clock in our test.
 
 **big.data.study.fakes.ClockWrapper.scala**
 -----------------------------------------------------------
 
 In this class we have method to can advance clock in synchronous mode.
 
 **big.data.study.fakes.StreamingContextFake.scala**
 --------------------------------------------------------------------
 
 In this class we simulate data window for each time advance.
 

  
 