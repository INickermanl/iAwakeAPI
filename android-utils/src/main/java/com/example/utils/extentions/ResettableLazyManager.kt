package com.example.utils.extentions

import android.app.Dialog
import android.view.View
import java.util.*
import kotlin.reflect.KProperty

class ResettableLazyManager {
    val managedDelegates = LinkedList<Resettable>()

    fun register(managed: Resettable) {
        synchronized(managedDelegates) {
            managedDelegates.add(managed)
        }
    }

    fun reset() {
        synchronized(managedDelegates) {
            managedDelegates.forEach { it.reset() }
            managedDelegates.clear()
        }
    }
}

interface Resettable {
    fun reset()
}

class ResettableLazy<P>(val manager: ResettableLazyManager, val init: () -> P) : Resettable {
    @Volatile
    var lazyHolder = makeInitBlock()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): P {
        return lazyHolder.value
    }

    override fun reset() {
        lazyHolder = makeInitBlock()
    }

    fun makeInitBlock(): Lazy<P> {
        return lazy {
            manager.register(this)
            init()
        }
    }
}

fun <P> resettableLazy(manager: ResettableLazyManager, init: () -> P): ResettableLazy<P> {
    return ResettableLazy(manager, init)
}

fun resettableManager(): ResettableLazyManager = ResettableLazyManager()

/**
 * Only for custom view
 *
 * Example:
 * private val arrow: View? by getView(R.id.sub_view_id)
 */
fun <V : View?> View.getView(resId: Int): Lazy<V> {
    return lazy { findViewById<V>(resId) }
}
/**
 * Only for custom view
 *
 * Example:
 * private val arrow: View? by getView(R.id.sub_view_id)
 */
fun <V : View?> Dialog.getView(resId: Int): Lazy<V> {
    return lazy { findViewById<V>(resId) }
}