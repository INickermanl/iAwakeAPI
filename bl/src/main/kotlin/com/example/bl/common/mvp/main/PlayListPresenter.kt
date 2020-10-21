package com.example.bl.common.mvp.main

import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.mvp.main.view.PlayListView
import com.example.domain.dto.media_test.Program
import com.example.domain.dto.media_test.Track
import com.example.domain.interactor.MusicPlayer
import com.example.domain.interactor.PlayerListener
import moxy.InjectViewState
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class PlayListPresenter @Inject constructor() : AbstractPresenter<PlayListView>(), PlayerListener {
    lateinit var program: Program

    @Inject
    internal lateinit var musicPlayer: MusicPlayer

    fun init(program: Program) {
        this@PlayListPresenter.program = program
        musicPlayer.playerListener = this
    }

    override fun onFirstViewAttach() {
        viewState.showList(program.tracks)
    }

    fun pausePlay() {
        musicPlayer.pausePlay()
    }

    fun resumePlay() {
        musicPlayer.resumePlay()
    }

    fun startPlay(track: Track) {
        musicPlayer.startPlay(track)

    }

    override fun onDestroy() {
        musicPlayer.destroy()
        super.onDestroy()
    }

    override fun trackTime(time: Long) = launchUnit {
        viewState.showTrackTime(TimeUnit.MILLISECONDS.toSeconds(time).toString())
    }
}