package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.currentWeatherBox

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.R

@Composable
fun ExtraWeatherInfo(
    precipitationProb: Int,
    humidity: Int,
    windSpeed: Double
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ExtraWeatherCard(
            "Rain",
            "$precipitationProb%",
            R.drawable.umbrella
        )
        ExtraWeatherCard(
            "Humidity",
            "$humidity%",
            R.drawable.drop
        )
        ExtraWeatherCard(
            "Wind Speed",
            "$windSpeed km/h",
            R.drawable.windy
        )
    }
}

@Composable
fun ExtraWeatherCard(
    title: String,
    value: String,
    @DrawableRes id: Int
) {
    val textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
    val textStyle2 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
    Column(
        modifier = Modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id),
            contentDescription = title,
            modifier = Modifier.size(65.dp)
        )
        Text(value, style = textStyle2)
        Text(title, style = textStyle)
    }
}

@Preview
@Composable
fun ExtraWeatherCardPreview() {
    ExtraWeatherCard(
        "Precipitation",
        "2.0",
        R.drawable.umbrella
    )
}