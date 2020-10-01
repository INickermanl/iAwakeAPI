package com.nickerman.test3

import android.os.Bundle
import com.nickerman.test3.base.BaseActivity
import com.example.bl.common.navigation.LoginScreen
import com.nickerman.test3.base.navigator.NavigationManagerImpl
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class MainActivity @Inject constructor() : BaseActivity() {

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