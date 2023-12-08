package com.kironstylo.weatherApp.ui.viewModel

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.model.Weather.Hourly
import com.kironstylo.weatherApp.data.model.Weather.Temperature
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import com.kironstylo.weatherApp.domain.GetLocationUseCase
import com.kironstylo.weatherApp.domain.GetTemperatureUseCase
import com.kironstylo.weatherApp.domain.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getTemperatureUseCase: GetTemperatureUseCase,
    private val locationProvider:  LocationProvider
): ViewModel() {

    val weatherModel  = MutableLiveData<Temperature>()
    val isLoading = MutableLiveData<Boolean>()

     fun getTemperature(){

        isLoading.postValue(true)
        viewModelScope.launch {


            val locationData = locationProvider.location
            Log.d("WeatherViewModel","Los datos de localizacion son"+ (locationData?.results?.get(0)?.name))

            if(locationData?.results?.isNotEmpty() == true){
                val location = locationData.results[0]
                val result= getWeatherUseCase(location)

                Log.d("Datos de localización", location.toString())
                Log.d("Datos de temperatura",result.toString())

                if(result!!.hourly.temperatures.isNotEmpty() && result!!.hourly.dates.isNotEmpty()){

                    val temperature = getTemperatureUseCase.getTemperature(result)
                    weatherModel.postValue(temperature)
                    isLoading.postValue(false)

                }
            }else{

            }


        }
    }

}