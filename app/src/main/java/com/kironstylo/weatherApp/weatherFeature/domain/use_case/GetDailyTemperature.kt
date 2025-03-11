package com.kironstylo.weatherApp.weatherFeature.domain.use_case

import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.WeatherInfo
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import java.time.LocalDateTime
import javax.inject.Inject

class GetDailyTemperature @Inject constructor(
) {

     operator fun invoke (weatherDataDto: WeatherDataDto, currentTime: LocalDateTime): WeatherInfo {
         val hourlyDate = DateFormatter.formatDate(currentTime, "yyyy-MM-dd'T'HH:00")
         val dailyDate = DateFormatter.formatDate(currentTime, "yyyy-MM-dd")
         val time12 = DateFormatter.formatDate(currentTime, "hh a")
         val time24 = DateFormatter.formatDate(currentTime, "HH").toInt()


         val hourlyIndex = weatherDataDto.hourlyDto.dates.indexOf(hourlyDate)
         val dailyIndex = weatherDataDto.dailyDto.dates.indexOf(dailyDate)

         // Weather Info
         val currentTemp = weatherDataDto.hourlyDto.temperatures[hourlyIndex]
         val weatherCode = weatherDataDto.hourlyDto.codes[hourlyIndex]
         val currentSpeed = weatherDataDto.hourlyDto.windspeedList[hourlyIndex]
         val currentHumidity = weatherDataDto.hourlyDto.humidityList[hourlyIndex]
         val currentPrecipitation = weatherDataDto.hourlyDto.precipitationList[hourlyIndex]
         val maxTemp = weatherDataDto.dailyDto.maxTemperatures[dailyIndex]
         val minTemp = weatherDataDto.dailyDto.minTemperatures[dailyIndex]

         val weatherInfo = WeatherInfo(weatherCode,currentTemp,minTemp, maxTemp, time12, time24, currentPrecipitation, currentHumidity, currentSpeed)

         return weatherInfo
    }
}