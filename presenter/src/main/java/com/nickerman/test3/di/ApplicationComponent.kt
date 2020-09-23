package com.nickerman.test3.di

import com.nickerman.test3.MainActivity
import com.nickerman.test3.MyApplication
import com.nickerman.test3.di.modules.ApplicationModule
import com.nickerman.test3.di.modules.NavigationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NavigationModule::class]
)
interface ApplicationComponent {
    fun inject(application: MyApplication)
    fun inject(application: MainActivity)
}