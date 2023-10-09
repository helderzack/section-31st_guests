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
import com.helder.section_31_guests.databinding.FragmentAllGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.listener.OnGuestListener
import com.helder.section_31_guests.ui.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {
    private lateinit var binding: FragmentAllGuestsBinding
    private lateinit var viewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        with(binding) {
            recyclerViewAllGuests.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewAllGuests.adapter = adapter

            viewModel = ViewModelProvider(requireActivity())[AllGuestsViewModel::class.java]

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
                        viewModel.getAll()
                    }
                }
            })

            viewModel.getAll()
            observe()

            floatingButtonAddGuest.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterGuestActivity::class.java))
            }

            return root
        }
    }

    private fun observe() {
        viewModel.allGuests.observe(viewLifecycleOwner) {
            binding.textEmptyGuestsList.visibility =
                if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            adapter.updateGuests(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    private fun showToast(toastMessages: String) {
        Toast.makeText(
            context,
            toastMessages,
            Toast.LENGTH_SHORT
        ).show()
    }
}