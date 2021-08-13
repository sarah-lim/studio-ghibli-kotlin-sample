package com.slim.studiog.ui.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slim.studiog.databinding.ViewHolderLocationsBinding
import com.slim.studiog.network.models.Location

class LocationsAdapter(private val clickListener: OnClickListener) :
    ListAdapter<Location, LocationsAdapter.LocationsViewHolder>(DiffCallback){

    class LocationsViewHolder(private var binding: ViewHolderLocationsBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(listener: OnClickListener, location:Location) {
                binding.location = location
                binding.clicklistener = listener
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): LocationsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewHolderLocationsBinding.inflate(layoutInflater, parent, false)
                return LocationsViewHolder(binding)
            }
        }

    }

    companion object DiffCallback: DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class OnClickListener(val clickListener: (location: Location) -> Unit) {
        fun onClick(location: Location) = clickListener(location)
    }

}