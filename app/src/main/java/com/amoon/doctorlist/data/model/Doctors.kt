package com.amoon.doctorlist.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



data class Doctors(@SerializedName("lastKey") val lastKey :String,
                   @SerializedName("doctors") val doctors :List<DoctorDetails>)

