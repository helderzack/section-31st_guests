package com.helder.section_31_guests.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.helder.section_31_guests.data.database.GuestRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterGuestViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)

    private var _guest = MutableStateFlow(Guest(0, "", GuestStatus.Present))
    val guest = _guest.asStateFlow()

    var channel = Channel<Int>()

    fun save(guest: Guest) {
        viewModelScope.launch(Dispatchers.IO) {
            val insertReturn = repository.insert(guest)
            channel.send(insertReturn.toInt())
        }
    }

    fun update(guest: Guest) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedRows = repository.update(guest)
            channel.send(updatedRows)
        }
    }

    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getById(id).collect {
                _guest.value = it
            }
        }
    }
}