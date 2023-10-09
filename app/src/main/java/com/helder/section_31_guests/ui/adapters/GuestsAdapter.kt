package com.helder.section_31_guests.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helder.section_31_guests.data.model.GuestModel
import com.helder.section_31_guests.databinding.GuestItemBinding
import com.helder.section_31_guests.ui.listener.OnGuestListener

class GuestsAdapter :
    RecyclerView.Adapter<GuestViewHolder>() {
    private lateinit var binding: GuestItemBinding
    private var guests: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        binding = GuestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = guests.size

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val guest = guests[position]
        holder.bind(guest)
    }

    fun updateGuests(guests: List<GuestModel>) {
        this.guests = guests
        notifyDataSetChanged()
    }

    fun attachListener(listener: OnGuestListener) {
        this.listener = listener
    }
}