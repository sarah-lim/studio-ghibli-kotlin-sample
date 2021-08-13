package com.slim.studiog.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slim.studiog.databinding.ViewHolderPeopleBinding
import com.slim.studiog.network.models.People

class PeopleAdapter(private val clickListener: OnClickListener) :
    ListAdapter<People, PeopleAdapter.PeopleViewHolder>(DiffCallback){

    class PeopleViewHolder(private var binding: ViewHolderPeopleBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(listener: OnClickListener, people: People) {
                binding.people = people
                binding.clicklistener = listener
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): PeopleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewHolderPeopleBinding.inflate(layoutInflater, parent, false)
                return PeopleViewHolder(binding)
            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class OnClickListener(val clickListener: (people: People) -> Unit) {
        fun onClick(people: People) = clickListener(people)
    }
}