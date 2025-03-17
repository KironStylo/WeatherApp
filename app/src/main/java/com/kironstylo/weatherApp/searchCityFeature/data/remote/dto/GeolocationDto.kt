package com.kironstylo.weatherApp.searchCityFeature.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation

data class GeolocationDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String?,
    @SerializedName("admin1")  val alias: String?,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
){
    fun toGeolocation(): Geolocation {
        return Geolocation(
            id = id,
            name = name,
            country = country ?: "",
            alias = alias ?: "",
            latitude = latitude,
            longitude = longitude
        )
    }
}
