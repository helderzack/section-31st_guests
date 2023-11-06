package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.helder.section_31_guests.data.database.GuestRepository
import com.helder.section_31_guests.data.model.Guest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)

    private var _allGuests = MutableStateFlow<List<Guest>>(mutableListOf())
    val allGuests = _allGuests.asStateFlow()

    var channel = Channel<Int>()

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().collect {
                _allGuests.value = it
            }
        }
    }

    fun getPresent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPresent().collect {
                _allGuests.value = it
            }
        }
    }

    fun getAbsent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAbsent().collect {
                _allGuests.value = it
            }
        }
    }

    fun deleteGuest(guest: Guest) {
        viewModelScope.launch(Dispatchers.IO) {
            val deletedRows = repository.delete(guest)
            channel.send(deletedRows)
        }
    }

}