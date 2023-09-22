package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.databinding.FragmentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModelFactory

class GuestsFragment : Fragment() {
    private lateinit var binding: FragmentGuestsBinding
    private lateinit var viewModel: GuestsViewModel
    private lateinit var viewModelFactory: GuestsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuestsBinding.inflate(inflater, container, false)

        with(binding) {
            recyclerViewAllGuests.layoutManager = LinearLayoutManager(requireContext())

            viewModelFactory = GuestsViewModelFactory(GuestLocalRepository(requireContext()))
            viewModel =
                ViewModelProvider(requireActivity(), viewModelFactory)[GuestsViewModel::class.java]
            viewModel.getObservable().observe(viewLifecycleOwner) {
                recyclerViewAllGuests.adapter = GuestsAdapter(it)
            }

            floatingButtonAddGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }
        }
        return binding.root
    }

}