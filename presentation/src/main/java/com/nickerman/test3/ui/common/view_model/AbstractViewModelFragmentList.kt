package com.nickerman.test3.ui.common.view_model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.nickerman.test3.ui.adapter.AbstractListAdapter
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import java.io.Serializable

abstract class AbstractViewModelFragmentList<T : Any, B : ViewDataBinding, VM : ViewModel>(@LayoutRes val layout: Int) :
    Fragment() {
    internal lateinit var binding: B
    internal lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        return binding.root
    }

    protected open inner class ListAdapter(list: List<T> = emptyList()) :
        AbstractListAdapter<T>(list) {

        override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<T> {
            return this@AbstractViewModelFragmentList.onCreateViewHolder(parent)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable?> getData(): T {
        return arguments?.getSerializable(EXTRA) as T
    }

    fun hasData() = arguments != null

    protected abstract fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder<T>

    protected companion object {
        private const val EXTRA = "extra"
        private const val IS_ARRAY = "isArray"

        fun <F : AbstractViewModelFragmentList<*, *, *>> F.saveData(data: Serializable?): F {
            val args = Bundle()
            args.putSerializable(EXTRA, data)
            this.arguments = args
            return this
        }

        fun <F : AbstractViewModelFragmentList<*, *, *>> F.saveData(vararg data: Serializable?): F {
            val args = Bundle()
            for (i in data.indices) {
                args.putSerializable(EXTRA + i, data[i])
            }
            args.putSerializable(IS_ARRAY, true)
            arguments = args
            return this
        }
    }
}