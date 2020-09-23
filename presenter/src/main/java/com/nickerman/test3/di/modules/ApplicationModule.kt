package com.nickerman.test3.di.modules

import android.content.Context
import com.nickerman.test3.MyApplication
import com.nickerman.test3.base.TEstIII
import com.nickerman.test3.base.TestImpl
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(application: MyApplication) {
    private var windowContext: Context = application

    @Provides
    fun provideApplicationContext(): Context = windowContext

    @Provides
    fun hello(): TEstIII = TestImpl()
}