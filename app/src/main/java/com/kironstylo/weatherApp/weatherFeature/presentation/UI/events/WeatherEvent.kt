package com.kironstylo.weatherApp.weatherFeature.presentation.ui.events

import java.time.LocalDate
import java.time.LocalDateTime

sealed class WeatherEvent {
    data class ChangeTimeEvent(val localTime: LocalDateTime) : WeatherEvent()
    data class ChangeDateEvent(val localDate: LocalDate): WeatherEvent()
}