package com.helder.section_31_guests.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.helder.section_31_guests.constants.DatabaseConstants

class GuestDBHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        DatabaseConstants.DATABASE_NAME,
        null,
        DatabaseConstants.DATABASE_VERSION
    ) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE ${DatabaseConstants.TABLE_NAME} " +
                    "(${DatabaseConstants.GUEST.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${DatabaseConstants.GUEST.COLUMNS.NAME} TEXT, " +
                    "${DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS} TEXT);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DatabaseConstants.TABLE_NAME}")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}