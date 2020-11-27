package com.example.bl.common.mvp.program_list.view

import com.example.bl.common.moxy.view.CommonView
import com.example.domain.dto.media_test.MediaResponse
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface ProgramListView : CommonView {
    fun showList(mediaResponse: MediaResponse?)
}