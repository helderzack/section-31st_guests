package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.FragmentAbsentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.viewmodel.GuestsViewModel

class AbsentGuestsFragment : BaseFragment() {
    private var _binding: FragmentAbsentGuestsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAbsentGuestsBinding.inflate(inflater, container, false)
        statusFilter = GuestStatus.Absent

        with(binding) {
            recyclerViewAbsentGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAbsentGuests.adapter = adapter

            viewModel = ViewModelProvider(requireActivity())[GuestsViewModel::class.java]
            attachListenerToAdapter()

            viewModel.getAll(statusFilter)
            observe()

            floatingButtonAddAbsentGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }

            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.allGuests.observe(viewLifecycleOwner) {
            binding.textEmptyAbsentGuestsList.visibility =
                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            adapter.updateGuests(it)
        }
    }
}