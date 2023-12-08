package com.kironstylo.weatherApp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.domain.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeoViewModel @Inject constructor(
    private val getLocationUseCase : GetLocationUseCase
): ViewModel() {

    val geoModel = MutableLiveData<Result>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun searchCity(cityName: String) {

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