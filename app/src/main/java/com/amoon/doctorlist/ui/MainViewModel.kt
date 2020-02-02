package com.amoon.doctorlist.ui

import android.content.Context
import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.amoon.doctorlist.data.model.DoctorDetails
import com.amoon.doctorlist.data.repository.AppRepository
import com.amoon.doctorlist.data.repository.Listing
import com.amoon.doctorlist.util.Storage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: AppRepository,
    private val disposables: CompositeDisposable
) : ViewModel() {

    private val pageResult = MutableLiveData<Listing<DoctorDetails>>()
    private val query = PublishSubject.create<String>()
    var seenResult = MutableLiveData<List<DoctorDetails>>()

    val pages = switchMap(pageResult) { it.pages }
    val networkState = switchMap(pageResult) { it.networkState }


    init {
        query
            .debounce(1000, TimeUnit.MILLISECONDS)
            .flatMapSingle { repository.getDoctorsList() }
            .subscribe(pageResult::postValue)
            .addTo(disposables)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


    fun listDoctors() {
        query.onNext("")
    }

    fun getSeenDoctors(context: Context) {
        seenResult.value = Storage.DataProviderManager.getArrayList(context)
    }

}