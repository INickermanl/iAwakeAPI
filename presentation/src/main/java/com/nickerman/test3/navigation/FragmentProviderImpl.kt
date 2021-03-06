package com.nickerman.test3.navigation

import androidx.fragment.app.Fragment
import com.example.bl.common.navigation.ProgramListScreen
import com.example.bl.common.navigation.PlayListScreen
import com.nickerman.test3.fragments.media.program.ProgramListFragment
import com.nickerman.test3.fragments.media.play_list.view_model.fragment.PlayListFragmentListVM.Companion.setUp
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentProviderImpl : FragmentProvider {
    override fun createFragment(screen: SupportAppScreen): Fragment {
        return when (screen) {
            is ProgramListScreen -> ProgramListFragment()
            is PlayListScreen -> setUp(screen)
            else -> throw IllegalArgumentException("NotSupportedScreen ${screen.screenKey}")
        }
    }
}