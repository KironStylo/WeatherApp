package com.kironstylo.weatherApp.data.model.Timezone

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Timezone(
    @SerializedName("currentLocalTime")
    val currentLocalTime: LocalDateTime
)
