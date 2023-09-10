package mx.android.buabap.ui.singin

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
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import mx.android.buabap.ANY_INVALID_PASSWORD
import mx.android.buabap.ANY_INVALID_USER_EMAIL
import mx.android.buabap.ANY_NAME
import mx.android.buabap.ANY_OTHER_PASSWORD
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.R
import mx.android.buabap.core.CountingIdlingResourceRule
import mx.android.buabap.core.matcher.LoginErrorTextMatchers.withErrorText
import mx.android.buabap.data.datasource.local.database.DATABASE_USER_NAME
import mx.android.buabap.ui.auth.AuthActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInFragmentShould {

    @get:Rule
    val countingIdlingResourceRule = CountingIdlingResourceRule()

    @Before
    fun setUp() {
        getApplicationContext<Context>().deleteDatabase(DATABASE_USER_NAME);
    }

    @Test
    fun displayViewsWhenSingUpFragmentStart() {
        launchActivity<AuthActivity>()
        onView(withId(R.id.sign_in_button)).perform(click())

        onView(withId(R.id.sign_in_text_view)).check(matches(isDisplayed()))
        onView(withId(R.id.email_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.confirm_sign_in_button)).check(matches(isDisplayed()))
    }

    @Test
    fun displayErrorEmailInvalidWhenTypeInvalidEmail() {
        val context = getApplicationContext<Context>()
        val emailInvalidText = context.getString(R.string.error_email_invalid)

        launchActivity<AuthActivity>()
        onView(withId(R.id.sign_in_button)).perform(click())

        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_INVALID_USER_EMAIL), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()

        onView(withId(R.id.confirm_sign_in_button)).perform(click())

        onView(withId(R.id.email_input_layout)).check(matches(withErrorText(emailInvalidText)))
    }

    @Test
    fun displayErrorPasswordInvalidWhenTypeInvalidPassword() {
        val context = getApplicationContext<Context>()
        val passwordInvalidText = context.getString(R.string.error_password_invalid)

        launchActivity<AuthActivity>()
        onView(withId(R.id.sign_in_button)).perform(click())

        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_INVALID_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()
        onView(withId(R.id.confirm_sign_in_button)).perform(click())

        onView(withId(R.id.password_input_layout)).check(matches(withErrorText(passwordInvalidText)))
    }

    @Test
    fun displaySuccessSingInWhenTypeSingInIsSuccess() {
        val context = getApplicationContext<Context>()
        val successSingInText = context.getString(R.string.success_sign_in, ANY_NAME, ANY_USER_EMAIL)

        //First Register some user
        val scenario = launchActivity<AuthActivity>()
        onView(withId(R.id.sign_up_button)).perform(click())

        onView(withId(R.id.name_edit_text)).perform(typeText(ANY_NAME), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_PASSWORD), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.confirm_password_edit_text)).perform(typeText(ANY_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()
        onView(withId(R.id.confirm_sign_up_button)).perform(click())

        scenario.close()

        //Then validate Sign In
        launchActivity<AuthActivity>()
        onView(withId(R.id.sign_in_button)).perform(click())

        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()

        onView(withId(R.id.confirm_sign_in_button)).perform(click())

        onView(withText(successSingInText)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    @Test
    fun displayErrorSingInWhenTypeSingInIsFailureAndUserAlreadyExist() {
        val context = getApplicationContext<Context>()
        val errorSingInText = context.getString(R.string.error_sign_in)

        launchActivity<AuthActivity>()
        onView(withId(R.id.sign_in_button)).perform(click())

        onView(withId(R.id.email_edit_text)).perform(typeText(ANY_USER_EMAIL), pressKey(KEYCODE_ENTER))
        onView(withId(R.id.password_edit_text)).perform(typeText(ANY_OTHER_PASSWORD), pressKey(KEYCODE_ENTER))
        closeSoftKeyboard()

        onView(withId(R.id.confirm_sign_in_button)).perform(click())

        onView(withText(errorSingInText)).inRoot(isDialog()).check(matches(isDisplayed()))
    }
}