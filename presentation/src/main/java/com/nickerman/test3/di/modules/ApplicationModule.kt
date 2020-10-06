package com.nickerman.test3.di.modules

import android.content.Context
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.base.navigator.FragmentProvider
import com.nickerman.test3.base.navigator.FragmentProviderImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
class ApplicationModule(application: AbstractApplication) {
    private var windowContext: Context = application

    @Provides
    fun provideApplicationContext(): Context = windowContext

    @Provides
    fun provideFragmentProvider(): FragmentProvider = FragmentProviderImpl()

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}