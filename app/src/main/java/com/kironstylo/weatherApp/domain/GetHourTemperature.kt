package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import com.kironstylo.weatherApp.data.model.Weather.WeatherInfo
import javax.inject.Inject

class GetHourTemperature @Inject constructor(
    private val getDailyTemperature: GetDailyTemperature,
    private val getDateFormatter: GetDateFormatter
) {
    operator fun invoke(weatherData: WeatherData, timezone: Timezone): List<WeatherInfo>{
        val date = getDateFormatter(timezone.currentLocalTime, "yyyy-MM-dd")
        val hourlyWeatherList = mutableListOf<WeatherInfo>()
        val hourlyWeatherDate = weatherData.hourly.dates.filter{
            it.startsWith(date)
        }
        hourlyWeatherDate.forEach{
            hourlyWeatherList.add(getDailyTemperature(weatherData, it))
        }

        return hourlyWeatherList
    }
}