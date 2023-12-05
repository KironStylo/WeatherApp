package com.kironstylo.weatherApp.domain

import android.util.Log
import com.kironstylo.weatherApp.data.model.Weather.Temperature
import com.kironstylo.weatherApp.data.model.Weather.WeatherData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GetTemperatureUseCase {

     fun getTemperature(weatherData: WeatherData): Temperature{
         // Get user's local date time
         val currentTime = LocalDateTime.now()
         // Apply format to current date
         val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH::00")
         // Date to compare with fetched dates from API to find the current time's temperature
         val realDate = currentTime.format(formatter)

         Log.d("Fecha formateada",realDate)

         // index to find temperature
         var temperatureIndex = 0

         // Iterate through the array until a match is found
         for (index in weatherData.hourly.dates.indices){
             if(weatherData.hourly.dates[index] == realDate){
                 temperatureIndex = index
             }
         }

         Log.d("El indice y el valor de la temperatura","$temperatureIndex es el indice y la temperatura ${weatherData.hourly.temperatures[temperatureIndex]}")

         return Temperature(weatherData.hourly.temperatures[temperatureIndex],LocalDateTime.now())






     }
}