package com.kironstylo.weatherApp.data.model.GeoLocation

import com.google.gson.annotations.SerializedName

data class LocationData(
    @SerializedName("results") val results: List<Result>,
    @SerializedName("generationtime_ms") val generationTimeMs: Double
)