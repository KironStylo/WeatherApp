package com.kironstylo.weatherApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kironstylo.weatherApp.core.screen.RoutingNames
import com.kironstylo.weatherApp.searchCityFeature.presentation.CityScreen
import com.kironstylo.weatherApp.searchCityFeature.presentation.GeoViewModel
import com.kironstylo.weatherApp.weatherFeature.presentation.WeatherScreen
import com.kironstylo.weatherApp.weatherFeature.presentation.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    private val geoViewModel: GeoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                containerColor = Color(0xFFACBDBA)
            ) { innerPadding ->
                //CityScreen(geoViewModel)
                App(Modifier.padding(innerPadding))
            }
        }
    }

    @Composable
    fun App(modifier: Modifier) {
        val navController = rememberNavController()
        val locationState by geoViewModel.locationState.collectAsState()
        val hourlyWeatherState by weatherViewModel.hourlyWeatherState.collectAsState()
        val dailyWeatherState by weatherViewModel.dailyWeatherState.collectAsState()
        val loadingState by weatherViewModel.loadingState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = RoutingNames.CityScreen
        ) {
            composable<RoutingNames.CityScreen>{
                CityScreen(
                    modifier = modifier,
                    locationUIState = locationState,
                    onEvent = geoViewModel::onEvent
                    ){
                    weatherViewModel.getWeather(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                    navController.navigate("Screen2")
                }
            }
            composable<RoutingNames.WeatherScreen>{
                WeatherScreen(
                    hourlyWeatherUIState = hourlyWeatherState,
                    dailyWeatherUIState = dailyWeatherState,
                    loadingState = loadingState
                )
            }
        }

    }

    @Preview
    @Composable
    fun WeatherScreenPreview() {
        WeatherScreen(weatherViewModel)
    }
}




