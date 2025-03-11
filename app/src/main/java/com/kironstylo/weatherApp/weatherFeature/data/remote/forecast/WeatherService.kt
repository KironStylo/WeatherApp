package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast

import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherService @Inject constructor(
    private val api: WeatherApiClient
){

    suspend fun getWeather(locationData: Geolocation): WeatherDataDto?{
        return withContext(Dispatchers.IO){
            val response1 = api
                .getWeatherData(locationData.latitude, locationData.longitude)
            response1.body()
        }
    }
}