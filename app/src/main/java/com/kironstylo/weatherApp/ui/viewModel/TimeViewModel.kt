package com.kironstylo.weatherApp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.LocationProvider
import com.kironstylo.weatherApp.data.model.Timezone.DateTimeFormatted
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.domain.GetFormattedDateUseCase
import com.kironstylo.weatherApp.domain.GetTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
    private val getTimeUseCase: GetTimeUseCase,
    private val getFormattedDateUseCase: GetFormattedDateUseCase
): ViewModel(){

    val timeModel = MutableLiveData<Timezone>()

    private val _timeZone = MutableLiveData<DateTimeFormatted>()
    val timeZone : LiveData<DateTimeFormatted> = _timeZone



    fun getTimeZone(){
        viewModelScope.launch{
            val locationData = locationProvider.locationList
            val index = locationProvider.index
            Log.d("TimeViewModel","Los datos de localizacion son"+ (locationData?.get(0)?.name ?: ""))

            if(locationData?.isNotEmpty()== true && index != null){
                val location = locationData[index]
                val geoLocation: GeolocationDto = GeolocationDto(
                    location.name,
                    location.country,
                    location.alias,
                    location.latitude,
                    location.longitude
                )
                val result = getTimeUseCase(geoLocation)

                if(result != null){
                    Log.d("TimeViewModel", "Time is ${result.currentLocalTime}")
                    _timeZone.value = getFormattedDateUseCase(result)
                }
            }
        }
    }

}