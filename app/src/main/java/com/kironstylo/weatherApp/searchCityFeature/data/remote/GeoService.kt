package com.kironstylo.weatherApp.searchCityFeature.data.remote

import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GeoService @Inject constructor(
    private val api: GeoApiClient
) {
    suspend fun getLocation(cityName: String): List<Geolocation>{
        return withContext(Dispatchers.IO){
            api.getLatitudeAndLongitude(cityName).body()?.results?.map{
                    it.toGeolocation()
                } ?: emptyList()
        }
    }
}