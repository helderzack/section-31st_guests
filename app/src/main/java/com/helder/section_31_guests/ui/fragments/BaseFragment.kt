package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.R
import com.helder.section_31_guests.constants.GuestConstants
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.GuestsFragmentsLayoutBinding
import com.helder.section_31_guests.service.ShowToastService
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.listener.OnGuestListener
import com.helder.section_31_guests.ui.viewmodel.GuestsViewModel

open class BaseFragment : Fragment() {
    private var _binding: GuestsFragmentsLayoutBinding? = null
    private val binding get() = _binding!!
    private var adapter = GuestsAdapter()
    private lateinit var viewModel: GuestsViewModel
    private val showToastService = ShowToastService.getInstance()
    protected var statusFilter: GuestStatus? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GuestsFragmentsLayoutBinding.inflate(inflater, container, false)

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

    override fun onResume() {
        super.onResume()
        viewModel.getAll(statusFilter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attachListenerToAdapter() {
        adapter.attachListener(object : OnGuestListener {
            override fun onUpdate(id: Int) {
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST.ID, id)
                val intent = Intent(requireContext(), RegisterGuestActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(guest: Guest) {
                try {
                    viewModel.deleteGuest(guest)
                    showToastService.showToast(requireContext(), getString(R.string.successful_delete_action))
                } catch(e: Exception) {
                    showToastService.showToast(requireContext(), getString(R.string.failed_delete_action))
                }

                viewModel.getAll(statusFilter)
            }
        })
    }

    private fun observe() {
        viewModel.allGuests.observe(viewLifecycleOwner) {
            binding.textEmptyGuestsList.visibility =
                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            adapter.updateGuests(it)
        }
    }
}