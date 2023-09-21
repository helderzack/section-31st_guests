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
import com.helder.section_31_guests.databinding.FragmentAbsentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.fragments.viewmodels.AbsentGuestsViewModel
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModelFactory

class AbsentGuestsFragment : Fragment() {
    private lateinit var binding: FragmentAbsentGuestsBinding
    private lateinit var viewModel: AbsentGuestsViewModel
    private lateinit var viewModelFactory: GuestsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbsentGuestsBinding.inflate(inflater, container, false)
        viewModelFactory = GuestsViewModelFactory(GuestLocalRepository(requireContext()))
        viewModel = ViewModelProvider(requireActivity(), this.viewModelFactory)[AbsentGuestsViewModel::class.java]

        with(binding) {
            recyclerViewAbsentGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAbsentGuests.adapter = GuestsAdapter(viewModel.getAbsentGuests())

            floatingButtonAddGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }
            return root
        }
    }
}