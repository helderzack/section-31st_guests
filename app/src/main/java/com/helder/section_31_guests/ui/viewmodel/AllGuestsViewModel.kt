package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.GuestModel

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {
    private var _allGuests = MutableLiveData<List<GuestModel>>()
    val allGuests get(): LiveData<List<GuestModel>> = _allGuests
    private val repository = GuestLocalRepository.getInstance(application.applicationContext)

    fun getAll() {
        _allGuests.value = repository.getGuests(null)
    }

    fun deleteGuest(id: Int): Int = repository.delete(id)
}