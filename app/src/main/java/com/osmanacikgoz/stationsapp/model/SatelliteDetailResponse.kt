package com.osmanacikgoz.stationsapp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class SatelliteDetailResponse: ArrayList<SatelliteDetailResponse.SatelliteDetail>() {

    @Parcelize
    data class SatelliteDetail(
        @SerializedName("id")
        val id: Int? = null,
        var stationName: String? = null,
        @SerializedName("cost_per_launch")
        val costPerLaunch: Int? = null,
        @SerializedName("first_flight")
        val firstFlight: String? = null,
        @SerializedName("height")
        val height: Int? = null,
        @SerializedName("mass")
        val mass: Int? = null
    ): Parcelable

}