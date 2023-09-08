package mx.android.buabap.ui.auth

import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp
import org.junit.Before
import org.junit.Test

class AuthViewModelShould {

    private lateinit var authViewModel: AuthViewModel

    @Before
    fun setup() {
        authViewModel = AuthViewModel()
    }

    @Test
    fun `navigate to ignUp when navigateToSingUp is called`() {
        authViewModel.navigateToSingUp()

        val value = authViewModel.navigateToAuthAction.value

        assertThatEquals(value, SignUp)
    }

    @Test
    fun `navigate to SignIn when navigateToSingIn is called`() {
        authViewModel.navigateToSingIn()

        val value = authViewModel.navigateToAuthAction.value

        assertThatEquals(value, SignIn)
    }
}
