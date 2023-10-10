package com.helder.section_31_guests.constants

class DatabaseConstants {
    companion object GUEST {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Guest.db"
        const val TABLE_NAME = "guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val GUEST_STATUS = "guest_status"
        }
    }
}