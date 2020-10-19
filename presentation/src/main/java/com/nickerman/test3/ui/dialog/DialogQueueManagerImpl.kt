package com.nickerman.test3.ui.dialog

import com.example.utils.dialog.DialogQueueManager
import com.example.utils.dialog.WrappedShowDialog
import javax.inject.Inject

class DialogQueueManagerImpl @Inject constructor() : DialogQueueManager {
    private val queue: MutableList<WrappedShowDialog> = mutableListOf()
    private var currentShowed: WrappedShowDialog? = null

    @Synchronized
    override fun addToQueue(dialog: WrappedShowDialog) {
        if (queue.isEmpty() && currentShowed == null) {
            show(dialog)
        } else {
            queue.add(dialog)
        }
    }

    private fun show(dialog: WrappedShowDialog) {
        currentShowed = dialog
        dialog.onDismissCallback = {
            currentShowed = null
            showNext()
        }
        dialog.internalShow()
    }

    private fun showNext() {
        if (queue.isNotEmpty()) {
            val dialog = queue[0]
            queue.remove(dialog)
            show(dialog)
        }
    }
}