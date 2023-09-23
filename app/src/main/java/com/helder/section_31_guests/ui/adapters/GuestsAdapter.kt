package com.helder.section_31_guests.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel

class GuestsAdapter(private val guests: List<Guest>) :
    RecyclerView.Adapter<GuestsAdapter.GuestsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guest_item, parent, false)
        return GuestsViewHolder(view)
    }

    override fun getItemCount(): Int = guests.size

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        val guest = guests[position]

        holder.bind(guest)

        holder.guestName.setOnClickListener {
            Log.d("onBindViewHolder()", "${guest.name} clicked!!!")
        }

        holder.imageDeleteGuest.setOnClickListener {
            GuestsViewModel.getInstance(null).deleteGuest(guest.guestId)
        }
    }

    class GuestsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val guestName: TextView
        val imageDeleteGuest: ImageView

        init {
            guestName = view.findViewById(R.id.text_view_guest_name)
            imageDeleteGuest = view.findViewById(R.id.image_delete_guest)
        }

        fun bind(guest: Guest) {
            guestName.text = guest.name
        }

    }
}