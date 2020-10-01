package com.nickerman.test3.di.modules

import com.example.bl.common.mvp.login.LoginPresenter
import com.example.bl.common.mvp.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideLoginPresenter(): LoginPresenter = LoginPresenter()

    @Provides
    fun provideMainPresenter(): MainPresenter = MainPresenter()
}