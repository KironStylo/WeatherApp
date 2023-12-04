package com.kironstylo.weatherApp.data.model.Weather

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("hourly")
    val hourly: List<Hourly>,
    @SerializedName("temperature_2m")
    val temperatures:  List<String>
)
