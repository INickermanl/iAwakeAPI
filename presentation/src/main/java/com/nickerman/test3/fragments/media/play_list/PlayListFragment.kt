package com.nickerman.test3.fragments.media.play_list

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
import com.nickerman.test3.fragments.common.list.AbstractListFragment
import com.nickerman.test3.fragments.media.play_list.widget.PlayListItemWidget
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_play_list.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class PlayListFragment @Inject constructor() :
    AbstractListFragment<Track, PlayListPresenter>(R.layout.fragment_play_list), PlayListView {
    @Inject
    internal lateinit var playListWidget: Provider<PlayListItemWidget>

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
    }

    override fun showList(track: List<Track>?) {
        if (!track.isNullOrEmpty()) {
            emptyView.gone()
            adapter.list = track
        } else {
            emptyView.visible()
        }
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<Track> {
        return playListWidget.get().getHolder(parent)
    }

    companion object {
        fun PlayListFragment.setUp(screen: PlayListScreen) = saveData(screen.program)
    }
}