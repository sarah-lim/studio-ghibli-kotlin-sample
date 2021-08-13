package com.slim.studiog.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.slim.studiog.databinding.FragmentLocationsBinding
import com.slim.studiog.network.StudioGhibliApiStatus
import timber.log.Timber

class LocationsFragment : Fragment() {

    private val viewModel : LocationsViewModel by lazy {
        ViewModelProvider(this).get(LocationsViewModel::class.java)
    }

    private lateinit var binding: FragmentLocationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationsBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = LocationsAdapter(LocationsAdapter.OnClickListener {
            location ->
            Timber.d("clicked: ${location.name}")
            findNavController().navigate(LocationsFragmentDirections.actionLocationsFragmentToLocationDetailsFragment(location))
        })

        binding.fragmentLocationsRecyclerview.adapter = adapter
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Studio Ghibli: Locations"

        binding.fragmentLocationsSwipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it) {
                StudioGhibliApiStatus.LOADING -> binding.fragmentLocationsSwipeRefresh.isRefreshing = true
                else -> binding.fragmentLocationsSwipeRefresh.isRefreshing = false
            }

        })
        return binding.root
    }
}