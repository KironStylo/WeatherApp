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

    private var searchJob: Job? = null

    fun onEvent(event: LocationEvent){
        when(event){
            is LocationEvent.SearchEvent -> {
                _locationState.value = locationState.value.copy(
                    selectedItem = null,
                    searchComplete = false
                )
                searchCity(event.query)
            }
            is LocationEvent.ChooseCity -> {
                _locationState.value = locationState.value.copy(
                    selectedItem = locationState.value.geolocationItems.find{it.id == event.geolocationId}
                )
            }
        }
    }
     private fun searchCity(cityName: String) {
         searchJob?.cancel()
         searchJob = viewModelScope.launch{
             delay(300)
             getLocationUseCase(cityName)
                 .onEach { result ->
                    when(result) {
                        is Resource.Loading -> {
                            _locationState.value = locationState.value.copy(
                                geolocationItems = result.data ?: emptyList(),
                                searchComplete = false,
                                selectedItem = null,
                                isLoading = true
                            )
                        }
                        is Resource.Success -> {
                            Log.i("Successful search", "${result.data}")
                            _locationState.value = locationState.value.copy(
                                geolocationItems = result.data ?: emptyList(),
                                searchComplete = true,
                                selectedItem = null,
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _locationState.value = locationState.value.copy(
                                geolocationItems = result.data ?: emptyList(),
                                searchComplete = true,
                                selectedItem = null,
                                isLoading = false
                            )
                        }
                    }
                 }.launchIn(this)
         }
    }

}