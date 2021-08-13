package com.slim.studiog.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(val id: String,
                    val name: String,
                    val climate: String,
                    val terrain: String,
                    @Json(name = "surface_water")
                    val surfaceWater: String,
                    val residents: List<String>,
                    val films: List<String>,
                    val url: String
    ):Parcelable{
}