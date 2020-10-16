package com.nickerman.test3.fragments.common

import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.bl.common.moxy.AbstractPresenter
import com.example.bl.common.navigation.BackButtonListener
import com.example.utils.extentions.setNavBarColor
import com.example.utils.extentions.setStatusBarColor
import com.nickerman.test3.R

abstract class AbstractScreenFragment<P : AbstractPresenter<*>>(@LayoutRes layoutId: Int) :
    AbstractFragment<P>(layoutId), BackButtonListener {
    private var adjustResizeMode: Boolean = true

    @ColorRes
    protected open val statusBarColorRes: Int = R.color.statusBarColor

    @ColorInt
    private var _statusBarColor: Int = 0

    protected var statusBarColor: Int
        get() = if (_statusBarColor == 0) {
            ContextCompat.getColor(requireContext(), statusBarColorRes)
                .also { _statusBarColor = it }
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

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        configWindow()
    }

    @Suppress("DEPRECATION")
    protected fun configWindow() {
        setStatusBarColor(statusBarColor)
        setNavBarColor(navBarColor)
    }

    override fun onDestroyView() {
        changeSoftInputModeToAdjustPan()
        closeAllDialogFragments()
        super.onDestroyView()
    }

    protected fun changeSoftInputModeToAdjustPan() {
        if (adjustResizeMode) {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        }
    }

    private fun closeAllDialogFragments() {
        val fragmentManager = if (activity == null) null else activity?.supportFragmentManager
        if (fragmentManager != null) {
            for (fragment in fragmentManager.fragments) {
                if (fragment is DialogFragment) {
                    fragment.dismiss()
                }
            }
        }
    }
}