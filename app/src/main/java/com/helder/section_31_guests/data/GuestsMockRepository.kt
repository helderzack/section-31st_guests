package com.helder.section_31_guests.data

import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class GuestsMockRepository private constructor() {
    private val mockGuests: MutableList<Guest> = mutableListOf(
        Guest("Guest One", GuestStatus.Present),
        Guest("Guest Two", GuestStatus.Present),
        Guest("Guest Three", GuestStatus.Absent)
    )

    companion object {
        private var instance: GuestsMockRepository? = null

        fun getInstance(): GuestsMockRepository {
            if (instance == null) {
                instance = GuestsMockRepository()
            }

            return instance!!
        }
    }


    fun addGuest(guest: Guest) {
        mockGuests.add(guest)
    }

    fun changeGuestStatus(guestIndex: Int, guestStatus: GuestStatus) {
        mockGuests.elementAt(guestIndex).guestStatus = guestStatus
    }

    fun getAllGuests(): List<Guest> = mockGuests


    fun getPresentGuests(): List<Guest> =
        mockGuests.filter { guest -> guest.guestStatus == GuestStatus.Present }

    fun getAbsentGuests(): List<Guest> =
        mockGuests.filter { guest -> guest.guestStatus == GuestStatus.Absent }
}