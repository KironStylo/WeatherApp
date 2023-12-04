package com.kironstylo.weatherApp.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.kironstylo.weatherApp.databinding.ActivityMainBinding
import com.kironstylo.weatherApp.ui.viewModel.GeoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val geoViewModel: GeoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        geoViewModel.geoModel.observe(this, Observer {
            binding.temperatura.text =
                "Latitud: ${it.latitude}, Longitud: ${it.longitude.toString()}"
        })

        binding.enviar.setOnClickListener {
            if (binding.txtClima.text.toString().isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre de la ciudad", Toast.LENGTH_SHORT).show()
            } else {
                geoViewModel.searchCity(binding.txtClima.text.toString())
            }
        }

        geoViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it;
        })

    }
}
