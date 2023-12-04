package com.kironstylo.weatherApp.data

import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider
import com.kironstylo.weatherApp.data.network.Geolocation.GeoService

class GeoRepository {

    private val api = GeoService()

    suspend fun getLocation(cityName: String): LocationData? {
        val response = api.getLocation(cityName);
        LocationProvider.location = response
        return response;
    }

}