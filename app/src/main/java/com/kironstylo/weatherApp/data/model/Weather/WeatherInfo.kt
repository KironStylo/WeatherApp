package com.kironstylo.weatherApp.data.model.Weather

data class WeatherInfo(
    val weatherCode: String = "",
    val weatherTemperature: Double = 0.0,
    val weatherMinTemperature: Double = 0.0,
    val weatherMaxTemperature: Double = 0.0,
    val weatherTime: String ="11"
)
