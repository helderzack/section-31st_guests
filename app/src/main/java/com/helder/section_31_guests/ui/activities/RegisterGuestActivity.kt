package com.helder.section_31_guests.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.ActivityRegisterGuestBinding
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel
import com.helder.section_31_guests.util.UtilMethods

class RegisterGuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterGuestBinding
    private lateinit var guestLocalRepository: GuestLocalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterGuestBinding.inflate(layoutInflater)
        guestLocalRepository = GuestLocalRepository(applicationContext)

        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            radioButtonStatusPresent.isChecked = true

            buttonSaveGuest.setOnClickListener {
                saveGuest()
            }

            setContentView(root)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return super.onSupportNavigateUp()
    }

    private fun saveGuest() {
        val guestName = binding.editGuestName.text.toString()
        if (guestName.isBlank() || guestName.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Please enter the guest name!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val checkedGuestStatus = binding.radioGroupGuestStatus.checkedRadioButtonId

        val guestStatus = if (checkedGuestStatus == R.id.radio_button_status_present) {
            GuestStatus.Present
        } else {
            GuestStatus.Absent
        }

        val guestId = UtilMethods.getInstance().generateRandomString()
        GuestsViewModel.getInstance(null).saveGuest(Guest(guestId, guestName, guestStatus))

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}