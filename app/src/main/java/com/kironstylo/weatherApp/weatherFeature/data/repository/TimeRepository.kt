package com.kironstylo.weatherApp.weatherFeature.data.repository

import android.util.Log
import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.TimeService
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimeRepository @Inject constructor(
    private val api: TimeService)
{
    fun getTime(latitude: Double, longitude: Double): Flow<Resource<Timezone>> {
        return api.getTime(latitude = latitude, longitude = longitude)
    }
    
    suspend fun getTime(locationData: Geolocation): Timezone?{
        val response = api.getTime(locationData)
        Log.d("TimeRepository","Local Time received: ${response?.localTime}")
        return response
    }
}