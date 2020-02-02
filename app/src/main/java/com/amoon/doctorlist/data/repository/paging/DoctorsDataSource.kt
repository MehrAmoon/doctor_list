package com.amoon.doctorlist.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.amoon.doctorlist.data.ApiService
import com.amoon.doctorlist.data.model.DoctorDetails
import com.amoon.doctorlist.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class DoctorsDataSource(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<String, DoctorDetails>() {

    val networkError = MutableLiveData<NetworkState>()
    var keyword = ""
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, DoctorDetails>) {
        apiService
            .getDoctorsList(keyword)
            ?.subscribe({
                callback.onResult(it.doctors.sortedWith(compareBy { it.rating }), null, it.lastKey)
                keyword = "-" + it.lastKey
            }, {
                networkError.postValue(NetworkState.error(it.message))
            })
            ?.addTo(disposables)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, DoctorDetails>) {
        apiService
            .getDoctorsList(keyword)
            ?.subscribe({
                callback.onResult(it.doctors.sortedWith(compareBy { it.rating }), it.lastKey)
                keyword = "-" + it.lastKey
            }, {
                networkError.postValue(NetworkState.error(it.message))
            })
            ?.addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, DoctorDetails>) {}


}