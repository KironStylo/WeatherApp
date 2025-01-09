package com.kironstylo.weatherApp.domain

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDateFormatter @Inject constructor() {

    operator fun invoke (localTime: String, format:String): String{
        val currentTime = LocalDateTime.parse(localTime)
        val formatter = DateTimeFormatter.ofPattern(format)
        return currentTime.format(formatter)
    }
}