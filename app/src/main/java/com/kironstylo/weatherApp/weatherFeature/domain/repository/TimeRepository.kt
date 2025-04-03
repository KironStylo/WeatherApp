package com.kironstylo.weatherApp.weatherFeature.domain.repository

import com.kironstylo.weatherApp.core.util.Resource
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import kotlinx.coroutines.flow.Flow

interface TimeRepository {
    fun getTime(latitude:Double, longitude: Double): Flow<Resource<Timezone>>
}