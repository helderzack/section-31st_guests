package com.helder.section_31_guests.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.helder.section_31_guests.constants.DatabaseConstants
import com.helder.section_31_guests.data.model.Guest
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDAO {

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_NAME}")
    fun getAll(): Flow<List<Guest>>

    @Query("SELECT * FROM Guest WHERE ${DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS} = (:guestStatus)")
    fun getByGuestStatus(guestStatus: String): Flow<List<Guest>>

    @Query("SELECT * FROM Guest WHERE ${DatabaseConstants.GUEST.COLUMNS.ID} = (:id)")
    fun getById(id: Int): Flow<Guest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(guest: Guest): Long

    @Update
    fun update(guest: Guest): Int

    @Delete
    fun delete(guest: Guest): Int
}