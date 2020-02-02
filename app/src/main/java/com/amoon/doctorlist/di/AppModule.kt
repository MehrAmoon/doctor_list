package com.amoon.doctorlist.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.amoon.doctorlist.BaseViewModelFactory
import com.amoon.doctorlist.data.repository.AppDataRepository
import com.amoon.doctorlist.data.repository.AppRepository
import com.amoon.doctorlist.di.qualifier.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
interface AppModule {
    @Module
    class ProvideModule {

        @Singleton
        @Provides
        @App
        fun provideContext(): Context = com.amoon.doctorlist.App.instance

        @Provides
        @Singleton
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }

    @Binds
    @Singleton
    fun bindsAppRepository(repository: AppDataRepository): AppRepository


    @Binds
    fun bindsViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory
}