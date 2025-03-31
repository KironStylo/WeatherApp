package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

data class HourlyWeather (
    val time: String = "",
    val date: String = "",
    val temperature: Double = 7.0,
    val precipitation: Int = 8,
    val humidity: Int = 9,
    val windSpeed: Double = 15.0,
    val weatherCode: Int = 1,
)