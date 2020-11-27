package com.example.bl.common.mvp.program_list

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.program_list.view.ProgramListView
import com.example.bl.common.navigation.PlayListScreen
import com.example.domain.dto.media_test.Program
import com.example.domain.interactor.MediaServiceUseCase
import com.example.domain.response
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class ProgramListPresenter @Inject constructor() : AbstractPresenter<ProgramListView>() {
    @Inject
    internal lateinit var useCase: MediaServiceUseCase

    override fun onFirstViewAttach() {
        getData(true)
    }

    private fun getData(needRefresh: Boolean) = launchUnit {
        viewState.showLoaderWithLock()
        viewState.showList(useCase.getMedia()?.response)
        viewState.hideLoaderWithLock()
    }

    fun navigateToPlayList(program: Program) {
        router.navigateTo(PlayListScreen(program))
    }
}