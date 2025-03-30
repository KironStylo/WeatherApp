package com.kironstylo.weatherApp.weatherFeature.data.remote.timezone

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class TimeService @Inject constructor(
    private val api: TimeApiClient
) {
    fun getTime(latitude: Double, longitude: Double): Flow<Resource<Timezone>> = flow{
        emit(Resource.Loading())
        try{
            val response = api.getTimeData(latitude = latitude, longitude = longitude)
            if(response.isSuccessful){
                val data = response.body()?.toTimezone() ?: Timezone()
                emit(Resource.Success(data))
            }
        }
        catch(e: IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
    suspend fun getTime(locationData: Geolocation): Timezone?{
        return withContext(Dispatchers.IO){
            api.getTimeData(locationData.latitude, locationData.longitude).body()?.toTimezone()
        }
    }
}