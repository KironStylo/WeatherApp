package com.kironstylo.weatherApp.core.screen

import kotlinx.serialization.Serializable


sealed class RoutingNames{
    @Serializable
    object WeatherScreen

    @Serializable
    object CityScreen
}

