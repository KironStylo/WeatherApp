package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

data class Forecast(
    val date: String,
    val hour: String,
    val currentWeatherInfo: WeatherInfo,
    val hourlyWeatherInfo: List<WeatherInfo>,
)
