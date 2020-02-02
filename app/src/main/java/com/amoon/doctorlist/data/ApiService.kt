package com.amoon.doctorlist.data

import com.amoon.doctorlist.data.model.Doctors
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("doctors{subreddit}.json")
    fun getDoctorsList(
        @Path("subreddit") subreddit: String
    ): Single<Doctors>?
}