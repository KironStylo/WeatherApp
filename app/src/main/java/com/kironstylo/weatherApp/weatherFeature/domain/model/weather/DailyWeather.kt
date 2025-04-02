package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

import java.time.LocalDateTime

data class DailyWeather(
    val date: LocalDateTime = LocalDateTime.now(),
    val maxTemperature: Double = 2.0,
    val minTemperature: Double = 1.0,
    val weatherCode: Int = 1
)