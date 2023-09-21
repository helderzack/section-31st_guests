package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class PresentGuestsViewModel(private val guestLocalRepository: GuestLocalRepository) : ViewModel() {
    fun getPresentGuests(): List<Guest> = guestLocalRepository.getGuests()
        .filter { guest -> guest.guestStatus == GuestStatus.Present }
}