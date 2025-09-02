package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.dailyWeatherList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.events.WeatherEvent
import java.time.LocalDateTime

@Composable
fun DailyWeatherList(
    modifier : Modifier = Modifier,
    dailyWeatherList : List<DailyWeather>,
    isSelected: (DailyWeather) -> Boolean,
    onEvent:(WeatherEvent)->Unit
){
    val modifierMinMax = modifier.width(50.dp)
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ){
            Text(
                text = "Next 7 days",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(3.5f)
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Max",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Min",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.weight(1f)
            )
        }
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(dailyWeatherList){ dailyWeather ->
                DailyWeatherItem(
                    dailyWeather = dailyWeather,
                    isSelected = isSelected(dailyWeather),
                    onEvent = onEvent
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyWeatherListPreview(){
    DailyWeatherList(
        dailyWeatherList = listOf(
            DailyWeather(
                date = LocalDateTime.of(2025,4,4,14,0),
                maxTemperature = 20.0,
                minTemperature = 10.0
            ),
            DailyWeather()
        ),
        isSelected = {
            DailyWeather().date.toLocalDate() == it.date.toLocalDate()
        }
    ){
    }
}