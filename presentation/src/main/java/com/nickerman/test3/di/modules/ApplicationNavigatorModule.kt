package com.nickerman.test3.di.modules

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.nickerman.test3.navigation.FragmentProvider
import com.nickerman.test3.navigation.NavigationManagerImpl
import dagger.Module
import dagger.Provides

@Module
open class ApplicationNavigatorModule {
    @Provides
    open fun provideApplicationNavigator(
        fragmentProvider: FragmentProvider,
        activity: FragmentActivity,
        fragmentManager: FragmentManager,
        containerId: Int
    ): NavigationManagerImpl {
        return NavigationManagerImpl(fragmentProvider, activity, fragmentManager, containerId)
    }
}