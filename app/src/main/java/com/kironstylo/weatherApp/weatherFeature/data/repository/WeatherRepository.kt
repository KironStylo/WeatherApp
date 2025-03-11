package com.kironstylo.weatherApp.weatherFeature.data.repository

import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import com.kironstylo.weatherApp.data.model.Weather.WeatherProvider
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.WeatherService
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val api: WeatherService
){
    suspend fun getWeather(locationData: Geolocation): WeatherDataDto?{
        val response = api.getWeather(locationData);
        WeatherProvider.weather = response
        return response
    }

}