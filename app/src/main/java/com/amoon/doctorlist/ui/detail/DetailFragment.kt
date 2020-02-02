package com.amoon.doctorlist.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.amoon.doctorlist.R
import com.amoon.doctorlist.databinding.GitDetailBinding
import kotlinx.android.synthetic.main.git_detail.*
import javax.inject.Inject


class DetailFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: GitDetailBinding


    companion object {

        private const val Name = "Name"
        private const val Adress = "Adress"
        private const val PhoneNumber = "PhoneNumber"
        private const val Photo = "Photo"

        //new instance for showing doctor details
        fun newInstance(name: String, adress: String, phoneNumber: String, photo: String?) = DetailFragment().apply {
            arguments = Bundle(4).apply {

                putString(Name, name)
                putString(Adress, adress)
                putString(PhoneNumber, phoneNumber)
                putString(Photo, photo)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.git_detail, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDetails()
    }


    fun getDetails() {
//        observe data from livedata and show on ui
        if (arguments?.getString(Photo) != null)
            Glide.with(this).load(arguments?.getString(Photo)).into(image)
        repoNameText.text = arguments?.getString(Name)
        subscribersCounts.text = arguments?.getString(Adress)
        phoneNumber.text = arguments?.getString(PhoneNumber)
    }
}