package mx.android.buabap.core.matcher

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

object LoginErrorTextMatchers {

    fun withErrorText(expectedErrorText: String): TypeSafeMatcher<View?> {
        return object : TypeSafeMatcher<View?>() {
            override fun matchesSafely(view: View?): Boolean {
                if (view !is TextInputLayout) return false

                val error = view.error ?: return false

                val hint = error.toString()

                return expectedErrorText == hint
            }

            override fun describeTo(description: Description) {}
        }
    }
}