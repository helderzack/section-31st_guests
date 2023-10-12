package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helder.section_31_guests.data.database.GuestRepository
import com.helder.section_31_guests.data.model.Guest

class RegisterGuestViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)
    private var _guest = MutableLiveData<Guest>()
    val guest: LiveData<Guest> get() = _guest

    fun save(guest: Guest) = repository.insert(guest)

    fun update(guest: Guest) = repository.update(guest)

    fun getById(id: Int) {
        _guest.value = repository.getById(id)
    }
}