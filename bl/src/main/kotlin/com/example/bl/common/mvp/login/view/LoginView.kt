package com.example.bl.common.mvp.login.view

import com.example.bl.common.moxy.view.CommonView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(SkipStrategy::class)
interface LoginView : CommonView {

}