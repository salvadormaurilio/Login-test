package mx.android.buabap.core.ui

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE


fun View.showOrHide(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}