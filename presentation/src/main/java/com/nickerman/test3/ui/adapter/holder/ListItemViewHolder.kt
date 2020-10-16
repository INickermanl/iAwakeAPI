package com.nickerman.test3.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ListItemViewHolder<out T : Any>(
    val view: View,
    private val onBind: (T) -> Unit,
    touchedView: View = view,
    onClick: (T) -> Unit = {},
    onLongClick: (T) -> Unit = {}
) : RecyclerView.ViewHolder(view) {
    private var hasOnClick = true
    private var hasOnLongClick = true

    constructor(view: View, onBind: (T) -> Unit) : this(view, onBind, view, {}, {}) {
        hasOnClick = false
        hasOnLongClick = false
    }

    constructor(view: View, onBind: (T) -> Unit, onClick: (T) -> Unit) : this(
        view,
        onBind,
        view,
        onClick,
        {}) {
        hasOnLongClick = false
    }

    constructor(
        view: View,
        onBind: (T) -> Unit,
        onClick: (T) -> Unit,
        onLongClick: (T) -> Unit
    ) : this(view, onBind, view, onClick, onLongClick)

    var onClickListener: (@UnsafeVariance T) -> Unit = onClick
    var onLongListener: (@UnsafeVariance T) -> Unit = onLongClick

    protected lateinit var t: @UnsafeVariance T

    init {
        if (hasOnClick) touchedView.setOnClickListener { onClickListener(t) }
        if (hasOnLongClick) touchedView.setOnLongClickListener {
            onLongListener(t)
            true
        }
    }

    fun onBindViewHolder(t: @UnsafeVariance T) {
        this.t = t
        onBind(t)
    }
}
