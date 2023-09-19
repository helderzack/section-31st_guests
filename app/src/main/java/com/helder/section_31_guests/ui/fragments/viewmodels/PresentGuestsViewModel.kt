package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.helder.section_31_guests.data.GuestsMockRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class PresentGuestsViewModel : ViewModel() {
    fun getPresentGuests(): List<Guest> =
        GuestsMockRepository.getInstance().getGuests()
            .filter { guest -> guest.guestStatus == GuestStatus.Present }
}