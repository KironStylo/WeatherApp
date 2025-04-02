package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto

import com.google.gson.annotations.SerializedName
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import java.time.LocalDateTime

data class HourlyDto(
    @SerializedName("time")
    val dates: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("precipitation_probability")
    val precipitationList: List<Int>,
    @SerializedName("relative_humidity_2m")
    val humidityList: List<Int>,
    @SerializedName("wind_speed_10m")
    val windspeedList: List<Double>,
    @SerializedName("weather_code")
    val codes: List<Int>
)
fun HourlyDto.toHourlyWeather(): List<HourlyWeather>{
    return dates.mapIndexed{ index, date ->
        HourlyWeather(
            date = LocalDateTime.parse(date),
            temperature = temperatures[index],
            precipitation = precipitationList[index],
            humidity = humidityList[index],
            windSpeed = windspeedList[index],
            weatherCode = codes[index]
        )
    }
}
