package com.kironstylo.weatherApp.data.network.Timezone

import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface TimeApiClient {
    @GET
    suspend fun getTimeData(@Url url:String): Response<Timezone>
}