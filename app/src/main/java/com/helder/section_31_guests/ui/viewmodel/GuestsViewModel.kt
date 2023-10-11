package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private var _allGuests = MutableLiveData<List<Guest>>()
    val allGuests get(): LiveData<List<Guest>> = _allGuests
    private val repository = GuestLocalRepository.getInstance(application.applicationContext)

    fun getAll(statusFilter: GuestStatus?) {
        _allGuests.value = repository.getGuests(statusFilter)
    }

    fun deleteGuest(guest: Guest) = repository.delete(guest)
}