package com.example.utils.dialog

import android.app.Dialog
import android.content.Context
import android.text.method.MovementMethod
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.example.utils.di.UtilsComponentProvider
import com.example.utils.extentions.getView
import com.example.utils.extentions.setAppearance
import com.nickerman.test3.utils.R
import javax.inject.Inject


class CustomDialog private constructor(
    private val builder: Builder
) : Dialog(builder.context ?: throw IllegalStateException("context"), R.style.CustomDialogTheme),
    WrappedShowDialog, IsDialog {

//    @Inject
//    lateinit var dialogSystemSubscription: DialogSystemSubscription

    @Inject
    lateinit var dialogQueueManager: DialogQueueManager

    @Inject
    lateinit var baseContext: Context

    private var needShowDialog: Boolean = true
    override var onDismissCallback: () -> Unit = {}

    private val dialogButtonContainer: ViewGroup by getView(R.id.dialogButtonContainer)
    private val dontShowAgain: CheckBox by getView(R.id.dontShowAgain)

    init {
        val horizontalDialogButtonsMaxLength =
            context.resources.getInteger(R.integer.horizontal_dialog_buttons_max_length)
        UtilsComponentProvider.component.inject(this)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (builder.customView != null) {
            setContentView(builder.customView ?: 0)
        } else if (builder.multipleButtons.isNotEmpty()) {
            setContentView(R.layout.dialog_vertical_buttons)
            initButtons(builder.multipleButtons, builder.multipleButtonsWithCancel)
        } else if (builder.okButtonText?.length ?: 0 > horizontalDialogButtonsMaxLength || builder.cancelButtonText?.length ?: 0 > horizontalDialogButtonsMaxLength) {
            setContentView(R.layout.dialog_vertical_buttons)

            if (builder.isSuccessButtonInStart) {
                createOkButton()
            }
            if (!builder.isAlert) {
                View.inflate(context, R.layout.dialog_vertical_button_cancel, dialogButtonContainer)
            }
            if (!builder.isSuccessButtonInStart) {
                createOkButton()
            }
        } else {
            setContentView(R.layout.dialog)
        }

        initTitle()

        val dialogText = findViewById<TextView>(R.id.dialogText)
        if (builder.text?.isNotEmpty() == true) {
            dialogText.text = builder.text
            builder.movementMethod?.let { dialogText.movementMethod = it }
        }

        initOkButton(
            builder.okButtonText,
            context,
            builder.isAlert,
            builder.okCallback,
        )
        initCancelButton(
            builder.isAlert,
            builder.cancelButtonText,
            context,
            builder.cancelCallback,
        )

        setCancelable(builder.cancelable)

        setOnDismissListener { onDismissCallback() }
    }

    private fun createOkButton() {
        View.inflate(context, R.layout.dialog_vertical_button_ok, dialogButtonContainer)
    }

    private fun initTitle() {
        val dialogTitle = findViewById<TextView>(R.id.dialogTitle)

        if (builder.isTitleVisible) {
            if (builder.title?.isEmpty() != false) {
                val defaultTitle = context.getString(R.string.defaultDialogTitle)
                builder.title = defaultTitle
                if (defaultTitle.isEmpty()) dialogTitle.visibility = View.GONE
            }

            dialogTitle.text = builder.title
        } else {
            dialogTitle.visibility = View.GONE
        }
    }

    private fun initButtons(
        buttonList: List<Pair<String, Boolean>>,
        multipleButtonsWithCancel: Boolean
    ) {
        val buttonPadding = context.resources.getDimension(R.dimen.dialog_button_padding).toInt()
        buttonList.forEach { pair ->
            val button = View.inflate(
                context,
                if (pair.second) R.layout.dialog_vertical_button_positive else R.layout.dialog_vertical_button_negative,
                null
            ) as Button
            button.setPadding(buttonPadding)
            button.gravity = Gravity.CENTER_HORIZONTAL
            val buttonTextRes = pair.first
            button.text = buttonTextRes
            button.setOnClickListener {
                builder.multipleCallback?.invoke(buttonTextRes)
                dismiss()
            }
            dialogButtonContainer.addView(button)
        }

        if (multipleButtonsWithCancel) {
            val cancelButton = View.inflate(context, R.layout.dialog_vertical_button_cancel, null)
            cancelButton.setPadding(buttonPadding)
            dialogButtonContainer.addView(cancelButton)
        }
    }

    private fun initCancelButton(
        isAlert: Boolean,
        cancelButtonText: String?,
        context: Context,
        cancelCallback: (() -> Unit)?,
    ) {
        val dialogCancel = findViewById<View>(R.id.dialogCancel) ?: return
        val dialogButtonsDivider = findViewById<View>(R.id.dialogButtonsDivider)
        dialogCancel.isVisible = !isAlert
        dialogButtonsDivider?.isVisible = !isAlert

        if (dialogCancel is Button)
            if (cancelButtonText.isNullOrEmpty()) {
                dialogCancel.text = context.resources.getString(R.string.dialog_cancel)
            } else {
                dialogCancel.text = cancelButtonText
            }


        dialogCancel.setOnClickListener {
            cancelCallback?.invoke()
            dismiss()
        }
    }

    private fun initOkButton(
        okButtonText: String?,
        context: Context,
        isAlert: Boolean,
        okCallback: (() -> Unit)?
    ) {
        val dialogOk = findViewById<Button>(R.id.dialogOk) ?: return
        if (okButtonText.isNullOrEmpty()) {
            dialogOk.text = context.resources.getString(R.string.dialog_ok)
        } else {
            dialogOk.text = okButtonText
        }
        if (isAlert) {
            dialogOk.setAppearance(R.style.default_dialog_single_alert_button)
        }

        dialogOk.setOnClickListener {
            if (!builder.isAlert) okCallback?.invoke()
            dismiss()
        }
    }

    override fun show() {
        if (builder.inQueue) {
            dialogQueueManager.addToQueue(this)
        } else {
            super.show()
        }
    }

    override fun internalShow() {
        //TODO if need show dialog
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
//        dialogCustomShadowStateListener?.dialogCustomShadowVisibility(false)
        if (builder.isAlert) builder.okCallback?.invoke()
//        dialogSystemSubscription.unsubscribe()
    }


    class Builder(internal val context: Context?) : DialogBuilder {
        internal var title: String? = null
        internal var text: CharSequence? = null
        internal var movementMethod: MovementMethod? = null
        internal var okButtonText: String? = null
        internal var cancelButtonText: String? = null
        internal var isAlert: Boolean = false
        internal var cancelable: Boolean = false
        internal var okCallback: (() -> Unit)? = null
        internal var cancelCallback: (() -> Unit)? = null
        internal var multipleButtons: List<Pair<String, Boolean>> = emptyList()
        internal var multipleButtonsWithCancel: Boolean = false
        internal var customView: Int? = null
        internal var multipleCallback: ((String) -> Unit)? = null
        internal var inQueue: Boolean = false
        internal var isTitleVisible: Boolean = true
        internal var isAutoCloseable: Boolean = true
        internal var needToHideAfterScreenChanged: Boolean = false
        internal var isSuccessButtonInStart: Boolean = false

        fun setTitle(@StringRes title: Int): Builder {
            this.title = context?.getString(title)
            return this
        }

        fun setText(@StringRes text: Int): Builder {
            this.text = context?.getString(text)
            return this
        }

        fun isSuccessButtonInStart(isStart: Boolean): Builder {
            this.isSuccessButtonInStart = isStart
            return this
        }

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setText(text: String): Builder {
            this.text = text
            return this
        }

        fun setText(text: CharSequence): Builder {
            this.text = text
            return this
        }

        fun setMovementMethod(movementMethod: MovementMethod): Builder {
            this.movementMethod = movementMethod
            return this
        }

        fun setOkButtonText(okButtonText: String): Builder {
            this.okButtonText = okButtonText
            return this
        }

        fun setOkButtonText(@StringRes okButtonText: Int): Builder {
            this.okButtonText = context?.getString(okButtonText)
            return this
        }

        fun setCancelButtonText(cancelButtonText: String): Builder {
            this.cancelButtonText = cancelButtonText
            return this
        }


        fun setCancelButtonText(@StringRes cancelButtonText: Int): Builder {
            this.cancelButtonText = context?.getString(cancelButtonText)
            return this
        }

        fun isAlert(): Builder {
            isAlert = true
            return this
        }

        fun isCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun setOkCallback(okCallback: () -> Unit): Builder {
            this.okCallback = okCallback
            return this
        }

        fun setCancelCallback(cancelCallback: () -> Unit): Builder {
            this.cancelCallback = cancelCallback
            return this
        }

        fun setMultipleButtons(multipleButtons: List<Pair<String, Boolean>>): Builder {
            this.multipleButtons = multipleButtons
            return this
        }

        fun setMultipleCallback(multipleCallback: (String) -> Unit): Builder {
            this.multipleCallback = multipleCallback
            return this
        }

        fun setCustomView(layout: Int): Builder {
            this.customView = layout
            return this
        }

        fun hideTitle(): Builder {
            this.isTitleVisible = false
            return this
        }

        fun multipleButtonsWithCancel(): Builder {
            this.multipleButtonsWithCancel = true
            return this
        }

        fun setHidingAfterScreenChanged(): Builder {
            this.needToHideAfterScreenChanged = true
            return this
        }

        fun disableAutoCloseable(): Builder {
            this.isAutoCloseable = false
            return this
        }

        fun inQueue(): Builder {
            inQueue = true
            return this
        }

        override fun build(): Dialog {
            return CustomDialog(this)
        }
    }
}