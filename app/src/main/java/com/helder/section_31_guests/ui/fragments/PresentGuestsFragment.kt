package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.FragmentPresentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.viewmodel.GuestsViewModel

class PresentGuestsFragment : BaseFragment() {
    private lateinit var binding: FragmentPresentGuestsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresentGuestsBinding.inflate(inflater, container, false)
        statusFilter = GuestStatus.Present

        with(binding) {
            recyclerViewPresentGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewPresentGuests.adapter = adapter

            viewModel = ViewModelProvider(requireActivity())[GuestsViewModel::class.java]

            attachListenerToAdapter()

            viewModel.getAll(statusFilter)
            observe()

            floatingButtonAddPresentGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }

            return root
        }
    }

    private fun observe() {
        viewModel.allGuests.observe(viewLifecycleOwner) {
            binding.textEmptyPresentGuestsList.visibility =
                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            adapter.updateGuests(it)
        }
    }
}