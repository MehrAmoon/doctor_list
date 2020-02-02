package com.amoon.doctorlist.di

import com.amoon.doctorlist.ui.MainActivity
import com.amoon.doctorlist.di.module.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}
