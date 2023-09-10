package mx.android.buabap.ui.auth

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import mx.android.buabap.R
import org.junit.Rule
import org.junit.Test

class AuthActivityShould {

    @get:Rule
    val intentRule = IntentsRule()

    @Test
    fun displayViewsWhenAuthActivityStart() {
        launchActivity<AuthActivity>()

        onView(withId(R.id.logo_image_view)).check(matches(isDisplayed()))
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed()))
        onView(withId(R.id.sign_up_button)).check(matches(isDisplayed()))
    }

    @Test
    fun openSingUpActivityWhenSingUpButtonIsClicked() {
        launchActivity<AuthActivity>()

        onView(withId(R.id.sign_up_button)).perform(click())

        intended(hasComponent(SingUpActivity::class.java.name))
    }

    @Test
    fun openSingInActivityWhenSingInButtonIsClicked() {
        launchActivity<AuthActivity>()

        onView(withId(R.id.sign_in_button)).perform(click())

        intended(hasComponent(SingInActivity::class.java.name))
    }
}