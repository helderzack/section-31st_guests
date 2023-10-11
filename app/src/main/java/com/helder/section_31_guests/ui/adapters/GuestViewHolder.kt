package com.helder.section_31_guests.ui.adapters

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.databinding.GuestItemBinding
import com.helder.section_31_guests.ui.listener.OnGuestListener

class GuestViewHolder(
    private val binding: GuestItemBinding,
    private val listener: OnGuestListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(guest: Guest) {
        with(binding) {
            textViewGuestName.text = guest.name
            textViewGuestName.setOnClickListener {
                listener.onUpdate(guest.id)
            }

            imageDeleteGuest.setOnClickListener {
                AlertDialog.Builder(binding.root.context)
                    .setMessage(R.string.remove_guest_dialog_title)
                    .setPositiveButton(
                        R.string.positive_remove_guest_dialog_response
                    ) { _, _ ->
                        listener.onDelete(guest)
                    }
                    .setNegativeButton(R.string.negative_remove_guest_dialog_response, null)
                    .setCancelable(false)
                    .create().show()
            }
        }
    }
}