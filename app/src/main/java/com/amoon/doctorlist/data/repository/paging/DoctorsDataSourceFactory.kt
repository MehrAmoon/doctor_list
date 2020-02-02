package com.amoon.doctorlist.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.amoon.doctorlist.data.ApiService
import com.amoon.doctorlist.data.model.DoctorDetails
import io.reactivex.disposables.CompositeDisposable

class DoctorsDataSourceFactory(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : DataSource.Factory<String, DoctorDetails>() {

    val sourceLiveData = MutableLiveData<DoctorsDataSource>()

    override fun create(): DataSource<String, DoctorDetails> {
        val source = DoctorsDataSource( apiService, disposables)
        sourceLiveData.postValue(source)
        return source
    }
}