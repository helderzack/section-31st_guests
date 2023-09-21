package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class AbsentGuestsViewModel(private val guestLocalRepository: GuestLocalRepository) : ViewModel() {
    fun getAbsentGuests(): List<Guest> = guestLocalRepository.getGuests()
        .filter { guest -> guest.guestStatus == GuestStatus.Absent }
}