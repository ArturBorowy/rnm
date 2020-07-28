package pl.arturborowy.rnm.base.date

import java.util.*

interface StringToDateConverter {

    fun convert(string: String): Date
}