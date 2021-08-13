package com.slim.studiog.ui.species

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.slim.studiog.databinding.FragmentSpeciesBinding
import timber.log.Timber

class SpeciesFragment : Fragment() {

    private val viewModel : SpeciesViewModel by lazy{
        ViewModelProvider(this).get(SpeciesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSpeciesBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = SpeciesAdapter(SpeciesAdapter.OnClickListener {
            species ->
            Timber.d("clicked ${species.name}")
            findNavController().navigate(SpeciesFragmentDirections
                .actionSpeciesFragmentToSpeciesDetailsFragment(species))
        })

        binding.fragmentSpeciesRecyclerview.adapter = adapter

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Studio Ghibli: Species"
        return binding.root
    }
}