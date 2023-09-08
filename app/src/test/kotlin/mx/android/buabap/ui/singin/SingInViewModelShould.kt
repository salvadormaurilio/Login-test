package mx.android.buabap.ui.singin

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mx.android.buabap.ANY_INVALID_USER_EMAIL
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.core.TestDispatcherRule
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.core.assertThatIsInstanceOf
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.domain.SignInUseCase
import mx.android.buabap.domain.UserData
import mx.android.buabap.givenUserData
import mx.android.buabap.ui.exception.AuthExceptionHandler
import mx.android.buabap.ui.exception.AuthUiException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SingInViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val authExceptionHandler = AuthExceptionHandler()
    private val signInUseCase = Mockito.mock<SignInUseCase>()

    private lateinit var singInViewModel: SingInViewModel

    @Before
    fun setup() {
        singInViewModel = SingInViewModel(authExceptionHandler, signInUseCase)
    }

    @Test
    fun `Test 1`() = runTest {
        singInViewModel.signIn(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiState.first()

        verify(signInUseCase, never()).signIn(ANY_INVALID_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiState.Error>(result)
        assertThatIsInstanceOf<AuthUiException.Email>((result as SignInUiState.Error).error)
    }

    @Test
    fun `Test 2`() = runTest {
        val userData = givenUserData()
        val resultUserData = Result.success(userData)

        whenever(signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserData))

        singInViewModel.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiState.first()

        verify(signInUseCase).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiState.Success>(result)
        assertThatEquals((result as SignInUiState.Success).userData, userData)
    }

    @Test
    fun `Test 3`() = runTest {
        val resultSignInException: Result<UserData> = Result.failure(AuthException.SignInException())

        whenever(signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignInException))

        singInViewModel.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        val result = singInViewModel.signInUiState.first()

        verify(signInUseCase).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<SignInUiState.Error>(result)
        assertThatIsInstanceOf<AuthException.SignInException>((result as SignInUiState.Error).error)
    }
}