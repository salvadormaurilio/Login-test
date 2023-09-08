package mx.android.buabap.core.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : AppCompatActivity> intentTo(context: Context?): Intent = Intent(context, T::class.java)
