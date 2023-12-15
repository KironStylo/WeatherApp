package com.kironstylo.weatherApp.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kironstylo.weatherApp.data.model.GeoLocation.Result
import com.kironstylo.weatherApp.databinding.ItemResultBinding

class ResultViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemResultBinding.bind(view)
    fun render(result: Result, onClickListener:(Result) -> Unit){
        binding.tvcityName.text = result.name
        binding.tvcityAlias.text = result.alias
        binding.tvcityCountry.text = result.country

        itemView.setOnClickListener {
            //Toast.makeText(view.context,"Longitud ${result.longitude}, Latitud: ${result.latitude}",Toast.LENGTH_SHORT).show()
            onClickListener(result)
        }
    }
}