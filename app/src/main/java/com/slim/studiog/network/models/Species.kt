package com.slim.studiog.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Species(
    val id: String,
    val name: String,
    val classification: String,
    @Json(name = "eye_colors")
    val eyeColors: String,
    @Json(name = "hair_colors")
    val hairColors: String,
    val url: String,
    val people: List<String>,
    val films: List<String>
):Parcelable {
}