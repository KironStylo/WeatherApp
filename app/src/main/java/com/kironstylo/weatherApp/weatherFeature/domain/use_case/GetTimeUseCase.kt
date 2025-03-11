package com.kironstylo.weatherApp.weatherFeature.domain.use_case

import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.searchCityFeature.domain.model.Geolocation
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto.TimezoneDto
import com.kironstylo.weatherApp.weatherFeature.data.repository.TimeRepository
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import javax.inject.Inject

class GetTimeUseCase @Inject constructor(
    private val repository: TimeRepository
) {
    suspend operator fun invoke(locationData: Geolocation): Timezone? = repository.getTime(locationData)
}