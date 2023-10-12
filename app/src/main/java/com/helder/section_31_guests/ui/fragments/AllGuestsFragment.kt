package com.helder.section_31_guests.ui.fragments

class AllGuestsFragment : BaseFragment() {

    override fun getGuests() {
        viewModel.getAll()
    }
}