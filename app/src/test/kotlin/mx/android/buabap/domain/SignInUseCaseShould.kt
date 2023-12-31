package mx.android.buabap.domain

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.core.assertThatIsInstanceOf
import mx.android.buabap.data.AuthRepository
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.givenUserData
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SignInUseCaseShould {

    private val authRepository = mock<AuthRepository>()

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setup() {
        signInUseCase = SignInUseCase(authRepository)
    }

    @Test
    fun `Get UserData when signIn is called and signIn authRepository is success`() = runTest {
        val userData = givenUserData()
        val resultUserData = Result.success(userData)

        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserData))

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), userData)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn authRepository is failure`() = runTest {
        val resultSignInException: Result<UserData> = Result.failure(AuthException.SignInException())

        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignInException))

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}