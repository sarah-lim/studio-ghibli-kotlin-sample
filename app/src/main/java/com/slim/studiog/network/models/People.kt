package com.slim.studiog.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class People(val id: String, val name: String, val gender: String, val age: String,
                  @Json(name="eye_color")
                  val eyeColor: String,
                  @Json(name="hair_color")
                  val hairColor: String,
                  val films: List<String>,
                  val species: String,
                  val url: String
                  ): Parcelable {
}