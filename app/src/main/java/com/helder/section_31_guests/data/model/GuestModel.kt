package com.helder.section_31_guests.data.model

import android.os.Parcel
import android.os.Parcelable

data class GuestModel(
    val id: Int,
    var name: String,
    var guestStatus: GuestStatus) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        GuestStatus.valueOf(parcel.readString()!!)
    )

    companion object CREATOR : Parcelable.Creator<GuestModel> {
        override fun createFromParcel(parcel: Parcel): GuestModel {
            return GuestModel(parcel)
        }

        override fun newArray(size: Int): Array<GuestModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(guestStatus.toString())
    }
}