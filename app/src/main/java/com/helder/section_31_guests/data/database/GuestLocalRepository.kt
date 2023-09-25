package com.helder.section_31_guests.data.database

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus

class GuestLocalRepository(private val context: Context) {
    private val dbHelper = GuestDBHelper(context)
    private val db = dbHelper.writableDatabase

    fun save(guest: Guest) {
        val values = ContentValues().apply {
            put(GuestDBHelper.GuestEntry.COLUMN_NAME_GUEST_ID, guest.guestId)
            put(GuestDBHelper.GuestEntry.COLUMN_NAME_GUEST_NAME, guest.name)
            put(GuestDBHelper.GuestEntry.COLUMN_NAME_GUEST_STATUS, guest.guestStatus.toString())
        }

        val queryResult = db.insert(GuestDBHelper.GuestEntry.TABLE_NAME, null, values)

        if (queryResult.toInt() == -1) {
            showToast(context.getString(R.string.failed_saved_guest_action))
        } else {
           showToast(context.getString(R.string.successful_saved_guest_action))
        }
    }

    fun getGuests(): List<Guest> {
        val guests: MutableList<Guest> = mutableListOf()

        val cursor = db.query(
            GuestDBHelper.GuestEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val guestId = getString(0)
                val guestName = getString(1)
                val guestStatus = getString(2)

                val guest = Guest(guestId, guestName, GuestStatus.valueOf(guestStatus))
                guests.add(guest)
            }

            close()
        }

        return guests
    }

    fun delete(guestId: String) {
        val deletedRows = db.delete(
            GuestDBHelper.GuestEntry.TABLE_NAME,
            "${GuestDBHelper.GuestEntry.COLUMN_NAME_GUEST_ID} LIKE ?",
            arrayOf(guestId)
        )

        if (deletedRows < 1) {
            showToast(context.getString(R.string.failed_delete_action_no_row_deleted))
        }

        if (deletedRows > 1) {
            showToast(context.getString(R.string.failed_delete_action_more_than_one_row_deleted))
        }
    }

    fun update(guest: Guest) {
        val values = ContentValues().apply {
            put(GuestDBHelper.GuestEntry.COLUMN_NAME_GUEST_NAME, guest.name)
            put(GuestDBHelper.GuestEntry.COLUMN_NAME_GUEST_STATUS, guest.guestStatus.toString())
        }

        val updatedRows = db.update(
            GuestDBHelper.GuestEntry.TABLE_NAME,
            values,
            "${GuestDBHelper.GuestEntry.COLUMN_NAME_GUEST_ID} LIKE ?",
            arrayOf(guest.guestId)
        )

        if (updatedRows < 1) {
            showToast(context.getString(R.string.failed_delete_action_no_row_updated))
        }

        if (updatedRows > 1) {
            showToast(context.getString(R.string.failed_delete_action_more_than_one_row_updated))
        }
    }

    private fun showToast(toastMessages: String) {
        Toast.makeText(
            context,
            toastMessages,
            Toast.LENGTH_SHORT
        ).show()
    }
}