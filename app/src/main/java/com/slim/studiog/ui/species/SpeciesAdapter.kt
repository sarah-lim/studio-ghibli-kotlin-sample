package com.slim.studiog.ui.species

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slim.studiog.databinding.ViewHolderSpeciesBinding
import com.slim.studiog.network.models.Species

class SpeciesAdapter(private val clickListener: OnClickListener) : ListAdapter<Species, SpeciesAdapter.SpeciesViewHolder>(DiffCallback) {

    class SpeciesViewHolder(private var binding: ViewHolderSpeciesBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(listener: OnClickListener, species: Species) {
                binding.species = species
                binding.clicklistener = listener
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): SpeciesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewHolderSpeciesBinding.inflate(layoutInflater, parent, false)
                return SpeciesViewHolder(binding)
            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Species>() {
        override fun areItemsTheSame(oldItem: Species, newItem: Species): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Species, newItem: Species): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        return SpeciesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class OnClickListener(val clickListener: (species: Species) -> Unit) {
        fun onClick(species: Species) = clickListener(species)
    }

}