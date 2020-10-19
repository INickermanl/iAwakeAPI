package com.nickerman.test3.ui.common.fragment

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import moxy.MvpDelegate
import moxy.MvpDelegateHolder

abstract class MvpDialogFragment : DialogFragment(), MvpDelegateHolder {
    private var mMvpDelegate: MvpDelegate<*>? = null

    override fun getMvpDelegate(): MvpDelegate<*> {
        return mMvpDelegate ?: MvpDelegate(this).also { newDelegate ->
            mMvpDelegate = newDelegate
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpDelegate.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mvpDelegate.onAttach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mvpDelegate.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        var anyParentIsRemoving = false
        var parent = this.parentFragment
        while (!anyParentIsRemoving && parent != null) {
            anyParentIsRemoving = parent.isRemoving
            parent = parent.parentFragment
        }
        if (this.isRemoving || anyParentIsRemoving || this.activity?.isFinishing != false) {
            mvpDelegate.onDestroy()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvpDelegate.onSaveInstanceState(outState)
    }
}