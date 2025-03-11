package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto

import com.google.gson.annotations.SerializedName

data class DailyDto (
    @SerializedName("time")
    val dates:List<String>,
    @SerializedName("temperature_2m_max")
    val maxTemperatures: List<Double>,
    @SerializedName("temperature_2m_min")
    val minTemperatures: List<Double>,
    )