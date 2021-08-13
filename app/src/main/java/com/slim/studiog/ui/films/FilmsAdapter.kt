package com.slim.studiog.ui.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slim.studiog.databinding.ViewHolderFilmsBinding
import com.slim.studiog.network.models.Film

class FilmsAdapter ( private val clickListener: OnClickListener) :
    ListAdapter<Film, FilmsAdapter.FilmsViewHolder>(DiffCallback){

    class FilmsViewHolder(private var binding: ViewHolderFilmsBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(listener: OnClickListener, film: Film) {
                binding.film = film
                binding.clicklistener = listener
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): FilmsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewHolderFilmsBinding.inflate(layoutInflater, parent, false)
                return FilmsViewHolder(binding)
            }
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
       return FilmsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class OnClickListener(val clickListener: (film: Film) -> Unit) {
        fun onClick(film: Film) = clickListener(film)
    }

}