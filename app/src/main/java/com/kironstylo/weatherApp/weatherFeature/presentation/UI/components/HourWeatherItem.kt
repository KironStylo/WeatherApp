package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
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
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.utils.DateFormatter
import com.kironstylo.weatherApp.weatherFeature.domain.utils.Weather

@Composable
fun HourWeatherItem(
    modifier : Modifier = Modifier,
    isSelected: Boolean = false,
    hourlyWeather: HourlyWeather
){
    OutlinedCard (
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCDDDDD),
        ),
        modifier = modifier
            .width(85.dp)
            .height(120.dp)
        ,
        border = BorderStroke(
            width = 1.dp,
            color = if(isSelected) Color(0xFF8E4CE3) else Color.Transparent
        )
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterVertically
            )
        ){
            val weatherInfo = Weather.getWeatherNameByCode(
                code = hourlyWeather.weatherCode,
                date = hourlyWeather.date
            )
            Text(
                DateFormatter.formatDate(hourlyWeather.date, "h a"),
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
            )
            Image(
                painterResource(
                    id = weatherInfo.icon
                ),
                contentDescription = weatherInfo.description,
                modifier = Modifier.size(50.dp)
            )
            Text(
                "${hourlyWeather.temperature} ºC",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Preview
@Composable
fun HourWeatherItemPreview(){
    HourWeatherItem(
        hourlyWeather = HourlyWeather(
        ),
        isSelected = true
    )
}
