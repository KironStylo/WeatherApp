package com.kironstylo.weatherApp.weatherFeature.data.remote.timezone

import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeService @Inject constructor(
    private val api: TimeApiClient
) {
    suspend fun getTime(locationData: Geolocation): Timezone?{
        return withContext(Dispatchers.IO){
            api.getTimeData(locationData.latitude, locationData.longitude).body()?.toTimezone()
        }
    }
}