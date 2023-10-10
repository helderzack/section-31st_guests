package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.listener.OnGuestListener
import com.helder.section_31_guests.ui.viewmodel.GuestsViewModel

open class BaseFragment : Fragment() {
    protected var adapter = GuestsAdapter()
    protected lateinit var viewModel: GuestsViewModel
    protected var statusFilter: GuestStatus? = null

    override fun onResume() {
        super.onResume()
        viewModel.getAll(statusFilter)
    }

    protected fun attachListenerToAdapter() {
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
                    viewModel.getAll(statusFilter)
                }
            }
        })
    }

    protected fun showToast(toastMessages: String) {
        Toast.makeText(
            context,
            toastMessages,
            Toast.LENGTH_SHORT
        ).show()
    }
}