package com.nickerman.test3.fragments.login

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bl.common.mvp.login.MediaFreePresenter
import com.example.bl.common.mvp.login.view.LoginView
import com.example.domain.dto.media_test.MediaResponse
import com.example.domain.dto.media_test.Playlists
import com.example.domain.dto.media_test.Program
import com.example.utils.extentions.visible
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.fragments.common.list.AbstractListFragment
import com.nickerman.test3.fragments.login.widget.FreeMediaListItemWidget
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_media_free.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MediaFreeListFragment @Inject constructor() :
    AbstractListFragment<Program, MediaFreePresenter>(R.layout.fragment_media_free),
    LoginView {

    override val presenter: MediaFreePresenter by moxyPresenter { presenterProvider.get() }

    private var adapter = ListAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        AbstractApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun showList(mediaResponse: MediaResponse) {
        if (mediaResponse.programs.isNullOrEmpty()) {
            emptyView.visible()
        } else {
            adapter.list = mediaResponse.programs ?: emptyList()
        }

    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.compareItemsByIdFun = { m1, m2 -> m1.title == m2.title }
    }

    override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<Program> =
        FreeMediaListItemWidget().getHolder(parent)
}