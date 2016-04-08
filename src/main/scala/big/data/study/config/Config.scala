package big.data.study.config


import com.typesafe.config.ConfigFactory


trait Config {
  val conf = ConfigFactory.load()
}
