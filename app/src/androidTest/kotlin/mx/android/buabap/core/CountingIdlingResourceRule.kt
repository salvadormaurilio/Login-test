package mx.android.buabap.core

import androidx.test.espresso.IdlingRegistry
import mx.android.buabap.core.test.CountingIdlingResourceSingleton
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CountingIdlingResourceRule() : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
                base.evaluate()
                IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
            }
        }
    }
}
