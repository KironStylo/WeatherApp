package com.kironstylo.weatherApp.weatherFeature.presentation.UI.states

import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather

data class HourlyWeatherUIState (
    val hourlyWeatherList: List<HourlyWeather> = emptyList(),
    val selectedHourlyWeather: HourlyWeather = HourlyWeather()
)