package com.nickerman.test3.di.modules

import com.nickerman.test3.fragments.media.play_list.mvp.widget.PlayListItemWidget
import com.nickerman.test3.fragments.media.program.widget.ProgramListItemWidget
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UiModule {
    @Provides
    @Singleton
    fun providePlayListWidget(): PlayListItemWidget = PlayListItemWidget()

    @Provides
    @Singleton
    fun provideProgramListItemWidget(): ProgramListItemWidget = ProgramListItemWidget()
}