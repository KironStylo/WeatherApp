package com.kironstylo.weatherApp.data.network.Timezone

import android.util.Log
import com.kironstylo.weatherApp.data.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.data.model.Timezone.TimeProvider
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import javax.inject.Inject

class TimeRepository @Inject constructor(
    private val api: TimeService,
    private val timeProvider: TimeProvider)
{

    suspend fun getTime(locationData: GeolocationDto): Timezone?{
        val response = api.getTime(locationData)
        Log.d("TimeRepository","Local Time received: ${response?.currentLocalTime}")
        timeProvider.timezone = response
        return response
    }
}