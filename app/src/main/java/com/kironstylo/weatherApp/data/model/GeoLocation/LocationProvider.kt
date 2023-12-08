package com.kironstylo.weatherApp.data.model.GeoLocation

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocationProvider @Inject constructor(){
    var location: LocationData? = null;
}