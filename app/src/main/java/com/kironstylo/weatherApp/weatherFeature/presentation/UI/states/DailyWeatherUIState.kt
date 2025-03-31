package com.kironstylo.weatherApp.weatherFeature.presentation.ui.states

import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather

data class DailyWeatherUIState (
    val dailyWeatherList: List<DailyWeather> = emptyList(),
    val selectedDailyWeather: DailyWeather = DailyWeather()
)