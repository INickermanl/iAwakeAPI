package com.nickerman.test3.fragments.media.play_list.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.domain.dto.media_test.Track
import com.nickerman.test3.R
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import com.nickerman.test3.ui.adapter.widget.AbstractItemWidget

class PlayListItemWidget : AbstractItemWidget<Track>() {
    private lateinit var view: View

    override fun getHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        selectAction: (Track) -> Unit
    ): ListItemViewHolder<Track> {
        view = inflater.inflate(R.layout.widget_play_list_item, parent, false)
        return ListItemViewHolder(view.rootView, this::onBindViewHolder, selectAction)
    }

    private fun onBindViewHolder(track: Track) {
        view.findViewById<TextView>(R.id.track).text = track.title
    }
}