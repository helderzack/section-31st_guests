package com.helder.section_31_guests.data.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.helder.section_31_guests.data.model.GuestModel
import com.helder.section_31_guests.data.model.GuestStatus

class GuestLocalRepository private constructor(context: Context) {
    private val dbHelper = GuestDBHelper(context)
    private val db = dbHelper.writableDatabase

    companion object {
        private lateinit var repository: GuestLocalRepository

        fun getInstance(context: Context): GuestLocalRepository {
            if (!::repository.isInitialized) {
                repository = GuestLocalRepository(context)
            }

            return repository
        }
    }

    fun insert(guest: GuestModel): Int {
        val values = ContentValues().apply {
            put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            put(DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS, guest.guestStatus.toString())
        }

        return db.insert(
            DatabaseConstants.TABLE_NAME,
            null,
            values
        ).toInt()
    }

    @SuppressLint("Range")
    fun getGuests(statusFilter: GuestStatus?): List<GuestModel> {
        val guests: MutableList<GuestModel> = mutableListOf()

        val selection =
            if (statusFilter == null) null else "${DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS} = ?"
        val selectionArgs = if (statusFilter == null) null else arrayOf(statusFilter.toString())

        val cursor = db.query(
            DatabaseConstants.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val guestId = getInt(getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                val guestName = getString(getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                val guestStatus =
                    getString(getColumnIndex(DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS))
                guests.add(GuestModel(guestId, guestName, GuestStatus.valueOf(guestStatus)))
            }
        }

        cursor.close()

        return guests
    }

    fun delete(id: Int): Int {
        return db.delete(
            DatabaseConstants.TABLE_NAME,
            "${DatabaseConstants.GUEST.COLUMNS.ID} LIKE ?",
            arrayOf(id.toString())
        )
    }

    @SuppressLint("Range")
    fun getById(id: Int): GuestModel? {
        val selection = "${DatabaseConstants.GUEST.COLUMNS.ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())

        val cursor = db.query(
            DatabaseConstants.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var guest: GuestModel? = null

        with(cursor) {
            while(moveToNext()) {
                guest = GuestModel(
                    id,
                    getString(getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME)),
                    GuestStatus.valueOf(getString(getColumnIndex(DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS)))
                )
            }
        }

        cursor.close()
        return guest
    }

    fun update(guest: GuestModel): Int {
        val values = ContentValues().apply {
            put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            put(DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS, guest.guestStatus.toString())
        }

        return db.update(
            DatabaseConstants.TABLE_NAME,
            values,
            "${DatabaseConstants.GUEST.COLUMNS.ID} LIKE ?",
            arrayOf(guest.id.toString())
        )
    }
}