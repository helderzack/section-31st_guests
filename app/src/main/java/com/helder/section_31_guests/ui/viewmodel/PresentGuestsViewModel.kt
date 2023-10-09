package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.GuestModel
import com.helder.section_31_guests.data.model.GuestStatus

class PresentGuestsViewModel(application: Application) : AndroidViewModel(application) {
    private var _presentGuests = MutableLiveData<List<GuestModel>>()
    val presentGuests get(): LiveData<List<GuestModel>> = _presentGuests
    private val repository = GuestLocalRepository.getInstance(application.applicationContext)

    fun getPresent() {
        _presentGuests.value = repository.getGuests(GuestStatus.Present)
    }

    fun deleteGuest(id: Int): Int = repository.delete(id)
}