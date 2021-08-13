package com.slim.studiog.ui.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.slim.studiog.databinding.FragmentVehicleDetailsBinding

class VehicleDetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVehicleDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val args = VehicleDetailsFragmentArgs.fromBundle(requireArguments())
        binding.vehicle = args.vehicle

        return binding.root
    }
}