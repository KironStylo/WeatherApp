package com.kironstylo.weatherApp.searchCityFeature.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.searchCityFeature.domain.use_case.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeoViewModel @Inject constructor(
    private val getLocationUseCase : GetLocationUseCase
): ViewModel() {

    private val _cityList = MutableLiveData<List<Geolocation>>()
    val cityList : LiveData<List<Geolocation>> = _cityList
    private val _cityName = MutableLiveData<String>()
    val cityName : LiveData<String> = _cityName

     fun searchCity(cityName: String) {
        viewModelScope.launch {
            val result = getLocationUseCase(cityName)
            Log.d("Location Data","Cities which match the name $cityName : $result")
            _cityList.value = result
        }
    }

    fun onCityNameChanged(cityName : String){
        _cityName.value = cityName
    }
}