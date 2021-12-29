package com.osmanacikgoz.stationsapp.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.osmanacikgoz.stationsapp.R
import com.osmanacikgoz.stationsapp.databinding.ItemListStationBinding
import com.osmanacikgoz.stationsapp.model.Satellite

class StationHolder(
    private val binding: ItemListStationBinding,
    listener: StationAdapter.onItemClickListener
) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(station: Satellite) {
        station.let {
            val name = binding.tvStationName
            name.text = it.name
            val activeState = binding.tvActiveState
            activeState.text = it.active.toString()

            when (station.active) {
                true -> {
                    binding.ivActiveState.setImageResource(R.drawable.ic_baseline_brightness_green_24)
                }
                false -> {
                    binding.ivActiveState.setImageResource(R.drawable.ic_baseline_brightness_red_24)
                }
            }
        }
    }

    init {
        binding.listItemRoot.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}
