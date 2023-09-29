package com.helder.section_31_guests.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.helder.section_31_guests.data.database.GuestLocalRepository
import com.helder.section_31_guests.databinding.FragmentAllGuestsBinding
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.adapters.GuestsAdapter
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModelFactory
import com.helder.section_31_guests.util.CallingFragmentEnum
import com.helder.section_31_guests.util.UtilMethods

class AllGuestsFragment : Fragment() {
    private lateinit var binding: FragmentAllGuestsBinding
    private lateinit var viewModel: GuestsViewModel
    private lateinit var viewModelFactory: GuestsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        with(binding) {
            recyclerViewAllGuests.layoutManager = LinearLayoutManager(requireContext())

            viewModelFactory = GuestsViewModelFactory(GuestLocalRepository(requireContext()))
            viewModel =
                ViewModelProvider(requireActivity(), viewModelFactory)[GuestsViewModel::class.java]

            viewModel.getObservable().observe(viewLifecycleOwner) {
                Log.d(
                    UtilMethods.getInstance().getLogTag(),
                    "getObservable() on ${this.javaClass.simpleName}"
                )

                recyclerViewAllGuests.adapter = GuestsAdapter(it, requireContext())

                if (it.isEmpty()) {
                    textEmptyGuestsList.visibility = View.VISIBLE
                } else {
                    textEmptyGuestsList.visibility = View.INVISIBLE
                }
            }

            floatingButtonAddGuest.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(
                    UtilMethods.getInstance().getCallingFragmentExtra(),
                    CallingFragmentEnum.ALL_GUESTS_FRAGMENT.toString()
                )
                val intent = Intent(requireContext(), RegisterGuestActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            return root
        }
    }
}