package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.FragmentPresentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModelFactory
import com.helder.section_31_guests.util.UtilMethods

class PresentGuestsFragment : Fragment() {
    private lateinit var binding: FragmentPresentGuestsBinding
    private lateinit var viewModel: GuestsViewModel
    private lateinit var viewModelFactory: GuestsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresentGuestsBinding.inflate(inflater, container, false)
        Log.d(UtilMethods.getInstance().getLogTag(), "onCreateView() on ${this.javaClass}")

        with(binding) {
            recyclerViewPresentGuests.layoutManager = LinearLayoutManager(requireContext())

            viewModelFactory = GuestsViewModelFactory(GuestLocalRepository(requireContext()))
            viewModel =
                ViewModelProvider(requireActivity(), viewModelFactory)[GuestsViewModel::class.java]

            viewModel.getObservable().observe(viewLifecycleOwner) {
                Log.d(UtilMethods.getInstance().getLogTag(), "getObservable() on ${this.javaClass}")

                recyclerViewPresentGuests.adapter = GuestsAdapter(it.filter { guest: Guest ->
                    guest.guestStatus == GuestStatus.Present
                }, requireContext())

                if (it.none { guest: Guest ->
                        guest.guestStatus == GuestStatus.Present
                    }) {
                    textEmptyPresentGuestsList.visibility = View.VISIBLE
                } else {
                    textEmptyPresentGuestsList.visibility = View.INVISIBLE
                }
            }

            floatingButtonAddPresentGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }
        }

        return binding.root
    }
}