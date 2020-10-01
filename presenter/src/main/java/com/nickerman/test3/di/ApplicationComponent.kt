package com.nickerman.test3.di

import com.nickerman.test3.MainActivity
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.base.navigator.NavigationSubComponent
import com.nickerman.test3.di.modules.ApplicationModule
import com.nickerman.test3.di.modules.NavigationModule
import com.nickerman.test3.fragments.LoginFragment
import com.nickerman.test3.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NavigationModule::class]
)
interface ApplicationComponent {
    val navigationSubComponentFactory: NavigationSubComponent.Factory
    fun inject(application: AbstractApplication)
    fun inject(mainActivity: MainActivity)

    fun inject(loginFragment: LoginFragment)
    fun inject(mainFragment: MainFragment)
}