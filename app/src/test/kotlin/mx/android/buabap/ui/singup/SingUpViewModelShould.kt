package mx.android.buabap.ui.singup

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mx.android.buabap.core.TestDispatcherRule
import mx.android.buabap.core.assertThatIsInstanceOf
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.domain.SignUpUseCase
import mx.android.buabap.givenUserCredentials
import mx.android.buabap.givenUserCredentialsUi
import mx.android.buabap.givenUserCredentialsUiWithInvalidEmail
import mx.android.buabap.ui.exception.AuthExceptionHandler
import mx.android.buabap.ui.exception.AuthUiException
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SingUpViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val authExceptionHandler = AuthExceptionHandler()
    private val signUpUseCase = Mockito.mock<SignUpUseCase>()

    private lateinit var singUpViewModel: SingUpViewModel

    @Before
    fun setup() {
        singUpViewModel = SingUpViewModel(authExceptionHandler, signUpUseCase)
    }

    @Test
    fun `Test 1`() = runTest {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidEmail()
        val userCredentials = givenUserCredentials()

        singUpViewModel.singUp(userCredentialsUi)

        val result = singUpViewModel.signUpUiState.first()

        verify(signUpUseCase, never()).signUp(userCredentials)
        assertThatIsInstanceOf<SignUpUiState.Error>(result)
        assertThatIsInstanceOf<AuthUiException.Email>((result as SignUpUiState.Error).error)
    }

    @Test
    fun `Test 2`() = runTest {
        val userCredentialsUi = givenUserCredentialsUi()
        val userCredentials = givenUserCredentials()
        val resultTrue = Result.success(true)

        whenever(signUpUseCase.signUp(userCredentials)).thenReturn(flowOf(resultTrue))

        singUpViewModel.singUp(userCredentialsUi)

        val result = singUpViewModel.signUpUiState.first()

        verify(signUpUseCase).signUp(userCredentials)
        assertThatIsInstanceOf<SignUpUiState.Success>(result)
    }

    @Test
    fun `Test 3`() = runTest {
        val userCredentialsUi = givenUserCredentialsUi()
        val userCredentials = givenUserCredentials()
        val resultSignUpException: Result<Boolean> = Result.failure(AuthException.SignUpException())

        whenever(signUpUseCase.signUp(userCredentials)).thenReturn(flowOf(resultSignUpException))

        singUpViewModel.singUp(userCredentialsUi)

        val result = singUpViewModel.signUpUiState.first()

        verify(signUpUseCase).signUp(userCredentials)
        assertThatIsInstanceOf<SignUpUiState.Error>(result)
        assertThatIsInstanceOf<AuthException.SignUpException>((result as SignUpUiState.Error).error)
    }
}