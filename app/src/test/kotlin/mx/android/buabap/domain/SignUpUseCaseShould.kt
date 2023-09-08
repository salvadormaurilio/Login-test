package mx.android.buabap.domain

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.core.assertThatIsInstanceOf
import mx.android.buabap.data.AuthRepository
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.givenUserCredentials
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SignUpUseCaseShould {

    private val authRepository = Mockito.mock<AuthRepository>()

    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setup() {
        signUpUseCase = SignUpUseCase(authRepository)
    }

    @Test
    fun `Get success when signUp is called and signUp authRepository is success`() = runTest {
        val userCredentials = givenUserCredentials()
        val resultTrue = Result.success(true)

        whenever(authRepository.signUp(userCredentials)).thenReturn(flowOf(resultTrue))

        val result = authRepository.signUp(userCredentials).first()

        verify(authRepository).signUp(userCredentials)
        assertThatEquals(result.getOrNull(), true)
    }

    @Test
    fun `Get SignUpException when signUp is called and signUp authRepository is failure`() = runTest {
        val userCredentials = givenUserCredentials()
        val resultSignUpException: Result<Boolean> = Result.failure(AuthException.SignUpException())

        whenever(authRepository.signUp(userCredentials)).thenReturn(flowOf(resultSignUpException))

        val result = authRepository.signUp(userCredentials).first()

        verify(authRepository).signUp(userCredentials)
        assertThatIsInstanceOf<AuthException.SignUpException>(result.exceptionOrNull())
    }
}