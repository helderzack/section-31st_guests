package com.helder.section_31_guests.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.Guest

class GuestsAdapter(private val guests: List<Guest>) :
    RecyclerView.Adapter<GuestsAdapter.GuestsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guest_item, parent, false)
        return GuestsViewHolder(view)
    }

    override fun getItemCount(): Int = guests.size

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guests[position])
    }

    class GuestsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val guestName: TextView

        init {
            guestName = view.findViewById(R.id.text_view_guest_name)
        }

        fun bind(guest: Guest) {
            guestName.text = guest.name
        }

    }
}