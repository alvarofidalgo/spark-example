package big.data.study.config


import com.typesafe.config.ConfigFactory


trait Config {
  lazy val conf =
    ConfigFactory.load

}
