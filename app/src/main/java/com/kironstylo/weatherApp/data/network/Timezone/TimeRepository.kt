package com.kironstylo.weatherApp.data.network.Timezone

import android.util.Log
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.model.Timezone.TimeProvider
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import javax.inject.Inject

class TimeRepository @Inject constructor(
    private val api: TimeService,
    private val timeProvider: TimeProvider)
{

    suspend fun getTime(locationData: Result): Timezone?{
        val response = api.getTime(locationData)
        Log.d("TimeRepository","Local Time received: ${response?.currentLocalTime}")
        timeProvider.timezone = response
        return response
    }
}