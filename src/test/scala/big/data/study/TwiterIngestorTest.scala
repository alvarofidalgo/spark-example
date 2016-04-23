package big.data.study

import org.scalatest.WordSpec


//THIS TEST USE TO RUN
class TwiterIngestorTest extends WordSpec {


  "first test with twiter " should  {

          "first call " in {
              new TwitterIngestor().ingestTwiterTags()
          }
    }

}
