package com.kironstylo.weatherApp.data.model.Weather

import com.google.gson.annotations.SerializedName

data class Daily (
    @SerializedName("time")
    val dates:List<String>,
    @SerializedName("temperature_2m_max")
    val maxTemperatures: List<Double>,
    @SerializedName("temperature_2m_min")
    val minTemperatures: List<Double>,
    )