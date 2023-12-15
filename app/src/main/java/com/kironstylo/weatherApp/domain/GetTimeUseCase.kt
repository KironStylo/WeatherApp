package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import com.kironstylo.weatherApp.data.network.Timezone.TimeRepository
import javax.inject.Inject

class GetTimeUseCase @Inject constructor(
    private val repository: TimeRepository
) {
    suspend operator fun invoke(locationData: Result): Timezone? = repository.getTime(locationData)
}