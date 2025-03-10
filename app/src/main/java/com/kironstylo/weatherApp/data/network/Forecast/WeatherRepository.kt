package com.kironstylo.weatherApp.data.network.Forecast

import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import com.kironstylo.weatherApp.data.model.Weather.WeatherProvider
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val api: WeatherService
){
    suspend fun getWeather(locationData: GeolocationDto): WeatherData?{
        val response = api.getWeather(locationData);
        WeatherProvider.weather = response
        return response
    }

}