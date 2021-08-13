package com.slim.studiog.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "vehicle_class")
    val vehicleClass: String,
    val length: String,
    val pilot: String,
    val films: List<String>,
    val url: String
): Parcelable {
}