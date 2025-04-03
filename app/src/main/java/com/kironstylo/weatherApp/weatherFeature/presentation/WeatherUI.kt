package com.kironstylo.weatherApp.weatherFeature.presentation

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kironstylo.weatherApp.R
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.HourlyWeather
import com.kironstylo.weatherApp.weatherFeature.domain.utils.Weather
import com.kironstylo.weatherApp.weatherFeature.domain.model.weather.WeatherInfo
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.states.DailyWeatherUIState
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.states.HourlyWeatherUIState
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.currentWeatherBox.*
import com.kironstylo.weatherApp.weatherFeature.presentation.ui.components.hourlyWeatherList.*
import java.time.LocalDateTime


@Composable
fun WeatherScreen(
    modifier : Modifier = Modifier,
    hourlyWeatherUIState: HourlyWeatherUIState,
    dailyWeatherUIState: DailyWeatherUIState,
    loadingState: Boolean
){
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(space = 4.dp, alignment =  if(loadingState) Alignment.CenterVertically else Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(loadingState){
            CircularProgressIndicator(
                modifier = Modifier.size(200.dp),
                color = Color(0xFFB58FE7),
                trackColor = Color(0xFFA599B5)
            )
        }
        else{
            WeatherInfoCard(
                modifier = Modifier.weight(0.5f),
                hourlyWeather = hourlyWeatherUIState.selectedHourlyWeather,
                dailyWeather = dailyWeatherUIState.selectedDailyWeather
            )
            HourlyWeatherList(
                modifier = Modifier.weight(0.25f),
                hourlyWeatherList = hourlyWeatherUIState.hourlyWeatherList,
                isSelected = {
                    it.date.hour == hourlyWeatherUIState.selectedHourlyWeather.date.hour
                }
            ) {
                it.date.toLocalDate() == dailyWeatherUIState.selectedDailyWeather.date.toLocalDate()
            }
            Text("Hi", modifier = Modifier.weight(0.25f))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WeatherScreenPreview(){
    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFFB5B9B9)
    ){

        innerPadding ->
        WeatherScreen(
            modifier = Modifier.padding(innerPadding),
            hourlyWeatherUIState = HourlyWeatherUIState(
                hourlyWeatherList = listOf(
                    HourlyWeather(),
                    HourlyWeather(
                        date = LocalDateTime.of(2025,3,31,20,0)
                    )
                ),
            ),
            dailyWeatherUIState = DailyWeatherUIState(),
            loadingState = false
        )
    }

}