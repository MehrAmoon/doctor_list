package com.amoon.doctorlist.di.module


import com.amoon.doctorlist.ui.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeGitDetailFragment(): DetailFragment
}
