package com.kironstylo.weatherApp.searchCityFeature.data.remote

import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.LocationDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeoApiClient {
    @GET("search")
    suspend fun getLatitudeAndLongitude(
        @Query("name") name: String,
        @Query("count") count: Int = 10,
        @Query("language") language: String = "en",
        @Query("format") format: String = "json"
    ): Response<LocationDataDto>
}