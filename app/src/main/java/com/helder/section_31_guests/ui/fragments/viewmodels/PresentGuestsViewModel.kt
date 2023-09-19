package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.helder.section_31_guests.data.GuestsMockRepository
import com.helder.section_31_guests.data.model.Guest

class PresentGuestsViewModel: ViewModel() {
    fun getAllGuests(): List<Guest> {
        return GuestsMockRepository.getInstance().getPresentGuests()
    }

    fun getOneGuest(): Guest {
        return GuestsMockRepository.getInstance().getPresentGuests().first()
    }
}