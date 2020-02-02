package com.amoon.doctorlist

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel>: HasSupportFragmentInjector, AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: VB
    lateinit var viewModel: VM

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>


    private var lastClickTime = 0L
    private val disposables by lazy {
        CompositeDisposable()
    }

    @LayoutRes
    protected abstract fun getLayoutRes(): Int
    protected abstract fun getModelClass(): Class<VM>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getModelClass())

        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.lifecycleOwner = this
        binding.setVariable(BR.view, this)
        binding.setVariable(BR.viewModel, viewModel)

    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}