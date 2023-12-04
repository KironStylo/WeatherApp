package com.kironstylo.weatherApp.data.network.Geolocation

import com.kironstylo.weatherApp.data.model.GeoLocation.LocationData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface GeoApiClient {
    @GET
    suspend fun getLatitudeAndLongitude(@Url url:String): Response<LocationData>
}