package com.kironstylo.weatherApp.weatherFeature.data.repository

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.WeatherApiClient
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.toDailyWeather
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.toHourlyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.Forecast
import com.kironstylo.weatherApp.weatherFeature.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException


class WeatherRepositoryImpl (
    private val api: WeatherApiClient
): WeatherRepository{

    override fun getWeather(latitude: Double, longitude: Double): Flow<Resource<Forecast>> = flow{
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

}