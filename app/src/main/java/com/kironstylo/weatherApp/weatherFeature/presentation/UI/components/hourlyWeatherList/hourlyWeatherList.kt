package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.hourlyWeatherList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather
import java.time.LocalDateTime

@Composable
fun HourlyWeatherList(
    hourlyWeatherList: List<HourlyWeather>,
    filterList: (HourlyWeather) -> Boolean
){
    Column (
        modifier = Modifier
            .width(390.dp)
            .height(200.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Today", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        LazyRow(
            modifier = Modifier
                .width(390.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                hourlyWeatherList.filter{
                    filterList(it)
                }
            ){
                HourWeatherItem(
                    hourlyWeather = it
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Selected time: ", style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp))
            Box(modifier = Modifier.size(16.dp).border(1.dp, Color.Black, RoundedCornerShape(5.dp)).clip(RoundedCornerShape(5.dp)).background(Color(0xFF8E4CE3)))
        }
    }
}


@Preview
@Composable
fun HourlyWeatherListPreview(){
    HourlyWeatherList(
        listOf(
            HourlyWeather(),
            HourlyWeather(
                date = LocalDateTime.of(2025,3,31,20,0)
            )
        )
    ) {
        it.date.toLocalDate() == LocalDateTime.now().toLocalDate()
    }
}