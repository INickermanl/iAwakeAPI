package com.nickerman.test3.base.navigator

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.nickerman.test3.di.modules.ApplicationNavigatorModule
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [ApplicationNavigatorModule::class])
interface NavigationSubComponent {

    fun createApplicationNavigator() : NavigationManagerImpl

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: FragmentActivity, @BindsInstance fragmentManager: FragmentManager, @BindsInstance containerId: Int): NavigationSubComponent
    }
}