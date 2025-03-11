package com.kironstylo.weatherApp.weatherFeature.data.remote.timezone

import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto.TimezoneDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApiClient {
    @GET("coordinate")
    suspend fun getTimeData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<TimezoneDto>
}