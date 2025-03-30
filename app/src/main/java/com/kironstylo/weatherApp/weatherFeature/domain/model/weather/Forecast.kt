package com.kironstylo.weatherApp.weatherFeature.domain.model.weather

data class Forecast(
    val currentDate: String? = "",
    val currentHour: String? = "",
    val dailyWeather: List<DailyWeather> = emptyList(),
    val hourlyWeather: List<HourlyWeather> = emptyList()
)
