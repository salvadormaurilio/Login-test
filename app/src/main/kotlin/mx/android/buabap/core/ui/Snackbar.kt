package mx.android.buabap.core.ui

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import mx.android.buabap.R


fun View.snackbar(text: String?, duration: Int = LENGTH_SHORT) = Snackbar.make(this, text.orEmpty(), duration).apply { show() }

fun View.snackbar(@StringRes resId: Int, duration: Int = LENGTH_SHORT) = Snackbar.make(this, resId, duration).apply { show() }

fun Snackbar.showSuccess() = apply { setBackgroundTint(context.getColor(R.color.green_snack_bar_success)) }

fun Snackbar.showError() = apply { setBackgroundTint(context.getColor(R.color.red_snack_bar_error)) }