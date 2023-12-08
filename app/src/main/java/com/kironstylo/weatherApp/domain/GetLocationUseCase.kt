package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.network.Geolocation.GeoRepository
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: GeoRepository
){

    suspend operator fun invoke(cityName: String): LocationData? = repository.getLocation(cityName);
}