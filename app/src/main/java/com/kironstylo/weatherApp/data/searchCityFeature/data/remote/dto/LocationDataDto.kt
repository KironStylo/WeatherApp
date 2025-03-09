package com.kironstylo.weatherApp.data.searchCityFeature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LocationDataDto(
    @SerializedName("results") val results: List<GeolocationDto>? = listOf(),
    @SerializedName("generationtime_ms") val generationTimeMs: Double
)
