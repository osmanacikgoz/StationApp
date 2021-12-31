package com.osmanacikgoz.stationsapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osmanacikgoz.stationsapp.R
import com.osmanacikgoz.stationsapp.base.BaseViewHolder
import com.osmanacikgoz.stationsapp.base.bindings
import com.osmanacikgoz.stationsapp.databinding.ItemListStationBinding
import com.osmanacikgoz.stationsapp.model.Satellite

class StationHolder(
    parent: ViewGroup
) : BaseViewHolder<Satellite>(
    LayoutInflater.from(parent.context)
        .inflate(
            R.layout.item_list_station,
            parent,
            false
        )
) {

    private val binding by bindings<ItemListStationBinding>(itemView)

    override fun bind(
        item: Satellite,
        position: Int,
        setOnClickListener: (model: Satellite, position: Int) -> Unit
    ) {
        with(binding) {
            tvStationName.text = item.name
            tvActiveState.text = item.active.toString()

            when (item.active) {
                true -> {
                    ivActiveState.setImageResource(R.drawable.ic_baseline_brightness_green_24)
                }
                false -> {
                    ivActiveState.setImageResource(R.drawable.ic_baseline_brightness_red_24)
                }
            }
        }
        itemView.setOnClickListener {
            setOnClickListener(item, position)
        }
    }
}
