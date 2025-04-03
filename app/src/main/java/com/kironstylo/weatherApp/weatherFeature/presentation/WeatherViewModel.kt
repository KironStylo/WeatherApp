package com.kironstylo.weatherApp.weatherFeature.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.WeatherInfo
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.use_case.GetForecastUseCase
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.events.WeatherEvent
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.states.DailyWeatherUIState
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.states.HourlyWeatherUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _hourlyWeatherState = MutableStateFlow(HourlyWeatherUIState())
    val hourlyWeatherState: StateFlow<HourlyWeatherUIState> = _hourlyWeatherState.asStateFlow()

    private val _dailyWeatherState = MutableStateFlow(DailyWeatherUIState())
    val dailyWeatherState: StateFlow<DailyWeatherUIState> = _dailyWeatherState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    fun onEvent(weatherEvent: WeatherEvent){
        when(weatherEvent){
            is WeatherEvent.ChangeTimeEvent -> {
                _hourlyWeatherState.value  = hourlyWeatherState.value.copy(
                    selectedHourlyWeather = hourlyWeatherState.value.hourlyWeatherList.find {
                        it.date == weatherEvent.localTime
                    }!!
                )
            }
        }
    }

    fun getWeather(latitude: Double, longitude: Double) {
        Log.i("get Weather", "Latitude:$latitude, longitude:$longitude")
        viewModelScope.launch {
            getForecastUseCase(latitude = latitude, longitude = longitude).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _loadingState.value = false
                    }
                    is Resource.Loading -> {
                        _loadingState.value = true
                    }
                    is Resource.Success -> {
                        Log.i("WeatherViewModel", "API calls successful")
                        Log.i("WeatherViewModel", "Current Date: ${result.data?.currentDate}")
                        result.data?.hourlyWeather?.forEach {
                            Log.i("WeatherViewModel", "${it.date}")
                        }
                        _loadingState.value = false
                        _hourlyWeatherState.value = hourlyWeatherState.value.copy(
                            hourlyWeatherList = result.data?.hourlyWeather ?: emptyList(),
                            selectedHourlyWeather = result.data?.hourlyWeather?.firstOrNull {
                                Log.i("WeatherViewModel", "${it.date}")
                                it.date.toLocalDate() == result.data.currentDate.toLocalDate() &&
                                        it.date.hour == result.data.currentDate.hour
                            }?.copy(date = result.data.currentDate) ?: HourlyWeather()
                        )
                        Log.i("WeatherViewModel", "HourlyWeatherState filled")
                        Log.i("WeatherViewModel", "Selected ${_hourlyWeatherState.value.selectedHourlyWeather}")
                        _dailyWeatherState.value = dailyWeatherState.value.copy(
                            dailyWeatherList = result.data?.dailyWeather ?: emptyList(),
                            selectedDailyWeather = result.data?.dailyWeather?.firstOrNull{
                                it.date.toLocalDate() == result.data.currentDate.toLocalDate()
                            } ?: DailyWeather()
                        )
                        Log.i("WeatherViewModel", "DailyWeatherState filled")
                        Log.i("WeatherViewModel", "Selected ${_dailyWeatherState.value.selectedDailyWeather}")
                    }
                }
            }.collect()
        }
    }
}