package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.dailyWeatherList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather

@Composable
fun DailyWeatherList(
    modifier : Modifier = Modifier,
    dailyWeatherList : List<DailyWeather>
){
    val modifierMinMax = modifier.width(50.dp)
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ){
            Text(
                text = "Next 7 days",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Max",
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Center,
                modifier = modifierMinMax
            )
            Text(
                text = "Min",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 16.sp),
                modifier = modifierMinMax
            )
        }
        LazyColumn {
            items(dailyWeatherList){ dailyWeather ->
                DailyWeatherItem(
                    modifierMinMax = modifierMinMax,
                    dailyWeather = dailyWeather
                )
            }
        }
    }
}

@Preview
@Composable
fun DailyWeatherListPreview(){
    DailyWeatherList(
        dailyWeatherList = listOf(
            DailyWeather()
        )
    )
}