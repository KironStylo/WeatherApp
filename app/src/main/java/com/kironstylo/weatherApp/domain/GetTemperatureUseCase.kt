package com.kironstylo.weatherApp.domain

import android.icu.util.TimeZone
import android.util.Log
import com.kironstylo.weatherApp.data.model.Timezone.Timezone
import com.kironstylo.weatherApp.data.model.Weather.Temperature
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetTemperatureUseCase @Inject constructor() {

     fun getTemperature(weatherData: WeatherData, timeZone: Timezone): Temperature{
         // Get user's local date time
         val currentTime = LocalDateTime.parse(timeZone.currentLocalTime)
         // Apply format to current date
         val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:00")
         // Date to compare with fetched dates from API to find the current time's temperature
         val realDate = currentTime.format(formatter)


         Log.d("Fecha formateada",realDate)

         // index to find temperature
         var temperatureIndex = 0

         // Iterate through the array until a match is found
         for (index in weatherData.hourly.dates.indices){
             Log.d("Temperatura $index", weatherData.hourly.dates[index])
             if(weatherData.hourly.dates[index] == realDate.toString()){
                 temperatureIndex = index
             }
         }

         Log.d("El indice y el valor de la temperatura","$temperatureIndex es el indice y la temperatura ${weatherData.hourly.temperatures[temperatureIndex]}")



         return Temperature(weatherData.hourly.temperatures[temperatureIndex],LocalDateTime.now())






     }
}