package com.kironstylo.weatherApp.data.model.Timezone

import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto.TimezoneDto
import com.kironstylo.weatherApp.weatherFeature.domain.model.timezone.Timezone
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeProvider @Inject constructor(){
    var timezoneDto: TimezoneDto? = null
    var timezone: Timezone? = null
}