package com.kironstylo.weatherApp.data.network.Geolocation

import android.util.Log
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider
import javax.inject.Inject

class GeoRepository @Inject constructor(private val api: GeoService, private val locationProvider: LocationProvider){

    suspend fun getLocation(cityName: String): LocationData? {
        val response = api.getLocation(cityName);
        locationProvider.location = response
        Log.d("Location Provider","Los datos de localización son"+locationProvider.location!!.results[0].name)
        return response
    }

}