package com.helder.section_31_guests.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.GuestModel
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.ActivityRegisterGuestBinding
import com.helder.section_31_guests.ui.viewmodel.RegisterGuestViewModel

class RegisterGuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterGuestBinding
    private lateinit var viewModel: RegisterGuestViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewModel =
                ViewModelProvider(this@RegisterGuestActivity)[RegisterGuestViewModel::class.java]

            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            buttonSaveGuest.setOnClickListener {
                saveGuest()
            }

            observe()

            loadData()

            radioButtonStatusPresent.isChecked = true

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        finish()
        return super.onSupportNavigateUp()
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            guestId = bundle.getInt(getString(R.string.guest_extra))
            viewModel.getById(guestId)
        }
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editGuestName.setText(it.name)
            when (it.guestStatus) {
                GuestStatus.Present -> {
                    binding.radioButtonStatusPresent.isChecked = true
                }

                GuestStatus.Absent -> {
                    binding.radioButtonStatusAbsent.isChecked = true
                }
            }
        }
    }

    private fun saveGuest() {
        val guestName = binding.editGuestName.text.toString()

        if (guestName.isBlank() || guestName.isEmpty()) {
            Toast.makeText(
                applicationContext,
                getString(R.string.blank_guest_name_alert),
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

        if(guestId == 0) {
            showInsertMessage(viewModel.save(GuestModel(guestId, guestName, guestStatus)))
        } else {
            showUpdateMessage(viewModel.update(GuestModel(guestId, guestName, guestStatus)))
        }

        supportFragmentManager.popBackStack()
        finish()
    }

    private fun showInsertMessage(queryResult: Int) {
        if (queryResult == -1) {
            showToast(getString(R.string.failed_saved_guest_action))
        } else {
            showToast(getString(R.string.successful_saved_guest_action))
        }
    }

    private fun showUpdateMessage(updatedRows: Int) {
        if (updatedRows < 1) {
            showToast(getString(R.string.failed_update_action_no_row_updated))
        } else if (updatedRows > 1) {
            showToast(getString(R.string.failed_update_action_more_than_one_row_updated))
        } else {
            showToast(getString(R.string.successful_update_action))
        }
    }

    private fun showToast(toastMessages: String) {
        Toast.makeText(
            this,
            toastMessages,
            Toast.LENGTH_SHORT
        ).show()
    }
}