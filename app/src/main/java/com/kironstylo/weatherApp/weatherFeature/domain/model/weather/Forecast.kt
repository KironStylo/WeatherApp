package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

import java.time.LocalDateTime

data class Forecast(
    val currentDate: LocalDateTime = LocalDateTime.now(),
    val dailyWeather: List<DailyWeather> = emptyList(),
    val hourlyWeather: List<HourlyWeather> = emptyList()
)
