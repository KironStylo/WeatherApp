package com.kironstylo.weatherApp.weatherFeature.data.repository

import android.util.Log
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.data.model.Timezone.TimeProvider
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.TimeService
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto.TimezoneDto
import javax.inject.Inject

class TimeRepository @Inject constructor(
    private val api: TimeService,
    private val timeProvider: TimeProvider)
{

    suspend fun getTime(locationData: GeolocationDto): TimezoneDto?{
        val response = api.getTime(locationData)
        Log.d("TimeRepository","Local Time received: ${response?.currentLocalTime}")
        timeProvider.timezoneDto = response
        return response
    }
}