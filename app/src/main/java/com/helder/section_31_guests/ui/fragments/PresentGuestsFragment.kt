package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.data.GuestsMockRepository
import com.helder.section_31_guests.databinding.FragmentPresentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.fragments.viewmodels.PresentGuestsViewModel

class PresentGuestsFragment : Fragment() {
    private lateinit var binding: FragmentPresentGuestsBinding
    private lateinit var viewModel: PresentGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresentGuestsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[PresentGuestsViewModel::class.java]

        with(binding) {
            recyclerViewPresentGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewPresentGuests.adapter = GuestsAdapter(viewModel.getPresentGuests())

            floatingButtonAddGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }
            return root
        }
    }
}