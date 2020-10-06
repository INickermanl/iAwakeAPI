package com.nickerman.test3.activity

import android.os.Bundle
import com.example.bl.common.navigation.LoginScreen
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.base.AbstractActivity
import com.nickerman.test3.base.navigator.NavigationManagerImpl
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

        navigator.applyCommands(arrayOf(Replace(LoginScreen())))
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