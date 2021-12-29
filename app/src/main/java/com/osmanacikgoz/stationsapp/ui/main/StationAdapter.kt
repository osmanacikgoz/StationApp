package com.osmanacikgoz.stationsapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osmanacikgoz.stationsapp.databinding.ItemListStationBinding
import com.osmanacikgoz.stationsapp.model.Satellite

class StationAdapter(
    private var satellite: ArrayList<Satellite>) :
    RecyclerView.Adapter<StationHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener:onItemClickListener) {
        mListener  =listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationHolder {
        val binding: ItemListStationBinding =
            ItemListStationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StationHolder(binding,mListener)
    }

    override fun onBindViewHolder(holder: StationHolder, position: Int) {
        holder.bind(satellite[position])
    }

    override fun getItemCount(): Int {
        return satellite.size
    }


}