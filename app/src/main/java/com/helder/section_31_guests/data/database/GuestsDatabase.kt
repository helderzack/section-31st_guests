package com.helder.section_31_guests.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.helder.section_31_guests.constants.DatabaseConstants
import com.helder.section_31_guests.data.database.dao.GuestDAO
import com.helder.section_31_guests.data.model.Guest

@Database(entities = [Guest::class], version = DatabaseConstants.DATABASE_VERSION)
abstract class GuestsDatabase : RoomDatabase() {
    companion object {
        private lateinit var database: GuestsDatabase

        fun getDatabase(context: Context): GuestsDatabase {
            if (!::database.isInitialized) {
                synchronized(GuestsDatabase::class) {
                    database = Room.databaseBuilder(
                        context,
                        GuestsDatabase::class.java,
                        DatabaseConstants.DATABASE_NAME
                    ).build()
                }
            }
            return database
        }
    }

    abstract fun guestDao(): GuestDAO
}