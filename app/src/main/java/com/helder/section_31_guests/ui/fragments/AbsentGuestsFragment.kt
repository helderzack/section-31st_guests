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
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.FragmentAbsentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModelFactory

class AbsentGuestsFragment : Fragment() {
    private lateinit var binding: FragmentAbsentGuestsBinding
    private lateinit var viewModel: GuestsViewModel
    private lateinit var viewModelFactory: GuestsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbsentGuestsBinding.inflate(inflater, container, false)

        with(binding) {
            recyclerViewAbsentGuests.layoutManager = LinearLayoutManager(requireContext())

            viewModelFactory = GuestsViewModelFactory(GuestLocalRepository(requireContext()))
            viewModel =
                ViewModelProvider(requireActivity(), viewModelFactory)[GuestsViewModel::class.java]

            viewModel.getObservable().observe(viewLifecycleOwner) {
                recyclerViewAbsentGuests.adapter = GuestsAdapter(it.filter { guest: Guest ->
                    guest.guestStatus == GuestStatus.Absent
                }, requireContext())

                if (it.none { guest: Guest ->
                        guest.guestStatus == GuestStatus.Absent
                    }) {
                    textEmptyAbsentGuestsList.visibility = View.VISIBLE
                } else {
                    textEmptyAbsentGuestsList.visibility = View.INVISIBLE
                }
            }

            floatingButtonAddAbsentGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }
        }
        return binding.root
    }
}