package com.nickerman.test3.base.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.nickerman.test3.base.presenter.AbstractPresenter
import java.io.Serializable

abstract class BaseFragment<T : AbstractPresenter>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    abstract var presenter: T

    protected companion object {
        private const val EXTRA = "extra"
        private const val IS_ARRAY = "isArray"

        fun <F : BaseFragment<*>> F.saveData(data: Serializable?): F {
            val args = Bundle()
            args.putSerializable(EXTRA, data)
            this.arguments = args
            return this
        }

        fun <F : BaseFragment<*>> F.saveData(vararg data: Serializable?): F {
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