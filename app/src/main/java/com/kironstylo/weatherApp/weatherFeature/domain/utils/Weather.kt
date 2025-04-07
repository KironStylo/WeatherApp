package com.kironstylo.weatherApp.weatherFeature.domain.utils

import com.kironstylo.weatherApp.R
import java.time.LocalDateTime
import java.time.LocalTime

object Weather {

    data class WeatherCode(
        val description: String,
        val icon: Int = R.drawable.sunny
    )

    private val weatherCodes: Map<Int, Map<String, WeatherCode>> = mapOf(
        0 to mapOf(
            "day" to WeatherCode("Sunny", R.drawable.sunny),
            "night" to WeatherCode("Clear", R.drawable.night)
        ),
        1 to mapOf(
            "day" to WeatherCode("Mainly Sunny", R.drawable.sunny),
            "night" to WeatherCode("Mainly Clear", R.drawable.night)
        ),
        2 to mapOf(
            "day" to WeatherCode("Partly cloudy", R.drawable.cloudy_sun),
            "night" to WeatherCode("Partly cloudy", R.drawable.cloudy_moon)
        ),
        3 to mapOf(
            "day" to WeatherCode("Cloudy", R.drawable.cloudy_sun),
            "night" to WeatherCode("Cloudy", R.drawable.cloudy_moon)
        ),
        45 to mapOf(
            "day" to WeatherCode("Foggy", R.drawable.foggy),
            "night" to WeatherCode("Foggy", R.drawable.foggy)
        ),
        48 to mapOf(
            "day" to WeatherCode("Dense fog", R.drawable.foggy),
            "night" to WeatherCode("Dense fog", R.drawable.foggy)
        ),
        51 to mapOf(
            "day" to WeatherCode("Light drizzle", R.drawable.drizzle_sunny),
            "night" to WeatherCode("Light drizzle", R.drawable.drizzle_night)
        ),
        53 to mapOf(
            "day" to WeatherCode("Drizzle", R.drawable.drizzle_sunny),
            "night" to WeatherCode("Drizzle", R.drawable.drizzle_night)
        ),
        55 to mapOf(
            "day" to WeatherCode("Heavy drizzle", R.drawable.drizzle_sunny),
            "night" to WeatherCode("Heavy drizzle", R.drawable.drizzle_night)
        ),
        56 to mapOf(
            "day" to WeatherCode("Light Freezing drizzle", R.drawable.drizzle_sunny),
            "night" to WeatherCode("Light Freezing drizzle", R.drawable.drizzle_night)
        ),
        57 to mapOf(
            "day" to WeatherCode("Freezing drizzle", R.drawable.drizzle_sunny),
            "night" to WeatherCode("Freezing drizzle", R.drawable.drizzle_night)
        ),
        61 to mapOf(
            "day" to WeatherCode("Light rain", R.drawable.rainy),
            "night" to WeatherCode("Light rain", R.drawable.rainy)
        ),
        63 to mapOf(
            "day" to WeatherCode("Rain", R.drawable.rainy),
            "night" to WeatherCode("Rain", R.drawable.rainy)
        ),
        65 to mapOf(
            "day" to WeatherCode("Heavy rain", R.drawable.rainy),
            "night" to WeatherCode("Heavy rain", R.drawable.rainy)
        ),
        66 to mapOf(
            "day" to WeatherCode("Light Freezing rain", R.drawable.rainy),
            "night" to WeatherCode("Light Freezing rain", R.drawable.rainy)
        ),
        67 to mapOf(
            "day" to WeatherCode("Freezing rain", R.drawable.rainy),
            "night" to WeatherCode("Freezing rain", R.drawable.rainy)
        ),
        71 to mapOf(
            "day" to WeatherCode("Light snow", R.drawable.snowy_day),
            "night" to WeatherCode("Light snow", R.drawable.snowy_night)
        ),
        73 to mapOf(
            "day" to WeatherCode("Snow", R.drawable.snowy_day),
            "night" to WeatherCode("Snow", R.drawable.snowy_night)
        ),
        75 to mapOf(
            "day" to WeatherCode("Heavy snow", R.drawable.snowy_day),
            "night" to WeatherCode("Heavy snow", R.drawable.snowy_night)
        ),
        80 to mapOf(
            "day" to WeatherCode("Light showers", R.drawable.showers),
            "night" to WeatherCode("Light showers", R.drawable.showers)
        ),
        81 to mapOf(
            "day" to WeatherCode("Showers", R.drawable.showers),
            "night" to WeatherCode("Showers", R.drawable.showers)
        ),
        82 to mapOf(
            "day" to WeatherCode("Heavy showers", R.drawable.showers),
            "night" to WeatherCode("Heavy showers", R.drawable.showers)
        ),
        85 to mapOf(
            "day" to WeatherCode("Light Snow showers", R.drawable.snowy_showers),
            "night" to WeatherCode("Light Snow showers", R.drawable.snowy_showers)
        ),
        86 to mapOf(
            "day" to WeatherCode("Snow showers", R.drawable.snowy_showers),
            "night" to WeatherCode("Snow showers", R.drawable.snowy_showers)
        ),
        95 to mapOf(
            "day" to WeatherCode("Thunderstorm", R.drawable.stormy_day),
            "night" to WeatherCode("Thunderstorm", R.drawable.stormy_night)
        ),
        96 to mapOf(
            "day" to WeatherCode("Light thunderstorm with hail", R.drawable.stormy_hail),
            "night" to WeatherCode("Light thunderstorm with hail", R.drawable.stormy_hail)
        ),
        99 to mapOf(
            "day" to WeatherCode("Thunderstorm with hail", R.drawable.stormy_hail),
            "night" to WeatherCode("Thunderstorm with hail", R.drawable.stormy_hail)
        )
    )

    private fun isDaytime(hour: Int): Boolean {
        return hour in 6..17
    }

    private fun isDaytime(localDateTime: LocalDateTime): Boolean{
        val time = localDateTime.toLocalTime()
        return !time.isBefore(LocalTime.of(6, 0)) && time.isBefore(LocalTime.of(18,0))
    }

    fun getWeatherNameByCode(code: Int, date: LocalDateTime): WeatherCode{
        val time = if(isDaytime(date)) "day" else "night"
        return weatherCodes[code]?.get(time) ?: WeatherCode("Unknown Weather")
    }

    fun getWeatherNameByCode(code: Int, hour: Int): WeatherCode {
        val time = if (isDaytime(hour)) "day" else "night"
        return weatherCodes[code]?.get(time) ?: WeatherCode("Unknown Weather")
    }

}