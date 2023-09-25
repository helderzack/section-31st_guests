package com.helder.section_31_guests.data.model

import android.os.Parcel
import android.os.Parcelable

data class Guest(
    val guestId: String,
    var name: String,
    var guestStatus: GuestStatus) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        GuestStatus.valueOf(parcel.readString()!!)
    )

    companion object CREATOR : Parcelable.Creator<Guest> {
        override fun createFromParcel(parcel: Parcel): Guest {
            return Guest(parcel)
        }

        override fun newArray(size: Int): Array<Guest?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(guestId)
        parcel.writeString(name)
        parcel.writeString(guestStatus.toString())
    }
}