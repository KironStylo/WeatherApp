package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto

import com.google.gson.annotations.SerializedName
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import java.time.LocalDateTime

data class DailyDto (
    @SerializedName("time")
    val dates:List<String>,
    @SerializedName("temperature_2m_max")
    val maxTemperatures: List<Double>,
    @SerializedName("temperature_2m_min")
    val minTemperatures: List<Double>,
    )
fun DailyDto.toDailyWeather():List<DailyWeather>
{
    return dates.mapIndexed{ index, date ->
        DailyWeather(
            date = LocalDateTime.parse(date),
            maxTemperature = maxTemperatures[index],
            minTemperature = minTemperatures[index],
            weatherCode = 1
        )
    }
}