package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.GeoRepository
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData

class GetLocationUseCase {

    private val repository = GeoRepository();

    suspend operator fun invoke(cityName: String): LocationData? = repository.getLocation(cityName);
}