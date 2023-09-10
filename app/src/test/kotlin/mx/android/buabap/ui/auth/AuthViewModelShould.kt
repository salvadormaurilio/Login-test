package mx.android.buabap.ui.auth

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import mx.android.buabap.core.TestDispatcherRule
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private lateinit var authViewModel: AuthViewModel

    @Before
    fun setup() {
        authViewModel = AuthViewModel()
    }

    @Test
    fun `navigate to signUp when navigateToSingUp is called`() = runTest {
        var result: AuthAction? = null

        authViewModel.viewModelScope.launch {
            result = authViewModel.navigateToAuthAction.firstOrNull()
        }

        authViewModel.navigateToSingUp()

        assertThatEquals(result, SignUp)
    }

    @Test
    fun `navigate to SignIn when navigateToSingIn is called`() = runTest {
        var result: AuthAction? = null

        authViewModel.viewModelScope.launch {
            result = authViewModel.navigateToAuthAction.firstOrNull()
        }

        authViewModel.navigateToSingIn()

        assertThatEquals(result, SignIn)
    }
}
