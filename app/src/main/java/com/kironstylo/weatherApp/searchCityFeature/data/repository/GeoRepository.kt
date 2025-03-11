package com.kironstylo.weatherApp.searchCityFeature.data.repository

import android.util.Log
import com.kironstylo.weatherApp.searchCityFeature.data.remote.GeoService
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import javax.inject.Inject

class GeoRepository @Inject constructor(private val api: GeoService){

    suspend fun getLocation(cityName: String): List<Geolocation> {
        val response = api.getLocation(cityName);
        return response
    }

}