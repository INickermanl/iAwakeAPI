package com.nickerman.test3.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

interface FragmentProvider {
    fun createFragment(screen: SupportAppScreen): Fragment
}