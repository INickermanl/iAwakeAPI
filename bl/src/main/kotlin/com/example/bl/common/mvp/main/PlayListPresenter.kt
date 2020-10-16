package com.example.bl.common.mvp.main

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.main.view.PlayListView
import com.example.domain.dto.media_test.Program
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class PlayListPresenter @Inject constructor() : AbstractPresenter<PlayListView>() {
    lateinit var program: Program

    fun init(program: Program) {
        this@PlayListPresenter.program = program
    }

    override fun onFirstViewAttach() {
        viewState.showList(program.tracks)
    }

}