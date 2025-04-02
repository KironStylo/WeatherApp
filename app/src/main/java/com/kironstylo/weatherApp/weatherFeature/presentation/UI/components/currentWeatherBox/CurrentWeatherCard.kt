package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.currentWeatherBox

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.utils.Weather

@Composable
fun WeatherInfoCard(
    modifier : Modifier = Modifier,
    hourlyWeather: HourlyWeather,
    dailyWeather: DailyWeather
){
    ElevatedCard (
        modifier = modifier
            .fillMaxWidth()
            .height(420.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCDDDDD)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ){
        Column (
            modifier = Modifier.fillMaxHeight().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
        ){
            val weatherInfo = Weather.getWeatherNameByCode(hourlyWeather.weatherCode, hourlyWeather.date)
            Text(
                weatherInfo.description,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(
                    weatherInfo.icon
                ),
                modifier = Modifier.size(120.dp),
                contentDescription = weatherInfo.description,
            )
            DateTimeText(hourlyWeather.date)
            Text(
                "${hourlyWeather.temperature}ºC",
                style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            MinMaxTemperatureInfo(
                maxTemperature = dailyWeather.maxTemperature,
                minTemperature = dailyWeather.minTemperature
            )
            ExtraWeatherInfo(
                hourlyWeather.precipitation,
                hourlyWeather.humidity,
                hourlyWeather.windSpeed
            )

        }
    }
}

@Preview
@Composable
fun WeatherInfoCardPreview(){
    WeatherInfoCard(
        hourlyWeather = HourlyWeather(),
        dailyWeather = DailyWeather()
    )
}