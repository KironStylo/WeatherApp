package com.kironstylo.weatherApp.data.network.Forecast

import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherApiClient {
    @GET
    suspend fun getWeatherData(@Url url:String): Response<WeatherData>
}