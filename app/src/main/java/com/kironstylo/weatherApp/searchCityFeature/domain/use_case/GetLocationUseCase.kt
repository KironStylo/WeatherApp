package com.kironstylo.weatherApp.searchCityFeature.domain.use_case

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.searchCityFeature.data.repository.GeoRepository
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: GeoRepository
){
     operator fun invoke(cityName: String): Flow<Resource<List<Geolocation>>> {
        if(cityName.isBlank()){
            return flow{}
        }
        return repository.getLocation(cityName);
    }
}