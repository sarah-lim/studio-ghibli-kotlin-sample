package com.slim.studiog.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slim.studiog.network.models.*
import com.slim.studiog.ui.films.FilmsAdapter
import com.slim.studiog.ui.locations.LocationsAdapter
import com.slim.studiog.ui.people.PeopleAdapter
import com.slim.studiog.ui.species.SpeciesAdapter
import com.slim.studiog.ui.vehicles.VehiclesAdapter


@BindingAdapter("filmsData")
fun bindFilms(recyclerView: RecyclerView, data: List<Film>?) {
    val adapter = recyclerView.adapter as FilmsAdapter
    adapter.submitList(data)
}

@BindingAdapter(value = ["originalTitle", "romanised"], requireAll = true)
fun bindOrigTitle(textView: TextView, romanised: String, originalTitle : String) {
    textView.text = "$romanised($originalTitle)"
}

@BindingAdapter("peopleData")
fun bindPeople(recyclerView: RecyclerView, data: List<People>?) {
    val adapter = recyclerView.adapter as PeopleAdapter
    adapter.submitList(data)
}

@BindingAdapter("locationsData")
fun bindLocations(recyclerView: RecyclerView, data: List<Location>?) {
    val adapter = recyclerView.adapter as LocationsAdapter
    adapter.submitList(data)
}

@BindingAdapter("speciesData")
fun bindSpecies(recyclerView: RecyclerView, data: List<Species>?) {
    val adapter = recyclerView.adapter as SpeciesAdapter
    adapter.submitList(data)
}

@BindingAdapter("vehiclesData")
fun bindVehicles(recyclerView: RecyclerView, data: List<Vehicle>?) {
    val adapter = recyclerView.adapter as VehiclesAdapter
    adapter.submitList(data)
}