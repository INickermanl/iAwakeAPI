package com.example.utils.dialog

fun interface DialogQueueManager {
    fun addToQueue(dialog: WrappedShowDialog)
}