package com.nickerman.test3.ui.adapter.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder

abstract class AbstractItemWidget<T : Any> {

    lateinit var itemData: T
    protected lateinit var holder: ListItemViewHolder<T>

    fun getHolder(parent: ViewGroup, selectAction: (T) -> Unit = {}): ListItemViewHolder<T> {
        holder = getHolder(parent, parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater, selectAction)
        return holder
    }

    protected abstract fun getHolder(parent: ViewGroup, inflater: LayoutInflater, selectAction: (T) -> Unit): ListItemViewHolder<T>
}
