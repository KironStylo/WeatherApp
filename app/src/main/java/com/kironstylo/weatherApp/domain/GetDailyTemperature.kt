package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import com.kironstylo.weatherApp.data.model.Weather.WeatherInfo
import javax.inject.Inject

class GetDailyTemperature @Inject constructor(
    private val getDateFormatter: GetDateFormatter
) {

     operator fun invoke (weatherData: WeatherData, timezone: Timezone): WeatherInfo {
         val hourlyDate = getDateFormatter(timezone.currentLocalTime, "yyyy-MM-dd'T'HH:00")
         val dailyDate = getDateFormatter(timezone.currentLocalTime, "yyyy-MM-dd")
         val time = getDateFormatter(timezone.currentLocalTime, "hh a")

         val hourlyIndex = weatherData.hourly.dates.indexOf(hourlyDate)
         val dailyIndex = weatherData.daily.dates.indexOf(dailyDate)

         // Weather Info
         val currentTemp = weatherData.hourly.temperatures[hourlyIndex]
         val maxTemp = weatherData.daily.maxTemperatures[dailyIndex]
         val minTemp = weatherData.daily.minTemperatures[dailyIndex]

         val weatherInfo = WeatherInfo("",currentTemp,minTemp, maxTemp, time)

         return weatherInfo
    }
}