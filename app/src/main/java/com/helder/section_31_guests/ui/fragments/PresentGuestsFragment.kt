package com.helder.section_31_guests.ui.fragments

class PresentGuestsFragment : BaseFragment() {

    override fun getGuests() {
        try {
            viewModel.getPresent()
        } catch (e: Exception) {
            showActionMessageService.showExceptionMessage(requireContext(), e.toString())
        }
    }
}