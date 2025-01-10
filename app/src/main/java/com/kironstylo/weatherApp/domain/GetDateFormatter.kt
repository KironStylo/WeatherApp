package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.model.Timezone.DateTimeFormatted
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDateFormatter @Inject constructor() {

    operator fun invoke (localTime: String, format:String): String{
        val currentTime = LocalDateTime.parse(localTime)
        val formatter = DateTimeFormatter.ofPattern(format)
        return currentTime.format(formatter)
    }

    fun get24hour(localTime: String): Int{
        val currentTime = LocalDateTime.parse(localTime)
        val hour = currentTime.hour
        return hour
    }

}