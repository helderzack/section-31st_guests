package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.GuestModel
import com.helder.section_31_guests.data.model.GuestStatus

class AbsentGuestsViewModel(application: Application) : AndroidViewModel(application) {
    private var _absentGuests = MutableLiveData<List<GuestModel>>()
    val absentGuests get(): LiveData<List<GuestModel>> = _absentGuests
    private val repository = GuestLocalRepository.getInstance(application.applicationContext)

    fun getAbsent() {
        _absentGuests.value = repository.getGuests(GuestStatus.Absent)
    }

    fun deleteGuest(id: Int): Int = repository.delete(id)
}