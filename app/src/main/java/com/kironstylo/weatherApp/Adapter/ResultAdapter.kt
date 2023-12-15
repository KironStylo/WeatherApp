package com.kironstylo.weatherApp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kironstylo.weatherApp.R
import com.kironstylo.weatherApp.data.model.GeoLocation.Result

class ResultAdapter (private val resultList: List<Result>, private val onClickListener:(Result) -> Unit): RecyclerView.Adapter<ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ResultViewHolder(layoutInflater.inflate(R.layout.item_result,parent,false))
    }

    override fun getItemCount(): Int = resultList.size

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = resultList[position]
        holder.render(item, onClickListener)
    }
}