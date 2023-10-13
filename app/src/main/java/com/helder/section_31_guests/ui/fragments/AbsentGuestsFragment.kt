package com.helder.section_31_guests.ui.fragments

class AbsentGuestsFragment : BaseFragment() {

    override fun getGuests() {
        try {
            viewModel.getAbsent()
        } catch (e: Exception) {
            showActionMessageService.showExceptionMessage(requireContext(), e.toString())
        }
    }
}