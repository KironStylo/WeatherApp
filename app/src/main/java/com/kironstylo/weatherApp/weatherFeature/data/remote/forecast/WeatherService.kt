package com.kironstylo.weatherApp.weatherFeature.data.remote.forecast

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.toDailyWeather
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.toHourlyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.Forecast
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class WeatherService @Inject constructor(
    private val api: WeatherApiClient
){
    fun getWeather(latitude: Double, longitude: Double): Flow<Resource<Forecast>> = flow{
        emit(Resource.Loading())
        try{
            val response = api.getWeatherData(latitude, longitude)
            if(response.isSuccessful){
                emit(Resource.Success(Forecast(
                    dailyWeather = response.body()?.dailyDto?.toDailyWeather() ?: emptyList(),
                    hourlyWeather = response.body()?.hourlyDto?.toHourlyWeather() ?: emptyList()
                )))
            }
        }
        catch(e: IOException){
            emit(Resource.Error("Couldn't reach server. Please Check Internet Connection"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getWeather(locationData: Geolocation): WeatherDataDto?{
        return withContext(Dispatchers.IO){
            val response1 = api
                .getWeatherData(locationData.latitude, locationData.longitude)
            response1.body()
        }
    }
}