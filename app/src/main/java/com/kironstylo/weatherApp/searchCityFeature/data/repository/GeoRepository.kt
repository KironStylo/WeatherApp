package com.kironstylo.weatherApp.searchCityFeature.data.repository

import android.util.Log
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.LocationDataDto
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.LocationProvider
import com.kironstylo.weatherApp.searchCityFeature.data.remote.GeoService
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import javax.inject.Inject

class GeoRepository @Inject constructor(private val api: GeoService, private val locationProvider: LocationProvider){

    suspend fun getLocation(cityName: String): List<Geolocation> {
        val response = api.getLocation(cityName);
        locationProvider.locationList = response
        Log.d("Location Provider",
            ("Los datos de localización son" + locationProvider.locationList)
        )
        return response
    }

}