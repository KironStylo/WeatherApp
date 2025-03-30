package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

data class DailyWeather(
    val date: String,
    val maxTemperature: Double,
    val minTemperature: Double,
    val weatherCode: Int
)