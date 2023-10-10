package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.helder.section_31_guests.R
import com.helder.section_31_guests.constants.GuestConstants
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.service.ShowToastService
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.listener.OnGuestListener
import com.helder.section_31_guests.ui.viewmodel.GuestsViewModel

open class BaseFragment : Fragment() {
    protected var adapter = GuestsAdapter()
    protected lateinit var viewModel: GuestsViewModel
    protected var statusFilter: GuestStatus? = null
    protected val showToastService = ShowToastService.getInstance()

    override fun onResume() {
        super.onResume()
        viewModel.getAll(statusFilter)
    }

    protected fun attachListenerToAdapter() {
        adapter.attachListener(object : OnGuestListener {
            override fun onUpdate(id: Int) {
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST.ID, id)
                val intent = Intent(requireContext(), RegisterGuestActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                val deletedRows = viewModel.deleteGuest(id)

                if (deletedRows < 1) {
                    showToastService.showToast(
                        requireContext(),
                        getString(R.string.failed_delete_action_no_row_deleted)
                    )
                } else if (deletedRows > 1) {
                    showToastService.showToast(
                        requireContext(),
                        getString(R.string.failed_delete_action_more_than_one_row_deleted)
                    )
                } else {
                    showToastService.showToast(
                        requireContext(),
                        getString(R.string.successful_delete_action)
                    )
                    viewModel.getAll(statusFilter)
                }
            }
        })
    }
}