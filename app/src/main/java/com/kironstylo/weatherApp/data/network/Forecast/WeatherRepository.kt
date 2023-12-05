package com.kironstylo.weatherApp.data.network.Forecast

import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import com.kironstylo.weatherApp.data.model.Weather.WeatherProvider


class WeatherRepository {
    private val api = WeatherService()

    suspend fun getWeather(locationData: Result): WeatherData?{
        val response = api.getWeather(locationData);
        WeatherProvider.weather = response
        return response
    }

}