package com.slim.studiog.ui.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.slim.studiog.databinding.FragmentVehiclesBinding
import timber.log.Timber

class VehiclesFragment : Fragment() {

    private val viewModel : VehiclesViewModel by lazy {
        ViewModelProvider(this).get(VehiclesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentVehiclesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = VehiclesAdapter(VehiclesAdapter.OnClickListener {
            vehicle ->
            Timber.d("clicked: ${vehicle.name}")
            findNavController().navigate(VehiclesFragmentDirections
                .actionVehiclesFragmentToVehicleDetailsFragment(vehicle))
        })

        binding.fragmentVehiclesRecyclerview.adapter = adapter

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Studio Ghibli: Vehicles"
        return binding.root
    }
}