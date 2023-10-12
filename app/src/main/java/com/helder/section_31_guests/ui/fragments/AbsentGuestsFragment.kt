package com.helder.section_31_guests.ui.fragments

class AbsentGuestsFragment : BaseFragment() {

    override fun getGuests() {
        viewModel.getAbsent()
    }
}