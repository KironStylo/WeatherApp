package com.kironstylo.weatherApp.weatherFeature.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.WeatherInfo
import com.kironstylo.weatherApp.weatherFeature.domain.use_case.GetDailyTemperature
import com.kironstylo.weatherApp.weatherFeature.domain.use_case.GetHourTemperature
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.domain.use_case.WeatherUseCases
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import com.kironstylo.weatherApp.weatherFeature.domain.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCases: WeatherUseCases,
    private val getDailyTemperature: GetDailyTemperature,
    private val getHourTemperature: GetHourTemperature,
): ViewModel() {

    private val _weatherCardLoading = MutableLiveData<Boolean>()
    val weatherCardLoading: LiveData<Boolean> =_weatherCardLoading

    private val _weatherInfo = MutableLiveData<WeatherInfo>()
    val weatherInfo : LiveData<WeatherInfo> = _weatherInfo

    private val _hourWeatherInfo = MutableLiveData<List<WeatherInfo>>()
    val hourWeatherInfo : LiveData<List<WeatherInfo>> = _hourWeatherInfo

    private val _timezoneInfo = MutableLiveData<DateTime>()
    val timeZoneInfo: LiveData<DateTime> = _timezoneInfo

      fun getWeather(location: Geolocation){

        //isLoading.postValue(true)
        viewModelScope.launch {

            _weatherCardLoading.value = true

            Log.d("WeatherViewModel","Los datos de localizacion son ${listOf(location.country,location.name, location.longitude, location.latitude)}")

            // Call Timezone API to retrieve current date time
            val timezone = weatherUseCases.getTimeUseCase(location)
            // Call Weather API to obtain the latest data of forecast
            val weather = weatherUseCases.getWeatherUseCase(location)
            if(timezone != null && weather != null){
                val currentTemperature = getDailyTemperature(weather, timezone.localTime)
                val hourTemperature = getHourTemperature(weather, timezone.localTime)

                _weatherInfo.value = currentTemperature
                _hourWeatherInfo.value = hourTemperature
                _timezoneInfo.value = DateTime(timezone.localTime)
                _weatherCardLoading.value = false

            }
            else{
                _weatherCardLoading.value = false
            }

        }
    }

    data class DateTime(val localDateTime: LocalDateTime){
         val date: String = DateFormatter.formatDate(localDateTime, "dd/MM/yyyy")
         val hour: String = DateFormatter.formatDate(localDateTime, "hh:mm a")

    }

}