package com.kironstylo.weatherApp.weatherFeature.data.repository

import android.util.Log
import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.TimeApiClient
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import com.kironstylo.weatherApp.weatherFeature.domain.repository.TimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class TimeRepositoryImpl(
    private val api: TimeApiClient) : TimeRepository
{
    override fun getTime(latitude: Double, longitude: Double): Flow<Resource<Timezone>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getTimeData(latitude = latitude, longitude = longitude)

            if (response.isSuccessful) {
                Log.i("Time Service", "API was successful")
                val data = response.body()?.toTimezone() ?: Timezone()
                emit(Resource.Success(data))
            } else {
                Log.e("Time Service", "API call failed with code: ${response.code()} and message: ${response.message()}")
                emit(Resource.Error("API error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: IOException) {
            // Handle network errors
            Log.e("Time Service", "Couldn't reach server: ${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            // Handle unexpected errors
            Log.e("Time Service", "Unexpected error: ${e.localizedMessage}")
            emit(Resource.Error("An unexpected error occurred: ${e.localizedMessage}"))
        }
    }
}