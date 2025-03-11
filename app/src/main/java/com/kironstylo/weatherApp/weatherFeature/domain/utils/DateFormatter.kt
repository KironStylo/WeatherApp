package com.kironstylo.weatherApp.weatherFeature.domain.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {
    fun formatDate(localDateTime: LocalDateTime, format:String): String{
        val formatter = DateTimeFormatter.ofPattern(format)
        return localDateTime.format(formatter)
    }
}