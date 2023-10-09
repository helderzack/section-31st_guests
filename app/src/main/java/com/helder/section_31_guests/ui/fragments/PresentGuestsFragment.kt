package com.helder.section_31_guests.ui.fragments

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
import com.helder.section_31_guests.databinding.FragmentPresentGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.listener.OnGuestListener
import com.helder.section_31_guests.ui.viewmodel.PresentGuestsViewModel

class PresentGuestsFragment : Fragment() {
    private lateinit var binding: FragmentPresentGuestsBinding
    private lateinit var viewModel: PresentGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresentGuestsBinding.inflate(inflater, container, false)

        with(binding) {
            recyclerViewPresentGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewPresentGuests.adapter = adapter

            viewModel = ViewModelProvider(requireActivity())[PresentGuestsViewModel::class.java]

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
                        viewModel.getPresent()
                    }
                }

            })

            viewModel.getPresent()
            observe()

            floatingButtonAddPresentGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }

            return root
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPresent()
    }

    private fun observe() {
        viewModel.presentGuests.observe(viewLifecycleOwner) {
            binding.textEmptyPresentGuestsList.visibility =
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