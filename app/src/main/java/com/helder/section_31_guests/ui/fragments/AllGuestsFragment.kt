package com.helder.section_31_guests.ui.fragments

class AllGuestsFragment : BaseFragment() {

    override fun getGuests() {
        try {
            viewModel.getAll()
        } catch (e: Exception) {
            showActionMessageService.showExceptionMessage(requireContext(), e.toString())
        }
    }
}