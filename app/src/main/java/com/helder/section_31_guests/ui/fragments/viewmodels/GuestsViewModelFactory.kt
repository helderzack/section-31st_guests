package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helder.section_31_guests.data.database.GuestLocalRepository

class GuestsViewModelFactory(private val guestLocalRepository: GuestLocalRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuestsViewModel::class.java)) {
            return GuestsViewModel.getInstance(guestLocalRepository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}