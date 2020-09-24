package com.nickerman.test3

import android.app.Activity
import android.app.Application
import com.nickerman.test3.di.ApplicationComponent
import com.nickerman.test3.di.DaggerApplicationComponent
import com.nickerman.test3.di.modules.ApplicationModule
import timber.log.Timber

class MyApplication : Application() {

    lateinit var currentActivity: Activity

    val mainComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this@MyApplication))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        mainComponent.inject(this)
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}