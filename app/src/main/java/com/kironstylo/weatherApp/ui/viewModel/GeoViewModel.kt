package com.kironstylo.weatherApp.ui.viewModel

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
    val cityName = MutableLiveData<String>()

     fun searchCity(cityName: String) {

        isVisibleCard.postValue(false)
        viewModelScope.launch {
            val result = getLocationUseCase(cityName)

            if(result!!.results.isNotEmpty()){
                geoModel.postValue(result.results)
                isVisibleList.postValue(true)
            }

        }
    }

    fun findCityIndex(result: Result){
        isVisibleList.postValue(false)
        isVisibleCard.postValue(true)
        cityName.postValue(result.name)
        locationProvider.index = locationProvider.location?.results?.indexOf(result) ?: 0

    }

}