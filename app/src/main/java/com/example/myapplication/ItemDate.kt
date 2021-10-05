package com.example.myapplication

import org.joda.time.DateTime
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

data class ItemDate (val date: DateTime, var isCheck: Boolean = false){
    fun getDayOfWeek(): String  = date.convertDayOfWeekToString()
    fun getDayOfMonth():String = DecimalFormat("00", DecimalFormatSymbols.getInstance(Locale.getDefault())).format(date.dayOfMonth)
    fun convertDateTimeToString(): String = date.toString("yyyy/MM/dd")
}