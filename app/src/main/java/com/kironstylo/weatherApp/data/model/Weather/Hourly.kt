package com.kironstylo.weatherApp.data.model.Weather

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("time")
    val dates: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("weather_code")
    val codes: List<Int>
)
