package com.kironstylo.weatherApp.ui.view.weatherui

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.kironstylo.weatherApp.data.model.Timezone.DateTimeFormatted
import com.kironstylo.weatherApp.data.model.Weather.Weather
import com.kironstylo.weatherApp.data.model.Weather.WeatherInfo
import com.kironstylo.weatherApp.ui.viewModel.TimeViewModel
import com.kironstylo.weatherApp.ui.viewModel.WeatherViewModel

@Composable
fun WeatherScreen(timeViewModel: TimeViewModel, weatherViewModel: WeatherViewModel) {
    val isCardLoading by weatherViewModel.weatherCardLoading.observeAsState(initial = true)
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color(0xFFACBDBA)),
        verticalArrangement = if (isCardLoading) Arrangement.Center else Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isCardLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(200.dp),
                color = Color(0xFFB58FE7),
                trackColor = Color(0xFFA599B5)

            )
        } else {
            WeatherInfoCard(timeViewModel, weatherViewModel)
            HourlyWeatherBox(weatherViewModel)
        }
    }
}

@Composable
fun WeatherInfoCard(timeViewModel: TimeViewModel, weatherViewModel: WeatherViewModel) {
    val timeZone: DateTimeFormatted by timeViewModel.timeZone.observeAsState(initial = DateTimeFormatted())
    val weatherInfo: WeatherInfo by weatherViewModel.weatherInfo.observeAsState(initial = WeatherInfo())
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
                Weather.getWeatherNameByCode(
                    weatherInfo.weatherCode,
                    weatherInfo.weatherTime24
                ).description,
                Modifier.padding(top = 18.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(
                    id = Weather.getWeatherNameByCode(
                        weatherInfo.weatherCode,
                        weatherInfo.weatherTime24
                    ).icon
                ),
                modifier = Modifier.size(120.dp),
                contentDescription = ""
            )
            DateTimeText(timeZone)
            Text(
                "${weatherInfo.weatherTemperature}ºC",
                style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            MinMaxTemperatureInfo(
                weatherInfo.weatherMaxTemperature,
                weatherInfo.weatherMinTemperature
            )
            ExtraWeatherInfo(
                weatherInfo.weatherPrecipitaion,
                weatherInfo.weatherHumidity,
                weatherInfo.weatherWindspeed
            )

        }
    }

}


@Composable
fun ExtraWeatherInfo(precipitationProb: Int, humidity: Int, windspeed: Double) {
    Row(modifier = Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        ExtraWeatherCard(
            "Rain",
            {
                Image(
                    painterResource(id = R.drawable.umbrella),
                    contentDescription = "RainImage",
                    Modifier.size(65.dp)
                )
            },
            "$precipitationProb%"
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
            "$humidity%"
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
            "$windspeed km/h"
        )
    }
}

@Composable
fun ExtraWeatherCard(title: String, image: @Composable () -> Unit, value: String) {
    val textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
    val textStyle2 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        image()
        Text(value, style = textStyle2)
        Text(title, style = textStyle)
    }
}

@Composable
fun DateTimeText(timeZone: DateTimeFormatted) {
    val textStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    )
    Row(modifier = Modifier.height(18.dp), horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(timeZone.date ?: "", style = textStyle)
        VerticalDivider(
            thickness = 1.dp,
            color = Color(0xFF051014)
        )
        Text(timeZone.time ?: "", style = textStyle)

    }
}

@Composable
fun MinMaxTemperatureInfo(maxTemp: Double, minTemp: Double) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        MinTempInfo(minTemp)
        MaxTempInfo(maxTemp)
    }
}

@Composable
fun MinTempInfo(minTem: Double) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)) {
                append("Min:")
            }
            withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Light)) {
                append("${minTem}ºC")
            }
        }
    )
}

@Composable
fun MaxTempInfo(maxTemp: Double) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp)) {
                append("Max:")
            }
            withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Light)) {
                append("${maxTemp}ºC")
            }
        }
    )
}


@Composable
fun HourlyWeatherBox(weatherViewModel: WeatherViewModel) {
    val hourWeatherInfo: List<WeatherInfo> by weatherViewModel.hourWeatherInfo.observeAsState(
        initial = listOf()
    )
    Column(
        modifier = Modifier
            .width(390.dp)
            .height(160.dp)
            .padding(horizontal = 11.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("Today", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        HourlyWeatherCardList(hourWeatherInfo)
    }
}

@Composable
fun HourlyWeatherCardList(hourWeatherInfo: List<WeatherInfo>) {
    LazyRow(
        modifier = Modifier
            .width(390.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(hourWeatherInfo) { hourWeather ->
            HourlyWeatherCard(
                weatherInfo = hourWeather
            )
        }
    }
}

@Composable
fun HourlyWeatherCard(weatherInfo: WeatherInfo) {
    ElevatedCard(
        modifier = Modifier
            .width(85.dp)
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCDDDDD)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            Text(
                weatherInfo.weatherTime12,
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
            )
            Image(
                painterResource(
                    id = Weather.getWeatherNameByCode(
                        weatherInfo.weatherCode,
                        weatherInfo.weatherTime24
                    ).icon
                ),
                contentDescription = Weather.getWeatherNameByCode(
                    weatherInfo.weatherCode,
                    weatherInfo.weatherTime24
                ).description,
                modifier = Modifier.size(50.dp)
            )
            Text(
                "${weatherInfo.weatherTemperature}",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
            )
        }
    }
}