package com.kironstylo.weatherApp.weatherFeature.presentation.ui.events

import java.time.LocalDateTime

sealed class WeatherEvent {
    data class ChangeTimeEvent(val localTime: LocalDateTime) : WeatherEvent()
}