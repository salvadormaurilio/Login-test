package mx.android.buabap.ui.singin

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import mx.android.buabap.R
import org.junit.Test

class SignInActivityShould {

    @Test
    fun displayViewsWhenSingUpActivityStart() {
        launchActivity<SingInActivity>()

        onView(withId(R.id.sign_in_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.email_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.confirm_sign_in_button)).check(matches(isDisplayed()))
    }
}