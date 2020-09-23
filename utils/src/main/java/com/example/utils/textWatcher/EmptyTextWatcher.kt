package com.example.utils.textWatcher

import android.text.Editable
import android.text.TextWatcher


open class EmptyTextWatcher : TextWatcher {

    private lateinit var beforeText: (() -> Unit)
    private lateinit var onText: (() -> Unit)
    private lateinit var afterText: ((CharSequence) -> Unit)

    constructor()

    constructor(afterText: (CharSequence) -> Unit) {
        this.afterText = afterText
    }

    constructor(onText: () -> Unit, afterText: (CharSequence) -> Unit) {
        this.onText = onText
        this.afterText = afterText
    }

    constructor(beforeText: () -> Unit, onText: () -> Unit, afterText: (CharSequence) -> Unit) {
        this.beforeText = beforeText
        this.onText = onText
        this.afterText = afterText
    }

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        if (::beforeText.isInitialized) {
            beforeText.invoke()
        }
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        if (::onText.isInitialized) {
            onText.invoke()
        }
    }

    override fun afterTextChanged(editable: Editable) {
        if (::afterText.isInitialized) {
            afterText.invoke(editable)
        }
    }
}