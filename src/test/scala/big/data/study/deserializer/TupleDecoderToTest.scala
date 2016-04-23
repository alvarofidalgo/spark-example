package big.data.study.deserializer

import java.util.Date

import com.google.common.primitives.Longs
import kafka.serializer.Decoder


class TupleDecoderToTest extends Decoder[(Date,String)]{

  override def fromBytes(bytes: Array[Byte]): (Date,String) = {
     val from = 0
     val to = 64
     val time = Longs.fromByteArray(bytes.slice(from,to))
     val message = toString(bytes.slice(to,bytes.length))
     (new Date(time),message)
  }

  private def toString(bytes: Array[Byte]):String = {
    val emptyString =""
    bytes.foldLeft(emptyString)((string,byte)=>concatByteWith(string,byte))
  }

  private def concatByteWith(string:String,byte:Byte):String ={
      string.concat(byte.toChar.toString)
  }
}
