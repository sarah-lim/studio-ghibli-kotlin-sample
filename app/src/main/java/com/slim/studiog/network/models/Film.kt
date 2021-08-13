package com.slim.studiog.network.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(val id: String,
                val title: String,
                @Json(name = "original_title")
                val originalTitle: String,
                @Json(name="original_title_romanised")
                val originalTitleRomanised: String,
                val description: String,
                val director: String,
                val producer: String,
                @Json(name = "release_date")
                val releaseDate: String,
                @Json(name = "running_time")
                val runningTime: Integer,
                @Json(name = "rt_score")
                val rtScore: Integer,
                val people: List<String>,
                val species: List<String>,
                val locations: List<String>,
                val vehicles: List<String>,
                val url: String
                ) : Parcelable {
}