package com.nickerman.test3.ui.adapter

import android.view.ViewGroup
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder

abstract class AbstractListAdapter<T : Any>(list: List<T> = emptyList()) : AbstractRefreshableListAdapter<T, ListItemViewHolder<T>>() {

    init {
        data = list.toMutableList()
    }

    override fun refreshItem(t: T) {
        notifyItemChanged(data.indexOf(t))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder<T> {
        return onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder<T>, position: Int) {
        holder.onBindViewHolder(data[position])
    }

    override fun delete(t: T) {
        val position = data.indexOf(t)
        if (position != -1) {
            data.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
        }
    }

    protected open fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<T> {
        throw NotImplementedError()
    }
}
