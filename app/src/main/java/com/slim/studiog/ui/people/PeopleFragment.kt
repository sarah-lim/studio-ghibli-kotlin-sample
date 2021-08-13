package com.slim.studiog.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.slim.studiog.databinding.FragmentPeopleBinding
import timber.log.Timber

class PeopleFragment : Fragment() {

    private val viewModel: PeopleViewModel by lazy {
        ViewModelProvider(this).get(PeopleViewModel::class.java)
    }

    private lateinit var binding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeopleBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = PeopleAdapter(PeopleAdapter.OnClickListener{
            people ->
            Timber.d("clicked: ${people.name}")
            findNavController().navigate(PeopleFragmentDirections.actionPeopleFragmentToPeopleDetailsFragment(people))
        })

        binding.fragmentPeopleRecyclerview.adapter = adapter
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Studio Ghibli: People"
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)

        binding.fragmentPeopleSwipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        return binding.root
    }
}