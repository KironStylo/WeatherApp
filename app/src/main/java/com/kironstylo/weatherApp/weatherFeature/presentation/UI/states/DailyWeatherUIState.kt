package com.kironstylo.weatherApp.weatherFeature.presentation.UI.states

import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather

data class DailyWeatherUIState (
    val dailyWeatherList: List<DailyWeather> = emptyList(),
    val selectedDailyWeather: DailyWeather = DailyWeather()
)