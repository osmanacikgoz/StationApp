package com.osmanacikgoz.stationsapp.model

import com.google.gson.annotations.SerializedName

data class Satellite(
    @SerializedName("name")
    val name: String,
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("id")
    val id: Int
)