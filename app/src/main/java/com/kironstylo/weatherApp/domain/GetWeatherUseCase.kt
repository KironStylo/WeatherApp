package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import com.kironstylo.weatherApp.data.network.Forecast.WeatherRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetWeatherUseCase {

    private val repository = WeatherRepository();

    suspend operator fun invoke(locationData: Result): WeatherData? = repository.getWeather(locationData)

}