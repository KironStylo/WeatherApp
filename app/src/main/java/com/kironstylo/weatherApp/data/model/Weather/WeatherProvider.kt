package com.kironstylo.weatherApp.data.model.Weather

import com.kironstylo.weatherApp.core.RetrofitHelper
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.network.Forecast.WeatherApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherProvider {
    private val baseUrl  = "https://api.open-meteo.com/v1/"
    private val retrofit = RetrofitHelper.getRetrofit(baseUrl)

    suspend fun getWeather(locationData: Result):WeatherData?{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(WeatherApiClient::class.java)
                .getWeatherData("forecast?latitude=${locationData.latitude}&longitude=${locationData.longitude}&hourly=temperature_2m&timezone=auto")
            response.body()
        }
    }

}
