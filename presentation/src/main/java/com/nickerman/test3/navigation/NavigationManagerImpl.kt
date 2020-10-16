package com.nickerman.test3.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class NavigationManagerImpl @Inject constructor(
    private val fragmentProvider: FragmentProvider,
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    private val containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {
    override fun createFragment(screen: SupportAppScreen): Fragment {
        return fragmentProvider.createFragment(screen)
    }

    val currentFragment: Fragment?
        get() = fragmentManager.findFragmentById(containerId)

}