package com.helder.section_31_guests.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.GuestsMockRepository
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.ActivityRegisterGuestBinding

class RegisterGuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterGuestBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            radioButtonStatusPresent.isChecked = true

            buttonSaveGuest.setOnClickListener {
                saveGuest()
            }
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

        GuestsMockRepository.getInstance().addGuest(Guest(guestName, guestStatus))
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}