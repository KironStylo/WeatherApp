package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

data class WeatherInfo(
    val weatherCode: Int = 1,
    val weatherTemperature: Double = 0.0,
    val weatherMinTemperature: Double = 0.0,
    val weatherMaxTemperature: Double = 0.0,
    val weatherTime12: String = "11 AM",
    val weatherTime24: Int = 11,
    val weatherPrecipitaion: Int = 90,
    val weatherHumidity: Int = 80,
    val weatherWindspeed: Double = 12.5,
)
