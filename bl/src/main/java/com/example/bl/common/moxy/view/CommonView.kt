package com.example.bl.common.moxy.view

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface CommonView : MvpView {
    fun showLoaderWithLock()
    fun hideLoaderWithLock()
    fun todo(message: String = "") {
    }
}