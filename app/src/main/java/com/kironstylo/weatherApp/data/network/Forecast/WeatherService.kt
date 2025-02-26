package com.kironstylo.weatherApp.data.network.Forecast

import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherService @Inject constructor(
    private val api: WeatherApiClient
){

    suspend fun getWeather(locationData: Result): WeatherData?{
        return withContext(Dispatchers.IO){
            val response = api
                .getWeatherData("forecast?latitude=${locationData.latitude}&longitude=${locationData.longitude}&hourly=temperature_2m,weather_code,relative_humidity_2m,precipitation_probability,wind_speed_10m&daily=temperature_2m_max,temperature_2m_min&timezone=auto")
            response.body()
        }
    }
}