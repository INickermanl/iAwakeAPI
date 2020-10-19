package com.example.utils.dialog

interface WrappedShowDialog {
    fun internalShow()
    var onDismissCallback: () -> Unit
}