package com.amoon.doctorlist

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.amoon.doctorlist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector {

    companion object {
        lateinit var instance: App
            private set
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun onCreate() {
        super.onCreate()
        instance = this

        setupStetho()
        setupDagger()
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupDagger() {
        DaggerAppComponent
            .builder()
            .create(this)
            .inject(this)
    }
}