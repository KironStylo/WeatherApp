package com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto

import com.google.gson.annotations.SerializedName
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import java.time.LocalDateTime

data class TimezoneDto(
    @SerializedName("currentLocalTime")
    val currentLocalTime: String
){
    fun toTimezone(): Timezone {
        return Timezone(
            LocalDateTime.parse(currentLocalTime)
        )
    }
}
