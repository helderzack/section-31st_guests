package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.helder.section_31_guests.data.GuestsMockRepository
import com.helder.section_31_guests.databinding.FragmentAllGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.fragments.viewmodels.AllGuestsViewModel

class AllGuestsFragment : Fragment() {
    private lateinit var binding: FragmentAllGuestsBinding
    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[AllGuestsViewModel::class.java]

        with(binding) {

            textViewGuestName.text = GuestsMockRepository.getInstance().getAllGuests().first().name

            floatingButtonAddGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }
            return root
        }
    }
}