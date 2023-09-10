package mx.android.buabap.ui.singin

import android.content.Context
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import mx.android.buabap.ANY_INVALID_PASSWORD
import mx.android.buabap.ANY_INVALID_USER_EMAIL
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.R
import mx.android.buabap.core.matcher.LoginErrorTextMatchers.withErrorText
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

    @Test
    fun displayErrorEmailInvalidWhenTypeInvalidEmail() {
        launchActivity<SingInActivity>()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val emailInvalid = context.getString(R.string.error_email_invalid)

        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_INVALID_USER_EMAIL), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()

        onView(withId(R.id.confirm_sign_in_button)).perform(click())

        onView(withId(R.id.email_input_layout)).check(matches(withErrorText(emailInvalid)))
    }

    @Test
    fun displayErrorPasswordInvalidWhenTypeInvalidPassword() {
        launchActivity<SingInActivity>()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val passwordInvalid = context.getString(R.string.error_password_invalid)

        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_INVALID_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()
        onView(withId(R.id.confirm_sign_in_button)).perform(click())

        onView(withId(R.id.password_input_layout)).check(matches(withErrorText(passwordInvalid)))
    }

}