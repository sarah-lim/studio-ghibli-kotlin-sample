package com.slim.studiog.ui.films.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.slim.studiog.databinding.FragmentFilmDetailsBinding

class FilmDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFilmDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        var args = FilmDetailsFragmentArgs.fromBundle(requireArguments())

        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.film.title

        binding.film = args.film
        return binding.root
    }
}