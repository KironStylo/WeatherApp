package com.kironstylo.weatherApp.weatherFeature.data.remote.timezone

import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto.TimezoneDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeService @Inject constructor(
    private val api: TimeApiClient
) {
    suspend fun getTime(locationData: GeolocationDto): TimezoneDto?{
        return withContext(Dispatchers.IO){
            val response = api.getTimeData(locationData.latitude, locationData.longitude)
            response.body()
        }
    }
}