package com.helder.section_31_guests.ui.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class GuestsViewModel private constructor(private val guestLocalRepository: GuestLocalRepository) :
    ViewModel() {
    private val observableGuests = MutableLiveData<List<Guest>>()
    private var guests: List<Guest> = guestLocalRepository.getGuests()

    companion object {
        private var instance: GuestsViewModel? = null

        fun getInstance(guestLocalRepository: GuestLocalRepository?): GuestsViewModel {
            if (instance == null) {
                instance = GuestsViewModel(guestLocalRepository!!)
            }

            return instance!!
        }
    }

    fun getObservable(): LiveData<List<Guest>> {
        observableGuests.postValue(guests)
        return observableGuests
    }

    fun filterGuests(guestStatusFilter: GuestStatus?) {
        if (guestStatusFilter == null) {
            observableGuests.postValue(guests)
        } else {
            observableGuests.postValue(guests.filter { guest -> guest.guestStatus == guestStatusFilter })
        }
    }

    fun saveGuest(guest: Guest) {
        guestLocalRepository.save(guest)
        guests = guestLocalRepository.getGuests()
        //I'm not posting a new value to the observable here because this saveGuest function is being called
        //      only from the RegisterGuestActivity. When the guest is saved, the RegisterGuestActivity starts
        //      a new MainActivity, which  will initialize the GuestsFragment once again, so the updated guests
        //      lists is obtained.
    }

    fun deleteGuest(guestId: String) {
        guestLocalRepository.delete(guestId)
        guests = guestLocalRepository.getGuests()
        observableGuests.postValue(guests)
    }
}