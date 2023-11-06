package com.helder.section_31_guests.data.database

import android.content.Context
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import kotlinx.coroutines.flow.Flow

class GuestRepository(context: Context) {
    private val db = GuestsDatabase.getDatabase(context).guestDao()

    fun getAll(): Flow<List<Guest>> {
        return db.getAll()
    }

    fun getById(id: Int): Flow<Guest> {
        return db.getById(id)
    }

    fun getPresent(): Flow<List<Guest>> {
        return db.getByGuestStatus(GuestStatus.Present.toString())
    }

    fun getAbsent(): Flow<List<Guest>> {
        return db.getByGuestStatus(GuestStatus.Absent.toString())
    }

    fun insert(guest: Guest): Long {
        return db.insert(guest)
    }

    fun update(guest: Guest): Int {
        return db.update(guest)
    }

    fun delete(guest: Guest): Int {
        return db.delete(guest)
    }
}