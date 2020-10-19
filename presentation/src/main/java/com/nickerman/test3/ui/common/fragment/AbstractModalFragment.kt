package com.nickerman.test3.ui.common.fragment

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.moxy.view.CommonView
import com.example.utils.extentions.ResettableLazy
import com.example.utils.extentions.resettableManager
import com.example.utils.extentions.setNavBarColor
import com.example.utils.extentions.setStatusBarColor
import com.nickerman.test3.R
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Provider

abstract class AbstractModalFragment<P : AbstractPresenter<*>> : MvpDialogFragment(), CommonView {

    @Inject
    lateinit var presenterProvider: Provider<P>

    private val backClickListener: (View) -> Unit = { onBackPressed() }

    protected lateinit var rootView: View
    private val lazyMgr = resettableManager()

    @ColorRes
    protected open val statusBarColorRes: Int = R.color.statusBarColor

    @ColorInt
    private var _statusBarColor: Int = 0

    protected var statusBarColor: Int
        get() = if (_statusBarColor == 0) {
            ContextCompat.getColor(requireContext(), statusBarColorRes).also { _statusBarColor = it }
        } else _statusBarColor
        set(value) {
            _statusBarColor = value
            setStatusBarColor(value)
        }
    protected open val navBarColorRes: Int = R.color.navigationBarColor
    @ColorInt
    private var _navBarColor: Int = 0

    protected var navBarColor: Int
        get() = if (_navBarColor == 0) {
            ContextCompat.getColor(requireContext(), navBarColorRes).also { _navBarColor = it }
        } else _navBarColor
        set(value) {
            _navBarColor = value
            setNavBarColor(value)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
    }

    fun <V : View?> getView(@IdRes id: Int): ResettableLazy<V> {
        return ResettableLazy(lazyMgr) { rootView.findViewById<V>(id) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lazyMgr.reset()
    }


    protected open fun onBackPressed(){
        dismiss()
    }

    override fun onResume() {
        super.onResume()
        setStatusBarColor(statusBarColor)
        setNavBarColor(statusBarColor)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable?> getData(): T {
        return arguments?.getSerializable(EXTRA) as T
    }

    fun savedDataIsArray(): Boolean {
        val arguments = arguments
        return arguments != null && arguments.getBoolean(IS_ARRAY)
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

    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable?> getData(index: Int): T {
        return arrayData[index] as T
    }

    protected companion object {
        private const val EXTRA = "extra"
        private const val IS_ARRAY = "isArray"

        fun <F : AbstractModalFragment<*>> F.saveData(data: Serializable?): F {
            val args = Bundle()
            args.putSerializable(EXTRA, data)
            this.arguments = args
            return this
        }

        fun <F : AbstractModalFragment<*>> F.saveData(vararg data: Serializable?): F {
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
