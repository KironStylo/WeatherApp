package com.kironstylo.weatherApp.data.model.GeoLocation

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)
