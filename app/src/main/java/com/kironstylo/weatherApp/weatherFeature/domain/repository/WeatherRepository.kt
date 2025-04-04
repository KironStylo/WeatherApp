package com.kironstylo.weatherApp.weatherFeature.domain.repository

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.Forecast
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double): Flow<Resource<Forecast>>
}