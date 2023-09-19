package com.helder.section_31_guests.data

import com.helder.section_31_guests.data.model.Guest

class GuestsMockRepository private constructor() {
    private val mockGuests: MutableList<Guest> = mutableListOf()

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

    fun getGuests(): List<Guest> = mockGuests
}