package big.data.study


import com.typesafe.config.Config
import twitter4j.{Twitter, TwitterFactory}



class TwitterAuthorizationBuilder(conf:Config)  {

  private lazy val configuration =  new twitter4j.conf.ConfigurationBuilder()
                          .setOAuthConsumerKey(conf.getString("authKeys.consumerKey").trim)
                          .setOAuthConsumerSecret(conf.getString("authKeys.consumerSecret").trim)
                          .setOAuthAccessToken(conf.getString("authKeys.accessToken").trim)
                          .setOAuthAccessTokenSecret(conf.getString("authKeys.tokenSecret".trim))
                          .build

  def build() : Twitter = {
   val authorization =  new twitter4j.auth.OAuthAuthorization(configuration)
   val twitter_auth = new TwitterFactory(configuration)
   twitter_auth.getInstance(authorization)
  }
}



