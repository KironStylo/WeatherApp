package com.kironstylo.weatherApp.data.network.Geolocation

import android.util.Log
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider

class GeoRepository {

    private val api = GeoService()

    suspend fun getLocation(cityName: String): LocationData? {
        val response = api.getLocation(cityName);
        LocationProvider.location = response
        Log.d("Location Provider","Los datos de localización son"+LocationProvider.location!!.results[0].name)
        return response
    }

}