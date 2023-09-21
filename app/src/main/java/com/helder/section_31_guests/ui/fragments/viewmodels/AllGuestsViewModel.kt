package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.Guest

class AllGuestsViewModel(private val guestLocalRepository: GuestLocalRepository) : ViewModel() {
    fun getAllGuests(): List<Guest> = guestLocalRepository.getGuests()
}