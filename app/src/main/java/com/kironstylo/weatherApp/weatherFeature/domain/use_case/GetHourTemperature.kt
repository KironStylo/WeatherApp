package com.kironstylo.weatherApp.weatherFeature.domain.use_case

import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.WeatherInfo
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import java.time.LocalDateTime
import javax.inject.Inject

class GetHourTemperature @Inject constructor(
    private val getDailyTemperature: GetDailyTemperature,
) {
    operator fun invoke(weatherDataDto: WeatherDataDto, localTime: LocalDateTime): List<WeatherInfo>{
        val date = DateFormatter.formatDate(localTime, "yyyy-MM-dd")
        val hourlyWeatherList = mutableListOf<WeatherInfo>()
        val hourlyWeatherDate = weatherDataDto.hourlyDto.dates.filter{
            it.startsWith(date)
        }.map{
            LocalDateTime.parse(it)
        }

        hourlyWeatherDate.forEach{
            hourlyWeatherList.add(getDailyTemperature(weatherDataDto, it))
        }

        return hourlyWeatherList
    }
}