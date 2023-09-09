package mx.android.buabap.core.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import mx.android.buabap.R

inline fun <reified T : AppCompatActivity> Context.intentTo() = Intent(this, T::class.java)

fun Context.showAlertDialog(message: String, positiveButton: (() -> Unit)? = null): AlertDialog =
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(R.string.accept) { _, _ -> positiveButton?.invoke() }
        .show()