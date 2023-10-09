package com.helder.section_31_guests.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.GuestModel
import com.helder.section_31_guests.databinding.FragmentAbsentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.listener.OnGuestListener
import com.helder.section_31_guests.ui.viewmodel.AbsentGuestsViewModel

class AbsentGuestsFragment : Fragment() {
    private lateinit var binding: FragmentAbsentGuestsBinding
    private lateinit var viewModel: AbsentGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbsentGuestsBinding.inflate(inflater, container, false)

        with(binding) {
            recyclerViewAbsentGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAbsentGuests.adapter = adapter

            viewModel = ViewModelProvider(requireActivity())[AbsentGuestsViewModel::class.java]

            adapter.attachListener(object : OnGuestListener {
                override fun onUpdate(id: Int) {
                    val bundle = Bundle()
                    bundle.putInt(getString(R.string.guest_extra), id)
                    val intent = Intent(requireContext(), RegisterGuestActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }

                override fun onDelete(id: Int) {
                    val deletedRows = viewModel.deleteGuest(id)

                    if (deletedRows < 1) {
                        showToast(getString(R.string.failed_delete_action_no_row_deleted))
                    } else if (deletedRows > 1) {
                        showToast(getString(R.string.failed_delete_action_more_than_one_row_deleted))
                    } else {
                        showToast(getString(R.string.successful_delete_action))
                        viewModel.getAbsent()
                    }
                }
            })

            viewModel.getAbsent()
            observe()

            floatingButtonAddAbsentGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }

            return root
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }

    private fun observe() {
        viewModel.absentGuests.observe(viewLifecycleOwner) {
            binding.textEmptyAbsentGuestsList.visibility =
                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            adapter.updateGuests(it)
        }
    }

    private fun showToast(toastMessages: String) {
        Toast.makeText(
            context,
            toastMessages,
            Toast.LENGTH_SHORT
        ).show()
    }
}