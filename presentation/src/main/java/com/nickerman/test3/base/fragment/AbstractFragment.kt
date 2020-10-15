package com.nickerman.test3.base.fragment

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.moxy.view.CommonView
import com.example.utils.gone
import com.example.utils.visible
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.R
import com.nickerman.test3.activity.MainActivity
import moxy.MvpAppCompatFragment
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractFragment<P : AbstractPresenter<*>>(@LayoutRes layoutId: Int) :
    MvpAppCompatFragment(layoutId), CommonView {

    @Inject
    lateinit var presenterProvider: Provider<P>

    protected abstract val presenter: P

    protected lateinit var rootView: View

    override fun showLoaderWithLock() {
        AbstractApplication.instance.currentActivity.rootView.findViewById<FrameLayout>(R.id.lockScreen)
            .visible()
        AbstractApplication.instance.currentActivity.rootView.findViewById<ProgressBar>(R.id.mainProgressBar)
            .visible()
    }

    override fun hideLoaderWithLock() {
        AbstractApplication.instance.currentActivity.rootView.findViewById<FrameLayout>(R.id.lockScreen)
            .gone()
        AbstractApplication.instance.currentActivity.findViewById<ProgressBar>(R.id.mainProgressBar)
            .gone()
    }

    val arrayData: List<Serializable?> by lazy {
        val serializables = mutableListOf<Serializable?>()
        var i = 0
        val args = arguments ?: return@lazy serializables
        while (args.containsKey(EXTRA + i)) {
            serializables.add(args.getSerializable(EXTRA + i))
            i++
        }
        serializables
    }

    fun hasData() = arguments != null

    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable?> getData(): T {
        return arguments?.getSerializable(EXTRA) as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable?> getData(index: Int): T {
        return arrayData[index] as T
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
    }

    protected companion object {
        private const val EXTRA = "extra"
        private const val IS_ARRAY = "isArray"

        fun <F : AbstractFragment<*>> F.saveData(data: Serializable?): F {
            val args = Bundle()
            args.putSerializable(EXTRA, data)
            this.arguments = args
            return this
        }

        fun <F : AbstractFragment<*>> F.saveData(vararg data: Serializable?): F {
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