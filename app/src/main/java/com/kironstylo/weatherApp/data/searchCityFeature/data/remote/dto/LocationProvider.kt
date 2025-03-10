package com.kironstylo.weatherApp.data.searchCityFeature.data.remote.dto

import com.kironstylo.weatherApp.data.searchCityFeature.domain.model.Geolocation
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocationProvider @Inject constructor(){
    var locationList: List<Geolocation>? = emptyList()
    var location: LocationDataDto? = null;
    var index: Int? = 0;
}