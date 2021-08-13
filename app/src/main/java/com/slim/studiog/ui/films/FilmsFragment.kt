package com.slim.studiog.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.slim.studiog.databinding.FragmentFilmsBinding
import com.slim.studiog.network.StudioGhibliApiStatus
import timber.log.Timber

class FilmsFragment : Fragment() {

    private val viewModel: FilmViewModel by lazy {
        ViewModelProvider(this).get(FilmViewModel::class.java)
    }

    private lateinit var binding : FragmentFilmsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        initLayout()

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Studio Ghibli"


        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it){
                StudioGhibliApiStatus.LOADING -> binding.fragmentFilmsSwipeRefresh.isRefreshing = true
                else -> binding.fragmentFilmsSwipeRefresh.isRefreshing = false
            }
        })

        return binding.root
    }

    private fun initLayout() {
        val adapter = FilmsAdapter(FilmsAdapter.OnClickListener {
                film ->
            Timber.d("clicked: ${film.title}")
            findNavController().navigate(FilmsFragmentDirections.actionFilmsFragmentToFilmDetailsFragment(film))
        })

        binding.fragmentFilmsRecyclerview.adapter = adapter

        binding.fragmentFilmsSwipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            viewModel.refresh()
        })
    }
}