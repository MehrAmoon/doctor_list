package com.amoon.doctorlist.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.amoon.doctorlist.data.ApiService
import com.amoon.doctorlist.util.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppNetworkModule.ProvideModule::class])
interface AppNetworkModule {


    @Module
    class ProvideModule {

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val loggingIntercepter = HttpLoggingInterceptor()
            loggingIntercepter.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor { chain ->
                val original = chain.request()

                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }

            return OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggingIntercepter)
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        }

        @Singleton
        @Provides
        fun retrofit(okHttpClient: OkHttpClient): Retrofit
                = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        @Singleton
        @Provides
        fun provideAppService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    }
}