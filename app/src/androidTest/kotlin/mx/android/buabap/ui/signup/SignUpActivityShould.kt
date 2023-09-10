package mx.android.buabap.ui.signup

import android.content.Context
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import mx.android.buabap.ANY_INVALID_NAME
import mx.android.buabap.ANY_INVALID_PASSWORD
import mx.android.buabap.ANY_INVALID_USER_EMAIL
import mx.android.buabap.ANY_NAME
import mx.android.buabap.ANY_OTHER_PASSWORD
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.R
import mx.android.buabap.core.TestDispatcherRule
import mx.android.buabap.core.matcher.LoginErrorTextMatchers.withErrorText
import mx.android.buabap.ui.singup.SingUpActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpActivityShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

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

    @Test
    fun displayErrorNameInvalidWhenTypeInvalidName() {
        launchActivity<SingUpActivity>()
        val context = getApplicationContext<Context>()
        val nameInvalid = context.getString(R.string.error_name_invalid)

        onView(withId(R.id.name_edit_text)).perform(typeText(ANY_INVALID_NAME), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()
        onView(withId(R.id.confirm_sign_up_button)).perform(click())

        onView(withId(R.id.name_input_layout)).check(matches(withErrorText(nameInvalid)))
    }

    @Test
    fun displayErrorEmailInvalidWhenTypeInvalidEmail() {
        launchActivity<SingUpActivity>()
        val context = getApplicationContext<Context>()
        val emailInvalid = context.getString(R.string.error_email_invalid)

        onView(withId(R.id.name_edit_text)).perform(typeText(ANY_NAME), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_INVALID_USER_EMAIL), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()
        onView(withId(R.id.confirm_sign_up_button)).perform(click())

        onView(withId(R.id.email_input_layout)).check(matches(withErrorText(emailInvalid)))
    }

    @Test
    fun displayErrorPasswordInvalidWhenTypeInvalidPassword() {
        launchActivity<SingUpActivity>()
        val context = getApplicationContext<Context>()
        val passwordInvalid = context.getString(R.string.error_password_invalid)

        onView(withId(R.id.name_edit_text)).perform(typeText(ANY_NAME), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_INVALID_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()

        onView(withId(R.id.confirm_sign_up_button)).perform(click())

        onView(withId(R.id.password_input_layout)).check(matches(withErrorText(passwordInvalid)))
    }

    @Test
    fun displayErrorConfirmPasswordInvalidWhenTypeInvalidPassword() {
        launchActivity<SingUpActivity>()
        val context = getApplicationContext<Context>()
        val passwordInvalid = context.getString(R.string.error_password_invalid)

        onView(withId(R.id.name_edit_text)).perform(typeText(ANY_NAME), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_PASSWORD), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.confirm_password_edit_text)).perform(typeText(ANY_INVALID_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()
        onView(withId(R.id.confirm_sign_up_button)).perform(click())

        onView(withId(R.id.confirm_password_input_layout)).check(matches(withErrorText(passwordInvalid)))
    }

    @Test
    fun displayErrorDifferentPasswordInvalidWhenTypeInvalidPassword() {
        launchActivity<SingUpActivity>()
        val context = getApplicationContext<Context>()
        val passwordDifferent = context.getString(R.string.error_password_different)

        onView(withId(R.id.name_edit_text)).perform(typeText(ANY_NAME), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_PASSWORD), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.confirm_password_edit_text)).perform(typeText(ANY_OTHER_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()
        onView(withId(R.id.confirm_sign_up_button)).perform(click())

        onView(withId(R.id.confirm_password_input_layout)).check(matches(withErrorText(passwordDifferent)))
    }

//    @Test
//    fun test1() = runTest{
//        launchActivity<SingUpActivity>()
//        val context = getApplicationContext<Context>()
//        val test = context.getString(R.string.success_sign_up)
//
//        onView(withId(R.id.name_edit_text)).perform(typeText(ANY_NAME), pressKey(KEYCODE_ENTER))
//        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
//        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_PASSWORD), pressKey(KEYCODE_ENTER))
//        onView(withId(R.id.confirm_password_edit_text)).perform(typeText(ANY_PASSWORD), pressKey(KEYCODE_ENTER))
//        closeSoftKeyboard()
//        onView(withId(R.id.confirm_sign_up_button)).perform(click())
//        SystemClock.sleep(DELAY_RESPONSE)
//
//        onView(withId(R.id.sign_up_text_view)).check(matches(withText(test)))
//    }
}