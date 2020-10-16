package com.example.bl.common.mvp.login.view

import com.example.bl.common.moxy.view.CommonView
import com.example.domain.dto.media_test.MediaResponse
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(SkipStrategy::class)
interface LoginView : CommonView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showList(mediaResponse: MediaResponse)
}