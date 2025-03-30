package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

data class HourlyWeather (
    val time: String,
    val date: String,
    val temperature: Double,
    val precipitation: Int,
    val humidity: Int,
    val windSpeed: Double,
    val weatherCode: Int,
)