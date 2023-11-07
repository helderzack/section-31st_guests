package com.helder.section_31_guests.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.helder.section_31_guests.R
import com.helder.section_31_guests.constants.GuestConstants
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.data.model.GuestStatus
import com.helder.section_31_guests.databinding.ActivityRegisterGuestBinding
import com.helder.section_31_guests.service.ShowActionMessageService
import com.helder.section_31_guests.ui.viewmodel.RegisterGuestViewModel
import kotlinx.coroutines.launch

class RegisterGuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterGuestBinding
    private lateinit var viewModel: RegisterGuestViewModel
    private var guestId = 0
    private val showActionMessageService = ShowActionMessageService.getInstance()

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
            guestId = bundle.getInt(GuestConstants.GUEST.ID)
            try {
                viewModel.getById(guestId)
            } catch (e: Exception) {
                showActionMessageService.showExceptionMessage(this, e.toString())
            }
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.guest.collect {
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
        }
    }

    private fun saveGuest() {
        val guestName = binding.editGuestName.text.toString()

        if (guestName.isBlank() || guestName.isEmpty()) {
            showActionMessageService.showBlankGuestNameAlertMessage(this)
            return
        }

        val checkedGuestStatus = binding.radioGroupGuestStatus.checkedRadioButtonId

        val guestStatus = if (checkedGuestStatus == R.id.radio_button_status_present) {
            GuestStatus.Present
        } else {
            GuestStatus.Absent
        }

        if (guestId == 0) {
            try {
                lifecycleScope.launch {
                    viewModel.save(Guest(guestId, guestName, guestStatus))
                    showActionMessageService.showInsertMessage(
                        this@RegisterGuestActivity,
                        viewModel.channel.receive()
                    )
                    viewModel.channel.close()
                }
            } catch (e: Exception) {
                showActionMessageService.showExceptionMessage(this, e.toString())
            }
        } else {
            try {
                lifecycleScope.launch {
                    viewModel.update(Guest(guestId, guestName, guestStatus))
                    showActionMessageService.showUpdateMessage(
                        this@RegisterGuestActivity,
                        viewModel.channel.receive()
                    )
                    viewModel.channel.close()
                }
            } catch (e: Exception) {
                showActionMessageService.showExceptionMessage(this, e.toString())
            }
        }

        supportFragmentManager.popBackStack()
        finish()
    }
}