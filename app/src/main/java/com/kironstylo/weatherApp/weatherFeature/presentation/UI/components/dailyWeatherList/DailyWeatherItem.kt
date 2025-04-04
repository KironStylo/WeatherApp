package com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.dailyWeatherList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kironstylo.weatherApp.R
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.DailyWeather
import java.time.format.TextStyle as DateTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.kironstylo.weatherApp.weatherFeature.domain.utils.Weather

import java.util.Locale

@Composable
fun DailyWeatherItem(
    modifier : Modifier = Modifier,
    dailyWeather: DailyWeather
){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start)
    ){
        val weatherInfo = Weather.getWeatherNameByCode(dailyWeather.weatherCode, dailyWeather.date)

        Text(
            dailyWeather.date.dayOfWeek.getDisplayName(DateTextStyle.SHORT, Locale.ENGLISH),
            modifier = Modifier.weight(0.5f),
            style = TextStyle(fontWeight = FontWeight.SemiBold),
        )

        Image(
            painter = painterResource(weatherInfo.icon),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .size(40.dp)
        )

        Text(
            text = weatherInfo.description,
            modifier = Modifier.weight(2f),
            overflow = TextOverflow.Ellipsis

        )
        Spacer(
            modifier.weight(1f)
        )
        Text(
            text = "${dailyWeather.maxTemperature} ºC",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${dailyWeather.minTemperature}ºC",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DailyWeatherItemPreview(){
    DailyWeatherItem(
        dailyWeather =  DailyWeather()
    )
}