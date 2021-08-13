package com.slim.studiog.ui.vehicles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slim.studiog.databinding.ViewHolderVehiclesBinding
import com.slim.studiog.network.models.Vehicle

class VehiclesAdapter(private val clickListener: OnClickListener) : ListAdapter<Vehicle, VehiclesAdapter.VehiclesViewHolder>(DiffCallback){

    class VehiclesViewHolder(private var binding: ViewHolderVehiclesBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(listener: OnClickListener, vehicle: Vehicle) {
                binding.clicklistener  = listener
                binding.vehicle = vehicle
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): VehiclesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewHolderVehiclesBinding.inflate(layoutInflater, parent, false)
                return VehiclesViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        return VehiclesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (vehicle: Vehicle) -> Unit){
        fun onClick(vehicle: Vehicle) = clickListener(vehicle)
    }

}