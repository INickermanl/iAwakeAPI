package com.nickerman.test3.di.modules

import android.content.Context
import com.example.bl.common.mvp.login.LoginPresenter
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.base.TEstIII
import com.nickerman.test3.base.TestImpl
import com.nickerman.test3.base.navigator.FragmentProvider
import com.nickerman.test3.base.navigator.FragmentProviderImpl
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(application: AbstractApplication) {
    private var windowContext: Context = application

    @Provides
    fun provideApplicationContext(): Context = windowContext

    @Provides
    fun provideFragmentProvider(): FragmentProvider = FragmentProviderImpl()
}