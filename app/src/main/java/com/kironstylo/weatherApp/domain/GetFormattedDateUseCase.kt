package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.model.Timezone.DateTimeFormatted
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class GetFormattedDateUseCase @Inject constructor() {

    operator fun invoke(timezone: Timezone): DateTimeFormatted{
        val localDateTime = LocalDateTime.parse(timezone.currentLocalTime)
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        return DateTimeFormatted(dateFormatter.format(localDateTime), timeFormatter.format(localDateTime))
    }
}