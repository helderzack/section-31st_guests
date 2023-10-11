package com.helder.section_31_guests.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.helder.section_31_guests.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_NAME)
data class Guest(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = DatabaseConstants.GUEST.COLUMNS.NAME) var name: String,
    @ColumnInfo(name = DatabaseConstants.GUEST.COLUMNS.GUEST_STATUS) var guestStatus: GuestStatus
)