package com.example.bl.common.mvp.play_list.view

import com.example.bl.common.moxy.view.CommonView
import com.example.domain.dto.media_test.Track
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PlayListView : CommonView {
    fun showList(trackList: List<Track>?)
    fun showTrackTime(time: String)
}