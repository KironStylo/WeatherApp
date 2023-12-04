package com.kironstylo.weatherApp.data.network.Geolocation

import com.kironstylo.weatherApp.core.RetrofitHelper
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeoService {
    private val baseUrl = "https://geocoding-api.open-meteo.com/v1/"
    private val retrofit = RetrofitHelper.getRetrofit(baseUrl)

    suspend fun getLocation(cityName: String): LocationData?{
        return withContext(Dispatchers.IO){
        val response = retrofit.create(GeoApiClient::class.java)
            .getLatitudeAndLongitude("search?name=$cityName&count=10&language=en&format=json")
            response.body()
        }
    }
}