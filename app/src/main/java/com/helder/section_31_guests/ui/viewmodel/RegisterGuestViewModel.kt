package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.GuestModel

class RegisterGuestViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestLocalRepository.getInstance(application)
    private var _guest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> get() = _guest

    fun save(guest: GuestModel): Int = repository.insert(guest)

    fun update(guest: GuestModel): Int = repository.update(guest)

    fun getById(id: Int) {
        _guest.value = repository.getById(id)
    }
}