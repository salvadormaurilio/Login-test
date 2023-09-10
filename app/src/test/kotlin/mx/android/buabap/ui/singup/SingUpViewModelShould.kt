package mx.android.buabap.ui.singup

import kotlinx.coroutines.flow.firstOrNull
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SingUpViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val authExceptionHandler = AuthExceptionHandler()
    private val signUpUseCase = mock<SignUpUseCase>()

    private lateinit var singUpViewModel: SingUpViewModel

    @Before
    fun setup() {
        singUpViewModel = SingUpViewModel(
            authExceptionHandler,
            signUpUseCase,
            testDispatcherRule.coroutinesDispatchers
        )
    }

    @Test
    fun `get EmailException from signUpUiState when singUp is called and email is invalid`() = runTest {
        val userCredentialsUi = givenUserCredentialsUiWithInvalidEmail()
        val userCredentials = givenUserCredentials()

        singUpViewModel.singUp(userCredentialsUi)

        val result = singUpViewModel.signUpUiState.firstOrNull()

        verify(signUpUseCase, never()).signUp(userCredentials)
        assertThatIsInstanceOf<SignUpUiState.Error>(result)
        assertThatIsInstanceOf<AuthUiException.EmailException>((result as SignUpUiState.Error).error)
    }

    @Test
    fun `get true from signUpUiState when singUp is called and signUpUseCase is success`() = runTest {
        val userCredentialsUi = givenUserCredentialsUi()
        val userCredentials = givenUserCredentials()
        val resultTrue = Result.success(true)

        whenever(signUpUseCase.signUp(userCredentials)).thenReturn(flowOf(resultTrue))

        singUpViewModel.singUp(userCredentialsUi)

        val result = singUpViewModel.signUpUiState.firstOrNull()

        verify(signUpUseCase).signUp(userCredentials)
        assertThatIsInstanceOf<SignUpUiState.Success>(result)
    }

    @Test
    fun `get SignUpException from signUpUiState when singUp is called and signUpUseCase is failure`() = runTest {
        val userCredentialsUi = givenUserCredentialsUi()
        val userCredentials = givenUserCredentials()
        val resultSignUpException: Result<Boolean> = Result.failure(AuthException.SignUpException())

        whenever(signUpUseCase.signUp(userCredentials)).thenReturn(flowOf(resultSignUpException))

        singUpViewModel.singUp(userCredentialsUi)

        val result = singUpViewModel.signUpUiState.firstOrNull()

        verify(signUpUseCase).signUp(userCredentials)
        assertThatIsInstanceOf<SignUpUiState.Error>(result)
        assertThatIsInstanceOf<AuthException.SignUpException>((result as SignUpUiState.Error).error)
    }
}