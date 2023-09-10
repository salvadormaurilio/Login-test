package mx.android.buabap.core.ui

import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController.safeNavigate(@IdRes resId: Int) = try {
    navigate(resId)
} catch (e: Exception) {
    e.printStackTrace()
}
