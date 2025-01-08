package com.kironstylo.weatherApp.ui.view.weatherui

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.R

@Composable
fun WeatherScreen() {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color(0xFFACBDBA)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherInfoCard()
    }
}

@Composable
fun WeatherInfoCard() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
            .padding(start = 31.dp, end = 31.dp, top = 21.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCDDDDD)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(40.dp),
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                "Partly Cloudy",
                Modifier.padding(top = 18.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.cloudy),
                modifier = Modifier.size(120.dp),
                contentDescription = ""
            )
            DateTimeText()
            Text(
                "14ºC",
                style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            MinMaxTemperatureInfo()
            ExtraWeatherInfo()
        }
    }
}

@Composable
fun ExtraWeatherInfo() {
    Row (modifier = Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)){
        ExtraWeatherCard(
            "Rain",
            {
                Image(
                    painterResource(id = R.drawable.umbrella),
                    contentDescription = "RainImage",
                    Modifier.size(65.dp)
                )
            },
            "20%"
        )
        ExtraWeatherCard(
            "Humidity",
            {
                Image(
                    painterResource(id = R.drawable.drop),
                    contentDescription = "HumidityImage",
                    Modifier.size(65.dp)
                )
            },
            "30%"
        )
        ExtraWeatherCard(
            "Wind Speed",
            {
                Image(
                    painterResource(id = R.drawable.windy),
                    contentDescription = "WindImage",
                    Modifier.size(65.dp)
                )
            },
            "12 km/h"
        )
    }
}

@Composable
fun ExtraWeatherCard(title: String, image: @Composable () -> Unit, value: String) {
    val textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal)
    val textStyle2 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        image()
        Text(value, style = textStyle2)
        Text(title, style = textStyle)
    }
}

@Composable
fun DateTimeText() {
    val textStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )
    Row(modifier = Modifier.height(16.dp), horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        Text("17/12/2024", style = textStyle)
        VerticalDivider(
            thickness = 1.dp,
            color = Color(0xFF051014)
        )
        Text("7:21 PM", style = textStyle)

    }
}

@Composable
fun MinMaxTemperatureInfo() {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        MinTempInfo()
        MaxTempInfo()
    }
}

@Composable
fun MinTempInfo() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)) {
                append("Min:")
            }
            withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Light)) {
                append("8ºC")
            }
        }
    )
}

@Composable
fun MaxTempInfo() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)) {
                append("Max:")
            }
            withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Light)) {
                append("20ºC")
            }
        }
    )
}


@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}