package com.nickerman.test3.di

import com.nickerman.test3.MainActivity
import com.nickerman.test3.MyApplication
import com.nickerman.test3.base.navigator.NavigationSubComponent
import com.nickerman.test3.base.presenter.AbstractPresenter
import com.nickerman.test3.di.modules.ApplicationModule
import com.nickerman.test3.di.modules.NavigationModule
import com.nickerman.test3.di.modules.PresentationModule
import com.nickerman.test3.fragments.LoginFragment
import com.nickerman.test3.fragments.MainFragment
import com.nickerman.test3.presenter.LoginPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NavigationModule::class,
        PresentationModule::class]
)
interface ApplicationComponent {
    val navigationSubComponentFactory: NavigationSubComponent.Factory
    fun inject(application: MyApplication)
    fun inject(mainActivity: MainActivity)

    fun inject(loginFragment: LoginFragment)
    fun inject(loginFragment: LoginPresenter)
    fun inject(mainFragment: MainFragment)
}