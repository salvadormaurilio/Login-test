package mx.android.buabap.ui.signup

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import mx.android.buabap.R
import mx.android.buabap.ui.singup.SingUpActivity
import org.junit.Test

class SignUpActivityShould {

    @Test
    fun displayViewsWhenSingUpActivityStart() {
        launchActivity<SingUpActivity>()

        onView(withId(R.id.sign_up_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.name_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.email_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.confirm_password_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.confirm_sign_up_button)).check(matches(isDisplayed()))
    }

}