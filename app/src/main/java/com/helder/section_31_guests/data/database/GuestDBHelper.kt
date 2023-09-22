package com.helder.section_31_guests.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class GuestDBHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        GuestEntry.DATABASE_NAME,
        null,
        GuestEntry.DATABASE_VERSION
    ) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(GuestQueries.SQl_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(GuestQueries.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    object GuestEntry : BaseColumns {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Guest.db"
        const val TABLE_NAME = "guest"
        const val COLUMN_NAME_GUEST_ID = "guest_id"
        const val COLUMN_NAME_GUEST_NAME = "name"
        const val COLUMN_NAME_GUEST_STATUS = "guest_status"
    }

    object GuestQueries {
        const val SQl_CREATE_ENTRIES =
            "CREATE TABLE ${GuestEntry.TABLE_NAME} " +
                    "(${GuestEntry.COLUMN_NAME_GUEST_ID} TEXT PRIMARY KEY, " +
                    "${GuestEntry.COLUMN_NAME_GUEST_NAME} TEXT, " +
                    "${GuestEntry.COLUMN_NAME_GUEST_STATUS} TEXT);"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${GuestEntry.TABLE_NAME}"
    }
}