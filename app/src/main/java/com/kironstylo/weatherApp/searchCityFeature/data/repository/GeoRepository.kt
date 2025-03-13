package com.kironstylo.weatherApp.searchCityFeature.data.repository

import android.util.Log
import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.searchCityFeature.data.remote.GeoService
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeoRepository @Inject constructor(private val api: GeoService){

     fun getLocation(cityName: String): Flow<Resource<List<Geolocation>>> {
        val response = api.getLocation(cityName);
        return response
    }

}