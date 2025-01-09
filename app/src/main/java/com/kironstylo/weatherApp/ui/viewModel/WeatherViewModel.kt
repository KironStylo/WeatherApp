package com.kironstylo.weatherApp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider
import com.kironstylo.weatherApp.data.model.Timezone.TimeProvider
import com.kironstylo.weatherApp.data.model.Weather.Temperature
import com.kironstylo.weatherApp.data.model.Weather.WeatherInfo
import com.kironstylo.weatherApp.domain.GetDailyTemperature
import com.kironstylo.weatherApp.domain.GetTemperatureUseCase
import com.kironstylo.weatherApp.domain.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getDailyTemperature: GetDailyTemperature,
    private val locationProvider:  LocationProvider,
    private val timeProvider: TimeProvider
): ViewModel() {


    private val _weatherInfo = MutableLiveData<WeatherInfo>()
    val weatherInfo : LiveData<WeatherInfo> = _weatherInfo

      fun getWeather(){

        //isLoading.postValue(true)
        viewModelScope.launch {


            val locationData = locationProvider.location
            val index = locationProvider.index
            Log.d("WeatherViewModel","Los datos de localizacion son"+ (locationData?.results?.get(0)?.name))

            if(locationData?.results?.isNotEmpty() == true && index != null){
                val location = locationData.results[index]
                val result= getWeatherUseCase(location)

                Log.d("WeatherViewModel", "Localizacion: $location")
                Log.d("WeatherViewModel","Temperatura:"+result.toString())

                if(result!!.hourly.temperatures.isNotEmpty() && result!!.hourly.dates.isNotEmpty()){

                    val time = timeProvider.timezone
                    if(time != null){
                        val temperature = getDailyTemperature(result, time)
                        Log.d("WeatherViewModel", "Temperature: ${temperature.weatherTemperature}")
                        _weatherInfo.value  = temperature

                    }

                }
            }
        }
    }

}