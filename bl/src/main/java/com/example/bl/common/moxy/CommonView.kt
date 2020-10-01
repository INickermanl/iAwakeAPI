package com.example.bl.common.moxy

import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SkipStrategy::class)
interface CommonView : MvpView {

    fun todo(message: String = "") {
    }
}