package com.kironstylo.weatherApp.data.model.Timezone

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeProvider @Inject constructor(){
    var  timezone: Timezone? = null;
}