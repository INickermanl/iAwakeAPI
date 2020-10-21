package com.example.domain.interactor

import com.example.domain.dto.media_test.Track
import kotlinx.coroutines.Job

interface MusicPlayer {
    var playerListener: PlayerListener?
    fun isPlaying(): Boolean
    fun startPlay(track: Track): Job
    fun resumePlay()
    fun pausePlay()
    fun destroy()
}