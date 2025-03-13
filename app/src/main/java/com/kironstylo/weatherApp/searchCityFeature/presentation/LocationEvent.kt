package com.kironstylo.weatherApp.searchCityFeature.presentation

import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation

sealed class LocationEvent {
    data class SearchEvent(val query: String): LocationEvent()
    data class ChooseCity(val geolocation: Geolocation): LocationEvent()
}