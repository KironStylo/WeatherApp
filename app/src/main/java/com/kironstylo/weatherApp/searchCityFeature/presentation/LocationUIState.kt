package com.kironstylo.weatherApp.searchCityFeature.presentation

import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation

data class LocationUIState (
    val geolocationItems: List<Geolocation> = emptyList(),
    val selectedItem: Geolocation? = null,
    val isLoading: Boolean = false,
    val searchComplete: Boolean = false
)