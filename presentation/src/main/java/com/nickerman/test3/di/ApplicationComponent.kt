package com.nickerman.test3.di

import com.example.utils.di.UtilsComponent
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.activity.MainActivity
import com.nickerman.test3.di.modules.ApplicationModule
import com.nickerman.test3.di.modules.NavigationModule
import com.nickerman.test3.di.modules.UiModule
import com.nickerman.test3.di.modules.UseCaseModule
import com.nickerman.test3.fragments.media.play_list.mvp.PlayListFragment
import com.nickerman.test3.fragments.media.play_list.view_model.fragment.PlayListFragmentListVM
import com.nickerman.test3.fragments.media.program.ProgramListFragment
import com.nickerman.test3.fragments.media.program.widget.ProgramListItemWidget
import com.nickerman.test3.navigation.NavigationSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NavigationModule::class,
        UseCaseModule::class,
        UiModule::class]
)
interface ApplicationComponent : UtilsComponent {
    val navigationSubComponentFactory: NavigationSubComponent.Factory
    fun inject(application: AbstractApplication)
    fun inject(mainActivity: MainActivity)

    fun inject(programListFragment: ProgramListFragment)
    fun inject(playListFragment: PlayListFragment)
    fun inject(programListItemWidget: ProgramListItemWidget)

    fun inject(programListFragment: PlayListFragmentListVM)
}