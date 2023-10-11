package com.helder.section_31_guests.data.database

import android.content.Context
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class GuestLocalRepository private constructor(context: Context) {

    private val db = GuestsDatabase.getDatabase(context)
    private val guestDAO = db.guestDao()

    companion object {
        private lateinit var repository: GuestLocalRepository

        fun getInstance(context: Context): GuestLocalRepository {
            if (!::repository.isInitialized) {
                repository = GuestLocalRepository(context)
            }

            return repository
        }
    }

    fun insert(guest: Guest) {
        guestDAO.insert(guest)
    }

    fun getGuests(statusFilter: GuestStatus?): List<Guest> {
        return if (statusFilter == null) {
            guestDAO.getAll()
        } else {
            guestDAO.getByGuestStatus(statusFilter.toString())
        }
    }

    fun delete(guest: Guest) {
        guestDAO.delete(guest)
    }

    fun getById(id: Int): Guest {
        return guestDAO.findById(id)
    }

    fun update(guest: Guest) {
        guestDAO.update(guest)
    }
}