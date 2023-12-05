package com.kironstylo.weatherApp.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.kironstylo.weatherApp.databinding.ActivityMainBinding
import com.kironstylo.weatherApp.ui.viewModel.GeoViewModel
import com.kironstylo.weatherApp.ui.viewModel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val geoViewModel: GeoViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.weatherModel.observe(this, Observer {
            binding.temperatura.text = it.temperature.toString()
        })

        binding.enviar.setOnClickListener {
            if (binding.txtClima.text.toString().isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre de la ciudad", Toast.LENGTH_SHORT).show()
            } else {
                weatherViewModel.getTemperature(binding.txtClima.text.toString())
            }
        }


    }
}
