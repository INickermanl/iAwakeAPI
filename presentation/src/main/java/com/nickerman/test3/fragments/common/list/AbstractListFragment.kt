package com.nickerman.test3.fragments.common.list

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.bl.common.moxy.AbstractPresenter
import com.nickerman.test3.fragments.common.AbstractScreenFragment
import com.nickerman.test3.ui.adapter.AbstractListAdapter
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder

abstract class AbstractListFragment<T : Any, P : AbstractPresenter<*>>(@LayoutRes layoutId: Int) : AbstractScreenFragment<P>(layoutId) {

    protected open inner class ListAdapter(list: List<T> = emptyList()) : AbstractListAdapter<T>(list) {

        override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<T> {
            return this@AbstractListFragment.onCreateViewHolder(parent)
        }
    }

    protected abstract fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<T>
}
