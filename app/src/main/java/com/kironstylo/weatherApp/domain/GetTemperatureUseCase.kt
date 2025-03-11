package com.kironstylo.weatherApp.domain

import android.util.Log
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.dto.TimezoneDto
import com.kironstylo.weatherApp.data.model.Weather.Temperature
import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.dto.WeatherDataDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetTemperatureUseCase @Inject constructor() {

     fun getTemperature(weatherDataDto: WeatherDataDto, timeZone: TimezoneDto): Temperature{
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
         for (index in weatherDataDto.hourlyDto.dates.indices){
             Log.d("Temperatura $index", weatherDataDto.hourlyDto.dates[index])
             if(weatherDataDto.hourlyDto.dates[index] == realDate.toString()){
                 temperatureIndex = index
             }
         }

         Log.d("El indice y el valor de la temperatura","$temperatureIndex es el indice y la temperatura ${weatherDataDto.hourlyDto.temperatures[temperatureIndex]}")



         return Temperature(weatherDataDto.hourlyDto.temperatures[temperatureIndex],LocalDateTime.now())






     }
}