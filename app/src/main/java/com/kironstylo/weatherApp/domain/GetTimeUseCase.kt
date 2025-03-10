package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.searchCityFeature.data.remote.dto.GeolocationDto
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import com.kironstylo.weatherApp.data.network.Timezone.TimeRepository
import javax.inject.Inject

class GetTimeUseCase @Inject constructor(
    private val repository: TimeRepository
) {
    suspend operator fun invoke(locationData: GeolocationDto): Timezone? = repository.getTime(locationData)
}