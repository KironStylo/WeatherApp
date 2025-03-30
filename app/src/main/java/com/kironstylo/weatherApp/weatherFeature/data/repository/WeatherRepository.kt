package com.kironstylo.weatherApp.weatherFeature.data.repository

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.WeatherService
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.Forecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val api: WeatherService
){
    fun getWeather(latitude: Double, longitude: Double): Flow<Resource<Forecast>>{
        return api.getWeather(latitude, longitude)
    }

    suspend fun getWeather(locationData: Geolocation): WeatherDataDto?{
        val response = api.getWeather(locationData);
        return response
    }

}