package com.helder.section_31_guests.ui.listener

import com.helder.section_31_guests.data.model.Guest

interface OnGuestListener {
    fun onUpdate(id: Int)

    fun onDelete(guest: Guest)
}