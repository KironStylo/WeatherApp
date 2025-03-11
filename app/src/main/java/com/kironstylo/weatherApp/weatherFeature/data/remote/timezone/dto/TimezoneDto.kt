package com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto

import com.google.gson.annotations.SerializedName

data class TimezoneDto(
    @SerializedName("currentLocalTime")
    val currentLocalTime: String
)
