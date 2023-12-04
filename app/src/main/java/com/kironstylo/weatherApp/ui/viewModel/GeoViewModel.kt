package com.kironstylo.weatherApp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.domain.GetLocationUseCase
import kotlinx.coroutines.launch

class GeoViewModel: ViewModel() {

    val geoModel = MutableLiveData<Result>()
    val isLoading = MutableLiveData<Boolean>()

    val getLocationUseCase = GetLocationUseCase()

    fun searchCity(cityName: String) {

        isLoading.postValue(true)
        viewModelScope.launch {
            val result = getLocationUseCase(cityName)

            if(result!!.results.isNotEmpty()){
                geoModel.postValue(result.results[0])
                isLoading.postValue(false)
            }

        }
    }

}