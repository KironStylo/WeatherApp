package com.kironstylo.weatherApp.searchCityFeature.domain.model

data class Geolocation(
    val id: Int,
    val name: String = "",
    val country: String ="",
    val alias: String = "",
    val latitude: Double,
    val longitude: Double
)
