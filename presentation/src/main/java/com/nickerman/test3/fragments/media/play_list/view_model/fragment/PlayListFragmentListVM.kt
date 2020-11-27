package com.nickerman.test3.fragments.media.play_list.view_model.fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bl.common.navigation.PlayListScreen
import com.example.domain.dto.media_test.Track
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.databinding.FragmentPlayListBinding
import com.nickerman.test3.fragments.media.play_list.mvp.widget.PlayListItemWidget
import com.nickerman.test3.fragments.media.play_list.view_model.viewModel.PlayListViewModel
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import com.nickerman.test3.ui.common.view_model.AbstractViewModelFragmentList
import javax.inject.Inject
import javax.inject.Provider

class PlayListFragmentListVM @Inject constructor() :
    AbstractViewModelFragmentList<Track, FragmentPlayListBinding, PlayListViewModel>(R.layout.fragment_play_list) {

    @Inject
    internal lateinit var playListWidget: Provider<PlayListItemWidget>

    private val adapter: ListAdapter by lazy { ListAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AbstractApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayListViewModel::class.java)

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }

        if (hasData()) {
            viewModel.playList = MutableLiveData(getData())
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.counterData.observe(viewLifecycleOwner, {
            binding.trackTime.text = it.toString()
        })
        viewModel.playList.observe(viewLifecycleOwner, {
            adapter.list = it.tracks ?: mutableListOf()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<Track> {
        return playListWidget.get().getHolder(parent)
    }

    companion object {
        fun setUp(screen: PlayListScreen) =
            PlayListFragmentListVM().saveData(screen.program)
    }
}