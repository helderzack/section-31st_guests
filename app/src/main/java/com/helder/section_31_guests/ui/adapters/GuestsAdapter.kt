package com.helder.section_31_guests.ui.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helder.section_31_guests.R
import com.helder.section_31_guests.data.model.Guest
import com.helder.section_31_guests.ui.activities.RegisterGuestActivity
import com.helder.section_31_guests.ui.fragments.viewmodels.GuestsViewModel
import com.helder.section_31_guests.util.UtilMethods

class GuestsAdapter(private val guests: List<Guest>, private val context: Context) :
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
            val bundle = Bundle()
            bundle.putParcelable(UtilMethods.getInstance().getGuestExtra(), guest)
            val intent = Intent(context, RegisterGuestActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        holder.imageDeleteGuest.setOnClickListener {
            val builder = AlertDialog.Builder(context)

            builder.setMessage(context.getString(R.string.remove_guest_dialog_title))
                .setPositiveButton(
                    context.getString(R.string.positive_remove_guest_dialog_response)
                ) { _, _ ->
                    GuestsViewModel.getInstance(null).deleteGuest(guest.guestId)
                }
                .setNegativeButton(context.getString(R.string.negative_remove_guest_dialog_response)) { _, _ -> }
                .setCancelable(false)

            val dialog = builder.create()
            dialog.show()
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