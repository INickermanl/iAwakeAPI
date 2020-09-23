package com.nickerman.test3

import android.app.Application
import com.nickerman.test3.di.ApplicationComponent
import com.nickerman.test3.di.DaggerApplicationComponent
import com.nickerman.test3.di.modules.ApplicationModule

class MyApplication : Application() {
    val mainComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this@MyApplication))
            .build()
    }

    override fun onCreate() {
        instance = this
        mainComponent.inject(this)
        super.onCreate()
    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}