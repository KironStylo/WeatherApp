package com.kironstylo.weatherApp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.domain.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeoViewModel @Inject constructor(
    private val getLocationUseCase : GetLocationUseCase,
    private val locationProvider: LocationProvider
): ViewModel() {

    val geoModel = MutableLiveData<List<Result>>()
    val isVisibleCard = MutableLiveData<Boolean>()
    val isVisibleList = MutableLiveData<Boolean>()
    //val cityName = MutableLiveData<String>()

    private val _cityList = MutableLiveData<List<Result>>()
    val cityList : LiveData<List<Result>> = _cityList
    private val _cityName = MutableLiveData<String>()
    val cityName : LiveData<String> = _cityName

     fun searchCity(cityName: String) {
        viewModelScope.launch {
            val result = getLocationUseCase(cityName)
            Log.d("Location Data","Cities which match the name $cityName : $result")
            if(result != null){
                if(!result.results.isNullOrEmpty())
                    _cityList.value = result.results ?: listOf()
            }
            else{
                _cityList.value = listOf()
            }
        }
    }

    fun onCityNameChanged(cityName : String){
        _cityName.value = cityName
    }

    fun findCityIndex(result: Result){
        //cityName.postValue(result.name)
        Log.d("Location Index","New city has been set to ${result.name}")
        locationProvider.index = locationProvider.location?.results?.indexOf(result) ?: 0

    }

}