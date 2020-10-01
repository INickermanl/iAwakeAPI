package com.example.bl.common.mvp.main

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.main.view.MainView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : AbstractPresenter<MainView>() {
}