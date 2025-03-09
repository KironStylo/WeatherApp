package com.kironstylo.weatherApp.data.searchCityFeature.domain.use_case

import com.kironstylo.weatherApp.data.searchCityFeature.data.repository.GeoRepository
import com.kironstylo.weatherApp.data.searchCityFeature.data.remote.dto.LocationDataDto
import com.kironstylo.weatherApp.data.searchCityFeature.domain.model.Geolocation
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: GeoRepository
){

    suspend operator fun invoke(cityName: String): List<Geolocation>{
        if(cityName.isBlank()){
            return emptyList()
        }
        return repository.getLocation(cityName);
    }
}