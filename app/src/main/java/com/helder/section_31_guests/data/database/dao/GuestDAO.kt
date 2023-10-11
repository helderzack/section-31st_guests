package com.helder.section_31_guests.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.helder.section_31_guests.constants.DatabaseConstants
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

@Dao
interface GuestDAO {

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_NAME}")
    fun getAll(): List<Guest>

    @Query("SELECT * FROM Guest WHERE ${DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS} = (:guestStatus)")
    fun getByGuestStatus(guestStatus: String): List<Guest>

    @Query("SELECT * FROM Guest WHERE ${DatabaseConstants.GUEST.COLUMNS.ID} = (:id)")
    fun findById(id: Int): Guest
    @Insert
    fun insert(guest: Guest)

    @Update
    fun update(guest: Guest)

    @Delete
    fun delete(guest: Guest)
}