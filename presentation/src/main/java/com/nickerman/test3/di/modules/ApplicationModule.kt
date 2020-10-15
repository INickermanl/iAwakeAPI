package com.nickerman.test3.di.modules

import android.content.Context
import com.example.data.services.MediaService
import com.example.domain.ErrorHandler
import com.example.services.impl.apis.MediaServices.MediaServiceImpl
import com.google.gson.GsonBuilder
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.BuildConfig
import com.nickerman.test3.ErrorHandlerImpl
import com.nickerman.test3.base.navigator.FragmentProvider
import com.nickerman.test3.base.navigator.FragmentProviderImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule(application: AbstractApplication) {
    private var windowContext: Context = application

    @Provides
    fun provideApplicationContext(): Context = windowContext

    @Provides
    fun provideFragmentProvider(): FragmentProvider = FragmentProviderImpl()

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @Provides
    @Singleton
    fun provideErrorHandler(handler: ErrorHandlerImpl): ErrorHandler = handler

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpClient =  OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TEST_API)
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMediaService(service: MediaServiceImpl): MediaService = service
}