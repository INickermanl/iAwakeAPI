package com.nickerman.test3.di.modules

import com.nickerman.test3.presenter.LoginPresenter
import com.nickerman.test3.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideLoginPresenter(): LoginPresenter = LoginPresenter()

    @Provides
    fun provideMainPresenter(): MainPresenter = MainPresenter()
}