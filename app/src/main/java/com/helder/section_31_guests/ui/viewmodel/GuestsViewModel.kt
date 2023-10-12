package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helder.section_31_guests.data.database.GuestRepository
import com.helder.section_31_guests.data.model.Guest

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)
    private var _allGuests = MutableLiveData<List<Guest>>()
    val allGuests get(): LiveData<List<Guest>> = _allGuests

    fun getAll() {
        _allGuests.value = repository.getAll()
    }

    fun getPresent() {
        _allGuests.value = repository.getPresent()
    }

    fun getAbsent() {
        _allGuests.value = repository.getAbsent()
    }

    fun deleteGuest(guest: Guest) = repository.delete(guest)
}