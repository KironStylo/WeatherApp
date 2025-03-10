package com.kironstylo.weatherApp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.LocationProvider
import com.kironstylo.weatherApp.data.model.Timezone.TimeProvider
import com.kironstylo.weatherApp.data.model.Weather.WeatherInfo
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.domain.GetDailyTemperature
import com.kironstylo.weatherApp.domain.GetHourTemperature
import com.kironstylo.weatherApp.domain.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getDailyTemperature: GetDailyTemperature,
    private val getHourTemperature: GetHourTemperature,
    private val locationProvider: LocationProvider,
    private val timeProvider: TimeProvider
): ViewModel() {

    private val _weatherCardLoading = MutableLiveData<Boolean>()
    val weatherCardLoading: LiveData<Boolean> =_weatherCardLoading

    private val _weatherInfo = MutableLiveData<WeatherInfo>()
    val weatherInfo : LiveData<WeatherInfo> = _weatherInfo

    private val _hourWeatherInfo = MutableLiveData<List<WeatherInfo>>()
    val hourWeatherInfo : LiveData<List<WeatherInfo>> = _hourWeatherInfo

      fun getWeather(){

        //isLoading.postValue(true)
        viewModelScope.launch {

            _weatherCardLoading.value = true
            val locationData = locationProvider.locationList
            val index = locationProvider.index
            Log.d("WeatherViewModel","Los datos de localizacion son"+ (locationData?.get(0)?.name))

            if(locationData?.isNotEmpty() == true && index != null){
                val location = locationData[index]
                val geoLocation: GeolocationDto = GeolocationDto(
                    location.name,
                    location.country,
                    location.alias,
                    location.latitude,
                    location.longitude
                )
                val result= getWeatherUseCase(geoLocation)

                Log.d("WeatherViewModel", "Localizacion: $location")
                Log.d("WeatherViewModel","Temperatura:"+result.toString())

                if(result!!.hourly.temperatures.isNotEmpty() && result.hourly.dates.isNotEmpty()){

                    val time = timeProvider.timezone
                    if(time != null){

                        val temperature = getDailyTemperature(result, time.currentLocalTime)
                        Log.d("WeatherViewModel", "Temperature: ${temperature.weatherTemperature}, Time: ${temperature.weatherTime24}")
                        val hourTemperature = getHourTemperature(result, time)

                        _weatherInfo.value  = temperature
                        _weatherCardLoading.value = false
                        _hourWeatherInfo.value = hourTemperature

                    }

                }
            }
        }
    }

}