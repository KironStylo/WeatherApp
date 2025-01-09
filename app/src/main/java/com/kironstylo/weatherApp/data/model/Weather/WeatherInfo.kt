package com.kironstylo.weatherApp.data.model.Weather

data class WeatherInfo(
    val weatherCode: String,
    val weatherTemperature: Double,
    val weatherMinTemperature: Double,
    val weatherMaxTemperature: Double,
    val weatherTime: String
)
