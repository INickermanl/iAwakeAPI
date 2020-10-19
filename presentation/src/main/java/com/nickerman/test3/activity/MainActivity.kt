package com.nickerman.test3.activity

import android.os.Bundle
import com.example.bl.common.navigation.BackButtonListener
import com.example.bl.common.navigation.ProgramListScreen
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.ui.common.activity.AbstractActivity
import com.nickerman.test3.navigation.NavigationManagerImpl
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class MainActivity @Inject constructor() : AbstractActivity() {

    private val navigator: NavigationManagerImpl by lazy {
        AbstractApplication.instance.mainComponent.navigationSubComponentFactory
            .create(this, supportFragmentManager, R.id.mainFragmentContainer)
            .createApplicationNavigator()
    }

    @Inject
    internal lateinit var navigatinHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        AbstractApplication.instance.currentActivity = this@MainActivity
        AbstractApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.rootView)

        navigator.applyCommands(arrayOf(Replace(ProgramListScreen())))
    }

    override fun onBackPressed() {
        val fragment = navigator.currentFragment
        if (fragment != null && fragment is BackButtonListener) {
            if (!(fragment as BackButtonListener).onBackPressed()) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        navigatinHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatinHolder.removeNavigator()
        super.onPause()
    }
}