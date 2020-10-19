package com.nickerman.test3

import android.app.Application
import com.example.utils.di.UtilsComponentProvider
import com.nickerman.test3.ui.common.activity.AbstractActivity
import com.nickerman.test3.di.ApplicationComponent
import com.nickerman.test3.di.DaggerApplicationComponent
import com.nickerman.test3.di.modules.ApplicationModule
import timber.log.Timber

class AbstractApplication : Application() {

    lateinit var currentActivity: AbstractActivity

    val mainComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this@AbstractApplication))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        mainComponent.inject(this)
        UtilsComponentProvider.component = mainComponent
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: AbstractApplication
            private set
    }
}