package com.kironstylo.weatherApp.data.network.Timezone

import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeService @Inject constructor(
    private val api: TimeApiClient
) {

    suspend fun getTime(locationData: Result): Timezone?{
        return withContext(Dispatchers.IO){
            val response = api.getTimeData("coordinate?latitude=${locationData.latitude}&longitude=${locationData.longitude}")
            response.body()
        }
    }
}