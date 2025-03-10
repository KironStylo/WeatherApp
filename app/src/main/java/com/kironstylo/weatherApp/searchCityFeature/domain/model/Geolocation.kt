package com.kironstylo.weatherApp.searchCityFeature.domain.model

data class Geolocation(
    val name: String = "",
    val country: String ="",
    val alias: String = "",
    val latitude: Double,
    val longitude: Double
)
