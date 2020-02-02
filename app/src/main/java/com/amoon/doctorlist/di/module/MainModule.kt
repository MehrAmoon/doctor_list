package com.amoon.doctorlist.di.module

import androidx.lifecycle.ViewModel
import com.amoon.doctorlist.di.key.ViewModelKey
import com.amoon.doctorlist.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsViewModel(viewModel: MainViewModel): ViewModel
}
