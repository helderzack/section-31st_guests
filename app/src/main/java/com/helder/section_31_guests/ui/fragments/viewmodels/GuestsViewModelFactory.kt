package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.helder.section_31_guests.data.database.GuestLocalRepository

class GuestsViewModelFactory(private val guestLocalRepository: GuestLocalRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AllGuestsViewModel::class.java)) {
            return AllGuestsViewModel(guestLocalRepository) as T
        }

        if(modelClass.isAssignableFrom(PresentGuestsViewModel::class.java)) {
            return PresentGuestsViewModel(guestLocalRepository) as T
        }

        if(modelClass.isAssignableFrom(AbsentGuestsViewModel::class.java)) {
            return AbsentGuestsViewModel(guestLocalRepository) as T
        }


        throw IllegalArgumentException("Unknown View Model Class")
    }
}