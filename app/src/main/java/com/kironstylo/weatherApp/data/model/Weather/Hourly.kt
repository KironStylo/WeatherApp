package com.kironstylo.weatherApp.data.model.Weather

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("time")
    val dates: List<String>
)
