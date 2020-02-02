package com.amoon.doctorlist.di.module

import com.amoon.doctorlist.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun bindMainActivity() : MainActivity


}