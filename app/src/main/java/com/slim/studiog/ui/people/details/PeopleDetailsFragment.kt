package com.slim.studiog.ui.people.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.slim.studiog.databinding.FragmentPeopleDetailsBinding

class PeopleDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPeopleDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        var args = PeopleDetailsFragmentArgs.fromBundle(requireArguments())

        binding.people = args.people


        return binding.root
    }
}