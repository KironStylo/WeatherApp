package com.kironstylo.weatherApp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kironstylo.weatherApp.databinding.ActivityMainBinding
import com.kironstylo.weatherApp.searchCityFeature.presentation.CityScreen
import com.kironstylo.weatherApp.ui.view.weatherui.WeatherScreen
import com.kironstylo.weatherApp.searchCityFeature.presentation.GeoViewModel
import com.kironstylo.weatherApp.ui.viewModel.TimeViewModel
import com.kironstylo.weatherApp.ui.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val weatherViewModel: WeatherViewModel by viewModels()

    private val geoViewModel: GeoViewModel by viewModels()

    private val timeViewModel: TimeViewModel by viewModels()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface() {
                //CityScreen(geoViewModel)
                App()
            }
        }


//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        weatherViewModel.weatherModel.observe(this, Observer {
//            binding.temperatura.text = it.temperature.toString()+"ºC"
//            //binding.txtTiempo.text = "Hoy a las "+it.date.format(DateTimeFormatter.ofPattern("HH:00")).toString()
//            placeImages(it.temperature)
//        })
//
//        timeViewModel.timeModel.observe(this, Observer{
//            val dateTime = LocalDateTime.parse(it.currentLocalTime)
//            val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
//            Log.d("Main Activity","Time in hours and minutes  $formattedTime")
//            binding.txtTiempo.text = "Son las $formattedTime"
//            placeImages2(dateTime)
//            weatherViewModel.getTemperature()
//        })
//
//
//        // Creates a progress bar that starts a loading animation on the screen until
//        // the api call has finished fetching all data
//        weatherViewModel.isLoading.observe(this, Observer {
//            binding.progress.isVisible = it
//        })
//
//        // Determines if the list of cities should be shown on the screen or not depending on user interaction
//        geoViewModel.isVisibleList.observe(this,Observer{
//            binding.resultados.isVisible = it
//        })
//
//        // Determines if the card info should be shown on the screen or not depending on user interaction
//        geoViewModel.isVisibleCard.observe(this,Observer{
//            binding.cardInfo.isVisible = it
//        })
//
//        geoViewModel.geoModel.observe(this, Observer {
//            // It displays the list of cities so users can see the places
//            // that are named after the name typed by them in the search bar.
//            initRecyclerView(it)
//        })
//
//        geoViewModel.cityName.observe(this, Observer{
//            val message: String = "La ciudad de $it esta a"
//
//            // Setting the text to read the name of the city :
//            binding.txtCiudad.text = message
//
//            // The time of that place is obtained from the latitude and longitude data
//            timeViewModel.getTimeZone()
//        })
//
//
//        binding.enviar.setOnClickListener {
//            if (binding.txtClima.text.toString().isEmpty()) {
//                Toast.makeText(this, "Ingrese el nombre de la ciudad", Toast.LENGTH_SHORT).show()
//            } else {
//                geoViewModel.searchCity(binding.txtClima.text.toString())
//
//            }
//        }


    }

    //    private fun initRecyclerView(results: List<Result>){
//        binding.rvResultados.layoutManager = LinearLayoutManager(this)
//        // Loading the cities into the recycler view for user to choose the city of their interest
//        binding.rvResultados.adapter = ResultAdapter(results) { result -> onItemSelected(result) }
//    }
//
//    private fun onItemSelected(result: Result){
//
//        Log.d("Location Data","Datos del elemento seleccionado longitud es ${result.longitude} y la latitud es ${result.latitude}")
//
//        // The element retrieved is searched in view model
//        // so the location provider will know what index to access
//        geoViewModel.findCityIndex(result)
//
//    }
//
//    private fun placeImages(temperature: Double) {
//        val drawable = if(temperature > 10.0){
//            ContextCompat.getDrawable(this, R.drawable.ic_hot_temperature)
//        }else if(temperature > 0 && temperature < 10.0 || temperature < 0){
//            ContextCompat.getDrawable(this, R.drawable.ic_cold_temperature)
//        } else{
//            ContextCompat.getDrawable(this, R.drawable.ic_zero_temperature)
//        }
//        binding.imgClima.setImageDrawable(drawable)
//
//    }
//
//    private fun placeImages2(date: LocalDateTime){
//        val drawable2 = if(date.hour in 0..11){
//            ContextCompat.getDrawable(this,R.drawable.ic_daytime)
//        }else{
//            ContextCompat.getDrawable(this,R.drawable.ic_nightime)
//        }
//        Log.d("Main Acitivity - Place Images 2", "Hora: "+ date.hour)
//
//        binding.imgHora.setImageDrawable(drawable2)
//
//    }
    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "Screen1"
        ) {
            composable("Screen1") {
                CityScreen(geoViewModel){
                    navController.navigate("Screen2")
                    timeViewModel.getTimeZone()
                    weatherViewModel.getWeather()
                }
            }
            composable("Screen2"){
                WeatherScreen(timeViewModel, weatherViewModel)
            }
        }

    }

    @Preview
    @Composable
    fun WeatherScreenPreview() {
        WeatherScreen(timeViewModel, weatherViewModel)
    }
}




