package com.amoon.doctorlist.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.amoon.doctorlist.BaseActivity
import com.amoon.doctorlist.R
import com.amoon.doctorlist.data.model.DoctorDetails
import com.amoon.doctorlist.databinding.ActivitySearchBinding
import com.amoon.doctorlist.ui.detail.DetailFragment
import com.amoon.doctorlist.ui.main.MainAdapter
import com.amoon.doctorlist.util.Storage
import kotlinx.android.synthetic.main.activity_search.*

class MainActivity : BaseActivity<ActivitySearchBinding, MainViewModel>(), MainAdapter.OnClickListener {

    private lateinit var imageAdapter: MainAdapter
    override fun getModelClass(): Class<MainViewModel> = MainViewModel::class.java
    override fun getLayoutRes(): Int = R.layout.activity_search
    private lateinit var liveData: MutableLiveData<List<DoctorDetails>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchKeyWord()
        imageAdapter = MainAdapter(this , liveData.value!!)

        viewModel.run {
            pages.observe(this@MainActivity, Observer {
                imageAdapter.submitList(it)
                binding.isLoading = true
            })
            networkState.observe(this@MainActivity, Observer {
                binding.errorText.text =  it.msg
                binding.isFailed = true
            })
            seenResult.observe(this@MainActivity, Observer {
                imageAdapter.updateData(liveData.value!!)
            })
        }

        setupRecycler()
        viewModel.listDoctors()
    }

    private fun setupRecycler() {
        with(imageRecycler) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = imageAdapter
        }
        return
    }


    private fun searchKeyWord() {
        viewModel.getSeenDoctors(this)
        liveData = viewModel.seenResult
        observeSeenDocs()
    }

    private fun observeSeenDocs(){
        //observe seen doctors livedata
        liveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                 it?.let  { it1 ->
                    MainAdapter(this,it1)
                }
                imageAdapter?.updateData(it)
            }
        })

    }

    override fun onItemClick(doctorDetails: DoctorDetails) {
        Log.d("mehr", "name is " + doctorDetails.name)
        supportFragmentManager
            .beginTransaction().setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .add(
                R.id.mainActivityRoot, DetailFragment.newInstance(
                    doctorDetails.name,
                    doctorDetails.address,
                    doctorDetails.phoneNumber,
                    doctorDetails.photoId
                ), "DoctorsList"
            )
            .addToBackStack(DetailFragment::class.java.name).commit()

        Storage.DataProviderManager.checkList(this, doctorDetails)
        searchKeyWord()
    }

}
