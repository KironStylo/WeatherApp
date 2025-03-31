package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

data class DailyWeather(
    val date: String = "",
    val maxTemperature: Double = 2.0,
    val minTemperature: Double = 1.0,
    val weatherCode: Int = 1
)