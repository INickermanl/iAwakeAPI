package com.nickerman.test3.presenter

import com.nickerman.test3.MyApplication
import com.nickerman.test3.base.presenter.AbstractPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor() : AbstractPresenter() {
    init {
        MyApplication.instance.mainComponent.inject(this)
    }
}