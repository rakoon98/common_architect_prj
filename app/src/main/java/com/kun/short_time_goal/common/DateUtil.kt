package com.kun.short_time_goal.common

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

object DateUtil {

    val timeTZoneDateFormat = SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss", Locale.KOREAN )
        .apply { timeZone = TimeZone.getTimeZone("UTC") }

    private val timeFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

    fun toLocalDateTime(dateTimeFormatString: String): LocalDateTime {
        return LocalDateTime.parse(dateTimeFormatString, DateTimeFormatter.ISO_DATE_TIME)
    }

    fun LocalDateTime.toTimeFormat(): String {
        return this.format(timeFormat)
    }

}