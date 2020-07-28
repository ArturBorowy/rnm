package pl.arturborowy.rnm.base.date

import java.text.SimpleDateFormat
import java.util.*

class YearMonthDayHourMinuteSecondMillisStringToDateConverter(private val locale: Locale) :
    StringToDateConverter {

    override fun convert(string: String): Date {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", locale)
        return simpleDateFormat.parse(string)!!
    }
}