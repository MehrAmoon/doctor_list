package com.amoon.doctorlist.data.repository

import com.amoon.doctorlist.data.model.DoctorDetails
import io.reactivex.Single

interface AppRepository {

    fun getDoctorsList(): Single<Listing<DoctorDetails>>
}