package com.helder.section_31_guests.util

class UtilMethods private constructor() {
    companion object {
        private var instance: UtilMethods? = null

        fun getInstance(): UtilMethods {
            if (instance == null) {
                instance = UtilMethods()
            }

            return instance!!
        }
    }

    fun generateRandomString(): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..UtilConstants.RANDOM_STRING_LENGTH)
            .map { charset.random() }
            .joinToString("")
    }

    private object UtilConstants {
        const val RANDOM_STRING_LENGTH = 15
    }
}