package com.amoon.doctorlist.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amoon.doctorlist.BaseViewModelFactory
import com.amoon.doctorlist.di.key.ViewModelKey
import com.amoon.doctorlist.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: BaseViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(searchViewModel: MainViewModel): ViewModel


}