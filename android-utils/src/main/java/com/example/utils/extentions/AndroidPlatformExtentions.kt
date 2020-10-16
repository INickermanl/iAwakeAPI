package com.example.utils.extentions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.*
import android.os.*
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.util.Base64
import android.util.TypedValue
import android.view.*
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.math.min

fun Context.dpToPx(dp: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
fun Context.dpToPx(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

@Suppress("deprecation")
fun TextView.setAppearance(@StyleRes resId: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.setTextAppearance(resId)
    } else {
        this.setTextAppearance(this.context, resId)
    }
}

fun Activity.getRootView(): View {
    return (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0)
}

fun Bitmap.roundCornerBitmap(pixels: Int): Bitmap {
    val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(output)
    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, width, height)
    val rectF = RectF(rect)
    val roundPx = pixels.toFloat()
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color
    canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, rect, rect, paint)
    return output
}

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun View.gone() {
    this.visibility = GONE
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}

operator fun Editable.plus(text: Editable?): String {
    val t = text?.toString() ?: ""
    return this.toString() + t
}

@RequiresApi(Build.VERSION_CODES.CUPCAKE)
fun View.showKeyboard() {
    if (requestFocus()) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInputFromWindow(windowToken, InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

@Suppress("unused")
fun <T : Any> whenNotNull(vararg elements: T?, transform: (List<T>) -> Unit) {
    val startSize = elements.size
    val filtered = elements.filterNotNull()
    if (filtered.size == startSize) transform(filtered)
}

@Suppress("unused")
fun whenNotNullOrNotEmpty(vararg elements: String?, transform: (List<String>) -> Unit) {
    val startSize = elements.size
    val filtered = elements.filter { !it.isNullOrEmpty() }
    if (filtered.size == startSize) transform(filtered.map { it ?: "" })
}


@Suppress("unused")
fun View.setMargins(left: Int, right: Int) {
    val layoutParams = this.layoutParams
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        layoutParams.setMargins(left, layoutParams.topMargin, right, layoutParams.bottomMargin)
    }
}

@Suppress("unused")
fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    val layoutParams = this.layoutParams
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        left?.run { layoutParams.leftMargin = this }
        top?.run { layoutParams.topMargin = this }
        right?.run { layoutParams.rightMargin = this }
        bottom?.run { layoutParams.bottomMargin = this }
        requestLayout()
    }
}

@Suppress("unused")
suspend fun <T> asyncWithErrorHandling(onError: (Exception) -> Unit = {}, doThat: () -> T): T? {
    return try {
        withContext(Dispatchers.Default) { doThat.invoke() }
    } catch (e: Exception) {
        onError.invoke(e)
        null
    }
}

fun Bitmap.encodeToByteArray(compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, quality: Int = 100): ByteArray {
    val byteArrayOS = ByteArrayOutputStream()
    compress(compressFormat, quality, byteArrayOS)
    return byteArrayOS.toByteArray()
}

fun String?.formatEuroPhoneNumber(): String? {
    if (this == null || this.length < 12) {
        return this
    } else {
        val formatted = StringBuilder()
        var count = 0
        for (i in this.indices) {
            if (Character.isDigit(this[i])) {
                if (count == 0) formatted.append("+")
                if (count == 3 || count == 5 || count == 8 || count == 10) formatted.append(" ")

                formatted.append(this[i])
                ++count
            }
        }
        return formatted.toString()
    }
}

fun String?.formatOnlyPrefixAndSuffixPhoneNumber(): String? {
    return this?.let {
        val builder = StringBuilder(it)
        if (builder.length == 13 && builder.startsWith("+")) {
            builder.insert(3, ' ')
            builder.insert(7, ' ')
        } else if (builder.length == 12 && !builder.startsWith("+")) {
            builder.insert(0, '+')
            builder.insert(3, ' ')
            builder.insert(7, ' ')
        } else if (builder.length == 10 && !builder.startsWith("+")){
            builder.insert(3, ' ')
        }
        builder.toString()
    } ?: ""
}

/**
 * Форматирует строку по указанной маске.
 * пример маски: ### (###) ### ####
 * this: +380501234567
 * вернет +38 (050) 123 4567
 * @return отформатированную по маске строку
 */
@Suppress("unused")
fun String?.masked(mask: String, maskSymbol: String = "#"): String {
    if (this.isNullOrEmpty()) return ""
    val out = StringBuilder()
    var j = 0
    mask.forEachIndexed { _, c ->
        val symbol = c.toString()
        if (symbol == maskSymbol) {
            out.append(this[j])
            j++
        } else {
            out.append(symbol)
        }
    }
    return out.toString()
}

fun getTranslatedColor(slideOffset: Float, @ColorInt startColor: Int, @ColorInt endColor: Int): Int {
    val startR = Color.red(startColor)
    val startG = Color.green(startColor)
    val startB = Color.blue(startColor)
    val startAlpha = Color.alpha(startColor)
    val finalR = (startR + (Color.red(endColor) - startR) * slideOffset).toInt()
    val finalG = (startG + (Color.green(endColor) - startG) * slideOffset).toInt()
    val finalB = (startB + (Color.blue(endColor) - startB) * slideOffset).toInt()
    val finalAlpha = (startAlpha + (Color.alpha(endColor) - startAlpha) * slideOffset).toInt()
    return Color.argb(finalAlpha, finalR, finalG, finalB)
}

fun ViewGroup.getViewByCoordinates(x: Float, y: Float, doBlock: (View) -> Unit) {
    (0 until childCount)
            .map { this.getChildAt(it) }
            .forEach { view ->
                val bounds = Rect()
                view.getHitRect(bounds)
                if (bounds.contains(x.toInt(), y.toInt())) {
                    doBlock.invoke(view)
                }
            }
}

fun ImageView.setBlackWhiteColorFilterIfNeed(needBlackWhiteColorFilter: Boolean) {
    val saturation = if (needBlackWhiteColorFilter) 0f else 1f
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(saturation)
    colorFilter = ColorMatrixColorFilter(colorMatrix)
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun ImageView.setImage(picture: String?, needClipToOutline: Boolean = false, @DrawableRes defaultRes: Int) {
    if (picture != null) {
        setImageBitmap(BitmapFactory.decodeFile(picture))
    } else {
        setImageResource(defaultRes)
    }
    clipToOutline = needClipToOutline
}

@Suppress("unused")
fun Array<SwitchCompat>.alwaysOneTurnedOnSwitchGroup(selected: (SwitchCompat) -> Unit) {
    fun stateChanged(switch: CompoundButton) {
        if (this.find { it.isChecked } == null) {
            if (this.indexOfFirst { it == switch } == 0) {
                this[min(size - 1, 1)].isChecked = true
            } else {
                this[0].isChecked = true
            }
        }
    }

    val listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
        if (!isChecked && buttonView is CompoundButton) stateChanged(buttonView)
        selected(buttonView as SwitchCompat)
    }
    for (item in this) {
        item.setOnCheckedChangeListener(listener)
    }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun DialogFragment.setStatusBarColor(@ColorInt color: Int) {
    dialog?.window?.let { setStatusBarColor(color, it) }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Fragment.setStatusBarColor(@ColorInt color: Int) {
    activity?.window?.let { setStatusBarColor(color, it) }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@Suppress("DEPRECATION")
private fun setStatusBarColor(color: Int, window: Window) {
    if ((Color.red(color) + Color.green(color) + Color.blue(color)) / 3 > 150) {
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
    window.statusBarColor = color
}

fun Fragment.setNavBarColor(@ColorInt color: Int) {
    activity?.window?.let { setNavBarColor(color, it) }
}

fun DialogFragment.setNavBarColor(@ColorInt color: Int) {
    dialog?.window?.let { setNavBarColor(color, it) }
}

@Suppress("DEPRECATION")
private fun setNavBarColor(@ColorInt color: Int, window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if ((Color.red(color) + Color.green(color) + Color.blue(color)) / 3 > 150) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR

        } else {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
        window.navigationBarColor = color
    }
}

fun View.setOnClickListenerWithDelay(delay: Int = 1000, clickListener: (View) -> Unit) {
    var mLastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - mLastClickTime < delay) {
            return@setOnClickListener
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        clickListener(it)
    }
}

fun AppCompatActivity.runOnActivityResume(todo: () -> Unit) {
    val activity = this
    if (activity.lifecycle.currentState == Lifecycle.State.RESUMED) {
        todo()
    } else {
        activity.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (activity.lifecycle.currentState == Lifecycle.State.RESUMED) {
                    Handler(Looper.getMainLooper()).post {
                        if (activity.lifecycle.currentState == Lifecycle.State.RESUMED) {
                            activity.lifecycle.removeObserver(this)
                            todo()
                        }
                    }
                }
            }
        })
    }
}

fun View.scrollToView(viewToScroll: View = this, smooth: Boolean = false) {
    if (this is ScrollView) {
        post {
            viewToScroll.requestFocus()
            val top = getTopRecursively(this, viewToScroll)
            if (smooth) {
                this.smoothScrollTo(0, top)
            } else {
                this.scrollTo(0, top)
            }
        }
    } else {
        val viewParent = parent
        if (viewParent is ViewGroup) viewParent.scrollToView(viewToScroll)
    }
}

private fun getTopRecursively(scrollView: ScrollView, viewToScroll: View, childTop: Int = 0): Int {
    return if (viewToScroll.parent == scrollView) {
        viewToScroll.top + childTop
    } else {
        getTopRecursively(scrollView, viewToScroll.parent as View, viewToScroll.top + childTop)
    }
}