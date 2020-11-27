package com.nickerman.test3.fragments.media.program

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bl.common.mvp.program_list.ProgramListPresenter
import com.example.bl.common.mvp.program_list.view.ProgramListView
import com.example.domain.dto.media_test.MediaResponse
import com.example.domain.dto.media_test.Program
import com.example.utils.extentions.visible
import com.example.utils.gone
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.ui.common.fragment.list.AbstractListFragment
import com.nickerman.test3.fragments.media.program.widget.ProgramListItemWidget
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import kotlinx.android.synthetic.main.empty_view.*
import kotlinx.android.synthetic.main.fragment_media_free.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class ProgramListFragment @Inject constructor() :
    AbstractListFragment<Program, ProgramListPresenter>(R.layout.fragment_media_free),
    ProgramListView {
    @Inject
    internal lateinit var programListItemWidget: Provider<ProgramListItemWidget>

    override val presenter: ProgramListPresenter by moxyPresenter { presenterProvider.get() }

    private val adapter by lazy { ListAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AbstractApplication.instance.mainComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    override fun showList(mediaResponse: MediaResponse?) {
        if (!mediaResponse?.programs.isNullOrEmpty()) {
            emptyView.gone()
            adapter.list = mediaResponse?.programs ?: emptyList()
        } else {
            emptyView.visible()
        }
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.compareItemsByIdFun = { m1, m2 -> m1.title == m2.title }
    }

    override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<Program> =
        programListItemWidget.get().getHolder(parent) { presenter.navigateToPlayList(it) }
}