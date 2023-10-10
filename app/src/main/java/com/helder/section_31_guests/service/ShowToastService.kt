package com.helder.section_31_guests.service

import android.content.Context
import android.widget.Toast

class ShowToastService private constructor() {
    companion object {
        private lateinit var instance: ShowToastService

        fun getInstance(): ShowToastService {
            if(!::instance.isInitialized) {
                instance = ShowToastService()
            }

            return instance
        }
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}