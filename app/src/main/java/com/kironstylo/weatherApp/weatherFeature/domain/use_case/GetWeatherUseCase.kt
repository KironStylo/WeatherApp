package com.kironstylo.weatherApp.weatherFeature.domain.use_case

import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import com.kironstylo.weatherApp.weatherFeature.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(locationData: Geolocation): WeatherDataDto? = repository.getWeather(locationData)

}