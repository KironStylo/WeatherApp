package com.kironstylo.weatherApp.domain

import com.kironstylo.weatherApp.data.model.Timezone.DateTimeFormatted
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class GetFormattedDateUseCase @Inject constructor(
    private val getDateFormatter: GetDateFormatter
) {
    operator fun invoke(timezone: Timezone): DateTimeFormatted{
        val dateFormat = getDateFormatter(timezone.currentLocalTime, "dd/MM/yyyy")
        val timeFormat = getDateFormatter(timezone.currentLocalTime, "hh:mm a")
        return DateTimeFormatted(dateFormat, timeFormat)
    }
}