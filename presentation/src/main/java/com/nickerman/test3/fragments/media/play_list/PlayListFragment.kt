package com.nickerman.test3.fragments.media.play_list

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bl.common.mvp.main.PlayListPresenter
import com.example.bl.common.mvp.main.view.PlayListView
import com.example.bl.common.navigation.PlayListScreen
import com.example.domain.dto.media_test.Track
import com.example.utils.gone
import com.example.utils.visible
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.ui.common.fragment.list.AbstractListFragment
import com.nickerman.test3.fragments.media.play_list.widget.PlayListItemWidget
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_play_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Provider
import kotlin.concurrent.schedule

class PlayListFragment @Inject constructor() :
    AbstractListFragment<Track, PlayListPresenter>(R.layout.fragment_play_list), PlayListView,
    MediaPlayer.OnPreparedListener {
    @Inject
    internal lateinit var playListWidget: Provider<PlayListItemWidget>


    var mediaPlayer: MediaPlayer? = null
    var needShowTime: Boolean = false
        get() = mediaPlayer?.isPlaying ?: false

    override val presenter: PlayListPresenter by moxyPresenter {
        presenterProvider.get().apply {
            if (hasData()) {
                init(getData())
            }
        }
    }

    private val adapter = ListAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        AbstractApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initView()
    }

    fun pause() {
        if (mediaPlayer?.isPlaying == true) mediaPlayer?.pause()
    }

    private fun initView() {
        pause.setOnClickListener {
            pause()
        }
        stop.setOnClickListener {
            stopPlay()
        }
        resume.setOnClickListener { resumePlay() }
    }

    override fun showList(track: List<Track>?) {
        if (!track.isNullOrEmpty()) {
            emptyView.gone()
            adapter.list = track
        } else {
            emptyView.visible()
        }
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer?.start()
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun resumePlay() {
        if (mediaPlayer?.isPlaying == false) mediaPlayer?.start()
    }

    private fun stopPlay() {
        mediaPlayer?.stop()
    }

    private fun playSound(track: Track) {
        val url = track.media?.mp3?.url
        if (!url.isNullOrBlank()) {
            if (mediaPlayer?.isPlaying == true) mediaPlayer?.release()
            mediaPlayer = null
            mediaPlayer = MediaPlayer()
            mediaPlayer?.apply {
                setDataSource(url)
                Timber.i("prepareAsync");
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setOnPreparedListener(this@PlayListFragment)
                prepareAsync()
            }
        }

    }

    fun time() {
        GlobalScope.launch {
            if(needShowTime){
                Timer("PlayTime", false).schedule(0L,1_000L) {

                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }

    override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<Track> {
        return playListWidget.get().getHolder(parent) { playSound(it) }
    }

    companion object {
        fun PlayListFragment.setUp(screen: PlayListScreen) = saveData(screen.program)
    }
}