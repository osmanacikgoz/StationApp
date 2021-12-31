package com.osmanacikgoz.stationsapp.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osmanacikgoz.stationsapp.base.BaseViewHolder
import com.osmanacikgoz.stationsapp.model.Satellite

class StationAdapter(
    private val setClickListeners: (item: Satellite, position: Int) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var satellites = emptyList<Satellite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationHolder {
        return StationHolder(parent)
    }

    override fun getItemCount(): Int = satellites.size

    fun setData(data: List<Satellite>) {
        satellites = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val satellite = satellites[position]
        when (holder) {
            is StationHolder -> holder.bind(satellite, position, setClickListeners)
        }
    }

}