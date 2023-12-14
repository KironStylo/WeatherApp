package com.kironstylo.weatherApp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kironstylo.weatherApp.data.model.GeoLocation.LocationProvider
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import com.kironstylo.weatherApp.domain.GetTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
    private val getTimeUseCase: GetTimeUseCase
): ViewModel(){

    val timeModel = MutableLiveData<Timezone>()


    fun getTimeZone(){
        viewModelScope.launch{
            val locationData = locationProvider.location
            Log.d("TimeViewModel","Los datos de localizacion son"+ (locationData?.results?.get(0)?.name))
            if(locationData?.results?.isNotEmpty() == true){
                val location = locationData.results[0]

                val result = getTimeUseCase(location)

                if(result != null){
                    timeModel.postValue(result)
                }


            }
        }
    }

}