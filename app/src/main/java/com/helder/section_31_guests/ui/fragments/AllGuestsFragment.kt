package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.databinding.FragmentAllGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.viewmodel.GuestsViewModel

class AllGuestsFragment : BaseFragment() {
    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        with(binding) {
            recyclerViewAllGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAllGuests.adapter = adapter

            viewModel = ViewModelProvider(requireActivity())[GuestsViewModel::class.java]

            attachListenerToAdapter()

            viewModel.getAll(statusFilter)
            observe()

            floatingButtonAddGuest.setOnClickListener {
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
            binding.textEmptyGuestsList.visibility =
                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            adapter.updateGuests(it)
        }
    }
}