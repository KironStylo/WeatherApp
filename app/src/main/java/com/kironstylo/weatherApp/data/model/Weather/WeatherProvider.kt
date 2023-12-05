package com.kironstylo.weatherApp.data.model.Weather

import com.kironstylo.weatherApp.core.RetrofitHelper
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.network.Forecast.WeatherApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherProvider {

    companion object{
        var weather: WeatherData? = null;
    }
}
