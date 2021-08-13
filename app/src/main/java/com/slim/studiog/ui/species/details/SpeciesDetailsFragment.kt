package com.slim.studiog.ui.species.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slim.studiog.databinding.FragmentSpeciesDetailsBinding

class SpeciesDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSpeciesDetailsBinding.inflate(inflater)

        val args = SpeciesDetailsFragmentArgs.fromBundle(requireArguments())

        binding.species = args.species

        return binding.root
    }
}