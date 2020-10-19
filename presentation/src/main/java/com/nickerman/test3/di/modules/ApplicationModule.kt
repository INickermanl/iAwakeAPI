package com.nickerman.test3.di.modules

import android.content.Context
import android.media.MediaPlayer
import com.example.data.services.MediaServiceAPI
import com.example.domain.ErrorHandler
import com.example.utils.dialog.DialogQueueManager
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.BuildConfig
import com.nickerman.test3.ErrorHandlerImpl
import com.nickerman.test3.navigation.FragmentProvider
import com.nickerman.test3.navigation.FragmentProviderImpl
import com.nickerman.test3.ui.dialog.DialogQueueManagerImpl
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
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .build()

    @Provides
    @Singleton
    fun provideMediaServiceAPI(httpClient: OkHttpClient): MediaServiceAPI =
        Retrofit.Builder()
            .baseUrl(BuildConfig.MEDIA_SERVICE_URL)
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MediaServiceAPI::class.java)

    @Provides
    @Singleton
    fun provideMediaPlayer(): MediaPlayer = MediaPlayer()

    @Provides
    @Singleton
    fun provideDialogQueueManager(manager: DialogQueueManagerImpl): DialogQueueManager = manager


}