package com.kironstylo.weatherApp.data.model.GeoLocation

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("admin1")  val alias: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)
