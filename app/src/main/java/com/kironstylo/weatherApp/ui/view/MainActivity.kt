package com.kironstylo.weatherApp.ui.view

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.kironstylo.weatherApp.R
import com.kironstylo.weatherApp.data.model.Weather.Temperature
import com.kironstylo.weatherApp.databinding.ActivityMainBinding
import com.kironstylo.weatherApp.ui.viewModel.GeoViewModel
import com.kironstylo.weatherApp.ui.viewModel.TimeViewModel
import com.kironstylo.weatherApp.ui.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val weatherViewModel: WeatherViewModel by viewModels()

    private val geoViewModel: GeoViewModel by viewModels()

    private val timeViewModel: TimeViewModel by viewModels()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.weatherModel.observe(this, Observer {
            binding.temperatura.text = it.temperature.toString()+"ºC"
            //binding.txtTiempo.text = "Hoy a las "+it.date.format(DateTimeFormatter.ofPattern("HH:00")).toString()
            placeImages(it.temperature)
        })

        timeViewModel.timeModel.observe(this, Observer{
            val dateTime = LocalDateTime.parse(it.currentLocalTime)
            val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            Log.d("Main Activity","Time in hours and minutes  $formattedTime")
            binding.txtTiempo.text = "Son las $formattedTime"
            placeImages2(dateTime)
            weatherViewModel.getTemperature()
        })


        // Creates a progress bar that starts a loading animation on the screen until
        // the api call has finished fetching all data
        weatherViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        geoViewModel.geoModel.observe(this, Observer {
            binding.txtCiudad.text = "La ciudad de ${it.name} esta:"
            timeViewModel.getTimeZone()
        })


        binding.enviar.setOnClickListener {
            if (binding.txtClima.text.toString().isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre de la ciudad", Toast.LENGTH_SHORT).show()
            } else {
                geoViewModel.searchCity(binding.txtClima.text.toString())

            }
        }


    }

    private fun placeImages(temperature: Double) {
        var drawable = if(temperature > 10.0){
            ContextCompat.getDrawable(this, R.drawable.ic_hot_temperature)
        }else if(temperature > 0 && temperature < 10.0 || temperature < 0){
            ContextCompat.getDrawable(this, R.drawable.ic_cold_temperature)
        } else{
            ContextCompat.getDrawable(this, R.drawable.ic_zero_temperature)
        }
        binding.imgClima.setImageDrawable(drawable)

    }

    private fun placeImages2(date: LocalDateTime){
        var drawable2 = if(date.hour in 0..11){
            ContextCompat.getDrawable(this,R.drawable.ic_daytime)
        }else{
            ContextCompat.getDrawable(this,R.drawable.ic_nightime)
        }
        Log.d("Main Acitivity - Place Images 2", "Hora: "+ date.hour)

        binding.imgHora.setImageDrawable(drawable2)

    }
}
