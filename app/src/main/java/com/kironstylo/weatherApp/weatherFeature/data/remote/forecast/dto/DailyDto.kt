package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto

import com.google.gson.annotations.SerializedName
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
            date = LocalDate.parse(date).atStartOfDay(),
            maxTemperature = maxTemperatures[index],
            minTemperature = minTemperatures[index],
            weatherCode = 1
        )
    }
}