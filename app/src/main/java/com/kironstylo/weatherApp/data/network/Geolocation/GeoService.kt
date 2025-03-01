package com.kironstylo.weatherApp.data.network.Geolocation

import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GeoService @Inject constructor(
    private val api: GeoApiClient
) {
    suspend fun getLocation(cityName: String): LocationData?{
        return withContext(Dispatchers.IO){
        val response = api
            .getLatitudeAndLongitude("search?name=$cityName&count=10&language=en&format=json")
            response.body()
        }
    }
}