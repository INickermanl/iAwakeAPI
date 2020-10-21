package com.nickerman.test3.music

import android.media.AudioAttributes
import android.media.MediaPlayer
import com.example.domain.dto.media_test.Track
import com.example.domain.interactor.MusicPlayer
import com.example.domain.interactor.PlayerListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class MusicPlayerImpl @Inject constructor() : MediaPlayer.OnPreparedListener, MusicPlayer {
    @Inject
    internal lateinit var mediaPlayer: MediaPlayer

    override var playerListener: PlayerListener? = null
    private var timerTask: TimerTask? = null
    private var currentTrack: Track? = null

    override fun startPlay(track: Track) = CoroutineScope(Dispatchers.Default).launch {
        val url = track.media?.mp3?.url

        if (!url.isNullOrBlank()) {
            if (url == currentTrack?.media?.mp3?.url) {
                resumePlay()
            } else {
                currentTrack = track
                mediaPlayer.reset()
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(url)
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setOnPreparedListener(this@MusicPlayerImpl)
                    Timber.i("prepareAsync")
                    prepareAsync()
                }
            }
        }
    }

    override fun resumePlay() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            checkTime(true)
        }
    }

    override fun pausePlay() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            checkTime(false)
        }
    }

    override fun destroy() {
        mediaPlayer.reset()
        timerTask?.cancel()
    }

    override fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    override fun onPrepared(mediaPlayer: MediaPlayer?) {
        this.mediaPlayer.start()
        checkTime(true)
    }

    private fun checkTime(needShow: Boolean) = CoroutineScope(Dispatchers.Default).launch {
        if (!needShow) {
            timerTask?.cancel()
        } else {
            timerTask = Timer("PlayTime", false).schedule(0L, 1_000L) {
                playerListener?.trackTime(mediaPlayer.currentPosition.toLong())
            }
        }
    }
}