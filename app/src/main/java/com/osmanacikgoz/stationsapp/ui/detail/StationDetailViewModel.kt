package com.osmanacikgoz.stationsapp.ui.detail

import androidx.lifecycle.ViewModel
import com.osmanacikgoz.stationsapp.model.SatellitePositionResponse.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach

class StationDetailViewModel : ViewModel() {

    fun getPositionFlow(positions: List<Position>) = positions.asFlow().onEach {
        delay(3000)
    }

}