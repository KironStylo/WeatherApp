package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

import java.time.LocalDateTime

data class HourlyWeather (
    val date: LocalDateTime = LocalDateTime.now(),
    val temperature: Double = 7.0,
    val precipitation: Int = 8,
    val humidity: Int = 9,
    val windSpeed: Double = 15.0,
    val weatherCode: Int = 1,
)