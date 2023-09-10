package mx.android.buabap.ui.auth

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import mx.android.buabap.R
import org.junit.Test

class AuthFragmentShould {

    @Test
    fun displayViewsWhenAuthFragmentStart() {
        launchActivity<AuthActivity>()

        onView(withId(R.id.logo_image_view)).check(matches(isDisplayed()))
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed()))
        onView(withId(R.id.sign_up_button)).check(matches(isDisplayed()))
    }

    @Test
    fun openSingUpFragmentWhenSingUpButtonIsClicked() {
        launchActivity<AuthActivity>()

        onView(withId(R.id.sign_up_button)).perform(click())

        onView(withId(R.id.sign_up_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun openSingInFragmentWhenSingInButtonIsClicked() {
        launchActivity<AuthActivity>()

        onView(withId(R.id.sign_in_button)).perform(click())

        onView(withId(R.id.sign_in_text_view)).check(matches(isDisplayed()))
    }
}