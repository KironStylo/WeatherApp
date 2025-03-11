package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto

import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    @SerializedName("hourly")
    val hourlyDto: HourlyDto,
    @SerializedName("daily")
    val dailyDto: DailyDto
)
