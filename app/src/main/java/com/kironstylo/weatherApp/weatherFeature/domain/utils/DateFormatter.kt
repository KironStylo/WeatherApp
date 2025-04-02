package com.kironstylo.weatherApp.weatherFeature.domain.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {
    fun formatDate(localDateTime: LocalDateTime, format:String): String{
        val formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)
        return localDateTime.format(formatter)
    }
}