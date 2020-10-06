package com.nickerman.test3.base.navigator

import androidx.fragment.app.Fragment
import com.example.bl.common.navigation.LoginScreen
import com.example.bl.common.navigation.MainScreen
import com.nickerman.test3.fragments.LoginFragment
import com.nickerman.test3.fragments.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentProviderImpl : FragmentProvider {
    override fun createFragment(screen: SupportAppScreen): Fragment {
        return when (screen) {
            is LoginScreen -> LoginFragment()
            is MainScreen -> MainFragment()
            else -> throw IllegalArgumentException("NotSupportedScreen ${screen.screenKey}")
        }
    }
}