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
import com.helder.section_31_guests.databinding.FragmentAllGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.fragments.viewmodels.AllGuestsViewModel
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModelFactory

class AllGuestsFragment : Fragment() {
    private lateinit var binding: FragmentAllGuestsBinding
    private lateinit var viewModel: AllGuestsViewModel
    private lateinit var viewModelFactory: GuestsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        viewModelFactory = GuestsViewModelFactory(GuestLocalRepository(requireContext()))
        viewModel = ViewModelProvider(requireActivity(), this.viewModelFactory)[AllGuestsViewModel::class.java]

        with(binding) {
            recyclerViewAllGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAllGuests.adapter = GuestsAdapter(viewModel.getAllGuests())

            floatingButtonAddGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }
            return root
        }
    }
}