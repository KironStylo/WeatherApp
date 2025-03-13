package com.kironstylo.weatherApp.searchCityFeature.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.searchCityFeature.domain.use_case.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeoViewModel @Inject constructor(
    private val getLocationUseCase : GetLocationUseCase
): ViewModel() {

    private val _locationState = MutableStateFlow(LocationUIState())
    val locationState: StateFlow<LocationUIState> = _locationState.asStateFlow()

    private val _cityName = MutableStateFlow("")
    val cityName : StateFlow<String> = _cityName

    private var searchJob: Job? = null

    fun onEvent(event: LocationEvent){
        when(event){
            is LocationEvent.SearchEvent -> {
                searchCity(event.query)
            }
            is LocationEvent.ChooseCity -> TODO()
        }
    }
     fun searchCity(cityName: String) {
         _cityName.value = cityName
         searchJob?.cancel()
         searchJob = viewModelScope.launch{
             delay(300)
             getLocationUseCase(cityName)
                 .onEach { result ->
                    when(result) {
                        is Resource.Loading -> {
                            _locationState.value = locationState.value.copy(
                                geolocationItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            _locationState.value = locationState.value.copy(
                                geolocationItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _locationState.value = locationState.value.copy(
                                geolocationItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                 }.launchIn(this)
         }
    }

    fun onCityNameChanged(cityName : String){
        _cityName.value = cityName
    }
}