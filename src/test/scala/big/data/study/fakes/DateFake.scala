package big.data.study.fakes

import java.text.SimpleDateFormat
import java.util.Date


object DateFake {


  def toDate(dateFormat:String,sDate:String) : Date = {
    val formatter = new SimpleDateFormat(dateFormat)
    formatter.parse(sDate)
  }

}
