package com.helder.section_31_guests.ui.fragments

class PresentGuestsFragment : BaseFragment() {

    override fun getGuests() {
        viewModel.getPresent()
    }
}