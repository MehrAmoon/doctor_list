package com.amoon.doctorlist.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue



data class DoctorDetails(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("photoId") val photoId: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("address") val address: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double,
    @SerializedName("highlighted") val highlighted: Boolean,
    @SerializedName("reviewCount") val reviewCount: Int,
    @SerializedName("specialityIds") val specialityIds: List<Int?>,
    @SerializedName("source") val source: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("email") val email: @RawValue Any,
    @SerializedName("website") val website: String,
    @SerializedName("openingHours") val openingHours: List<String?>,
    @SerializedName("integration") val integration: @RawValue Any
)


