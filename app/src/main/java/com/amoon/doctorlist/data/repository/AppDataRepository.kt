package com.amoon.doctorlist.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.amoon.doctorlist.data.ApiService
import com.amoon.doctorlist.data.model.DoctorDetails
import com.amoon.doctorlist.data.repository.paging.DoctorsDataSourceFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
): AppRepository {


    override fun getDoctorsList(): Single<Listing<DoctorDetails>> {
        val dataSourceFactory = DoctorsDataSourceFactory( apiService, disposables)


        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        val networkError = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.networkError
        }


        return Single.just(Listing(
            LivePagedListBuilder(dataSourceFactory, config).build(),
            networkError
        ))
    }

}