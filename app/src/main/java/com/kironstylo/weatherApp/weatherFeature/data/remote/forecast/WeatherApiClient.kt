package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast

import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiClient {
    @GET("forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: List<String> = listOf("temperature_2m","weather_code","relative_humidity_2m","precipitation_probability","wind_speed_10m"),
        @Query("daily") daily: List<String> = listOf("temperature_2m_min","temperature_2m_max"),
        @Query("timezone") timezone: String = "auto"
    ): Response<WeatherDataDto>
}