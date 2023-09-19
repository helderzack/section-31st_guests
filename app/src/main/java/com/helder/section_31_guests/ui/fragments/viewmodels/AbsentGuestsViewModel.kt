package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.helder.section_31_guests.data.GuestsMockRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class AbsentGuestsViewModel: ViewModel() {
    fun getAbsentGuests(): List<Guest> =
        GuestsMockRepository.getInstance().getGuests().filter { guest -> guest.guestStatus == GuestStatus.Absent }
}