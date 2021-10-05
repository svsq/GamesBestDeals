package tk.svsq.gamesbestdeals.presentation.ui.common.dialogs

import android.content.Context
import android.text.InputType
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import tk.svsq.gamesbestdeals.R

object EditEmailDialog {

    fun show(
        context: Context,
        message: String,
        title: String? = null,
        okListener: ((String) -> Unit)? = null,
        okText: String? = null,
        negativeListener: (() -> Unit)? = null,
        negativeText: String? = null,
        isCancellable: Boolean = true
    ) {
        val messageDialog: AlertDialog
        AlertDialog.Builder(context, R.style.CustomDialogTheme)
            .apply {
                val positiveText =
                    if (TextUtils.isEmpty(okText)) context.getString(R.string.ok_text)
                    else okText
                setTitle(title)
                setMessage(message)

                val input = EditText(this.context)
                input.hint = "Enter email"
                input.inputType = InputType.TYPE_CLASS_TEXT
                input.setPadding(60, 0, 60, 50)

                setView(input)

                setCancelable(isCancellable)
                setPositiveButton(positiveText) { dialog, _ ->
                    if (Patterns.EMAIL_ADDRESS.matcher(input.text.toString()).matches()) {
                        dialog.dismiss()
                        okListener?.invoke(input.text.toString())
                    } else {
                        Toast.makeText(context, "wrong email", Toast.LENGTH_SHORT).show()
                    }
                }
                if (!TextUtils.isEmpty(negativeText)) {
                    setNegativeButton(negativeText) { dialog, _ ->
                        dialog.dismiss()
                        negativeListener?.invoke()
                    }
                }
                messageDialog = this.create()
                messageDialog.show()
            }

        val color = ContextCompat.getColor(context, R.color.purple_200)
        (messageDialog.getButton(AlertDialog.BUTTON_POSITIVE) as Button).setTextColor(color)
        (messageDialog.getButton(AlertDialog.BUTTON_NEGATIVE) as Button).setTextColor(color)
    }

}