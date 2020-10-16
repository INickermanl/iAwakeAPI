package com.nickerman.test3.di

import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.activity.MainActivity
import com.nickerman.test3.di.modules.ApplicationModule
import com.nickerman.test3.di.modules.NavigationModule
import com.nickerman.test3.di.modules.UseCaseModule
import com.nickerman.test3.fragments.MainFragment
import com.nickerman.test3.fragments.login.MediaFreeListFragment
import com.nickerman.test3.fragments.login.widget.FreeMediaListItemWidget
import com.nickerman.test3.navigation.NavigationSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NavigationModule::class,
        UseCaseModule::class]
)
interface ApplicationComponent {
    val navigationSubComponentFactory: NavigationSubComponent.Factory
    fun inject(application: AbstractApplication)
    fun inject(mainActivity: MainActivity)

    fun inject(mediaFreeListFragment: MediaFreeListFragment)
    fun inject(mainFragment: MainFragment)
    fun inject(freeMediaListItemWidget: FreeMediaListItemWidget)
}