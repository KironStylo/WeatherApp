package com.kironstylo.weatherApp.weatherFeature.domain.use_case

import android.util.Log
import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.weatherFeature.data.repository.TimeRepository
import com.kironstylo.weatherApp.weatherFeature.data.repository.WeatherRepository
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.Forecast
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val timeRepository: TimeRepository
) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<Forecast>> =
        combine(
            weatherRepository.getWeather(latitude = latitude, longitude = longitude),
            timeRepository.getTime(latitude = latitude, longitude = longitude)
        ) { weatherResult, timeResult ->
            when {
                weatherResult is Resource.Loading || timeResult is Resource.Loading -> {
                    Log.i("ForecastUC", "Loading")
                    Resource.Loading()
                }
                weatherResult is Resource.Success && timeResult is Resource.Success -> {
                    Log.i("ForecastUC", "Successful")
                    Log.i("ForecastUC", "Succesful \n ${weatherResult.data?.dailyWeather}")
                    Resource.Success(
                        weatherResult.data?.copy(
                            currentDate = timeResult.data?.localTime ?: LocalDateTime.now()
                        ) ?: Forecast()
                    )
                }
                weatherResult is Resource.Error -> {
                    Log.i("ForecastUC", "Error Weather")
                    Resource.Error(weatherResult.message)
                }

                timeResult is Resource.Error -> {
                    Log.i("ForecastUC", "Error Time")

                    Resource.Error(timeResult.message)
                }

                else -> {
                    Resource.Error("Couldn't retrieve weather info")
                }
            }
        }
}