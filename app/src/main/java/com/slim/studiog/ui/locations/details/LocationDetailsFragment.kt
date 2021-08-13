package com.slim.studiog.ui.locations.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slim.studiog.databinding.FragmentLocationDetailsBinding

class LocationDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLocationDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val args = LocationDetailsFragmentArgs.fromBundle(requireArguments())

        binding.location = args.location

        return binding.root
    }
}