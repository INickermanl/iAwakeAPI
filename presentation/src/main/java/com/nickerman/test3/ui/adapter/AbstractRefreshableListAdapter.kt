package com.nickerman.test3.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import com.nickerman.test3.ui.notifyWithDiffUtil

abstract class AbstractRefreshableListAdapter<T : Any, H : ListItemViewHolder<T>> : RecyclerView.Adapter<H>() {

    var compareItemsByIdFun: ((T, T) -> Boolean)? = null
    var compareContent: ((T, T) -> Boolean)? = null
    protected var data: MutableList<T> = mutableListOf()

    open var list: List<T>
        get() = data
        set(value) {
            val oldList = data
            data = value.toMutableList()
            val compareItemsById = compareItemsByIdFun
            val compareContent = compareContent
            if (compareItemsById != null && compareContent != null) {
                notifyWithDiffUtil(oldList, data, compareItemsById, compareContent)
            } else if (compareItemsById != null) {
                notifyWithDiffUtil(oldList, data, compareItemsById)
            } else {
                notifyDataSetChanged()
            }
        }

    fun add(list: List<T>) {
        val dataSize = this.data.size
        this.data.addAll(list)
        this.notifyItemRangeInserted(dataSize, list.size)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    abstract fun refreshItem(t: T)

    open fun delete(t: T) {
        //Default NOP
    }
}
