package com.helder.section_31_guests.service

import android.content.Context
import android.widget.Toast
import com.helder.section_31_guests.R

class ShowActionMessageService private constructor() {
    companion object {
        private lateinit var instance: ShowActionMessageService

        fun getInstance(): ShowActionMessageService {
            if (!::instance.isInitialized) {
                instance = ShowActionMessageService()
            }

            return instance
        }
    }

    fun showBlankGuestNameAlertMessage(context: Context) {
        showToast(context, context.getString(R.string.blank_guest_name_alert))
    }

    fun showInsertMessage(context: Context, insertReturn: Int) {
        if (insertReturn == -1) {
            showToast(context, context.getString(R.string.failed_saved_guest_action))
        } else {
            showToast(context, context.getString(R.string.successful_saved_guest_action))
        }
    }

    fun showUpdateMessage(context: Context, updatedRows: Int) {
        if (updatedRows < 1) {
            showToast(
                context,
                context.getString(R.string.failed_update_action_no_row_updated)
            )
        } else if (updatedRows > 1) {
            showToast(
                context,
                context.getString(R.string.failed_update_action_more_than_one_row_updated)
            )
        } else {
            showToast(context, context.getString(R.string.successful_update_action))
        }
    }

    fun showDeleteMessage(context: Context, deletedRows: Int) {
        if (deletedRows < 1) {
            showToast(
                context,
                context.getString(R.string.failed_delete_action_no_row_deleted)
            )
        } else if (deletedRows > 1) {
            showToast(
                context,
                context.getString(R.string.failed_delete_action_more_than_one_row_deleted)
            )
        } else {
            showToast(
                context,
                context.getString(R.string.successful_delete_action)
            )
        }
    }

    fun showExceptionMessage(context: Context, exception: String) {
        showToast(context, exception)
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}