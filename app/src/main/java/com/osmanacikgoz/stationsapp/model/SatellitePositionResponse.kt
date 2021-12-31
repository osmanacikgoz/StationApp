package com.osmanacikgoz.stationsapp.model

class SatellitePositionResponse(
    val list: List<SatellitePosition>
){
    data class SatellitePosition(
        val id: String,
        val positions: List<Position>
    )

    data class Position(
        val posX: Double,
        val posY: Double
    )
}

