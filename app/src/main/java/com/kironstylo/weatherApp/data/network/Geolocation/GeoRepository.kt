package com.kironstylo.weatherApp.data.network.Geolocation

import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider

class GeoRepository {

    private val api = GeoService()

    suspend fun getLocation(cityName: String): LocationData? {
        val response = api.getLocation(cityName);
        LocationProvider.location = response
        return response
    }

}