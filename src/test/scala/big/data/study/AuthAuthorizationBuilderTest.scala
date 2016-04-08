package big.data.study



import com.typesafe.config.ConfigFactory
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ShouldMatchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class AuthAuthorizationBuilderTest extends WordSpec with ShouldMatchers {



  private val conf = ConfigFactory
    .parseString( """authKeys {
                    |          consumerKey= a
                    |          consumerSecret = b
                    |          accessToken = accessToken
                    |          tokenSecret = secretToken
                                           }
                    |
                  """.stripMargin)



  val testing = new TwitterAuthorizationBuilder(conf)

  "When build twiter Authorization result " should {


    " Authorization  with configrable token" in {

      testing.build().getOAuthAccessToken.getToken shouldBe  "accessToken"
    }

    " Authorization with configurable Secret Key " in  {

      testing.build().getOAuthAccessToken.getTokenSecret shouldBe  "secretToken"
    }
  }

}
