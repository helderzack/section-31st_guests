package com.helder.section_31_guests.ui.fragments

import com.helder.section_31_guests.data.model.GuestStatus

class PresentGuestsFragment : BaseFragment() {

    init {
        statusFilter = GuestStatus.Present
    }
}