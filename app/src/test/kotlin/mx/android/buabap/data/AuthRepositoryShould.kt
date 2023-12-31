package mx.android.buabap.data

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.core.assertThatIsInstanceOf
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.data.datasource.local.AuthLocalDataSource
import mx.android.buabap.data.datasource.local.database.UserEntity
import mx.android.buabap.givenUserCredentials
import mx.android.buabap.givenUserData
import mx.android.buabap.givenUserEntity
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRepositoryShould {

    private val authLocalDataSource = mock<AuthLocalDataSource>()

    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        authRepository = AuthRepository(authLocalDataSource)
    }

    @Test
    fun `Get success when signUp is called and signUp authLocalDataSource is success`() = runTest {
        val userEntity = givenUserEntity()
        val userCredentials = givenUserCredentials()
        val resultTrue = Result.success(true)

        whenever(authLocalDataSource.signUp(userEntity)).thenReturn(flowOf(resultTrue))

        val result = authRepository.signUp(userCredentials).lastOrNull()

        verify(authLocalDataSource).signUp(userEntity)
        assertThatEquals(result?.getOrNull(), true)
    }

    @Test
    fun `Get SignUpException when signUp is called and signUp authLocalDataSource  is failure`() = runTest {
        val userEntity = givenUserEntity()
        val userCredentials = givenUserCredentials()
        val resultSignUpException: Result<Boolean> = Result.failure(AuthException.SignUpException())

        whenever(authLocalDataSource.signUp(userEntity)).thenReturn(flowOf(resultSignUpException))

        val result = authRepository.signUp(userCredentials).lastOrNull()

        verify(authLocalDataSource).signUp(userEntity)
        assertThatIsInstanceOf<AuthException.SignUpException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get UserData when signIn is called and signIn authLocalDataSource is success`() = runTest {
        val userEntity = givenUserEntity()
        val userData = givenUserData()
        val resultUserEntity = Result.success(userEntity)

        whenever(authLocalDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserEntity))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authLocalDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), userData)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn authLocalDataSource is failure`() = runTest {
        val resultSignInException: Result<UserEntity> = Result.failure(AuthException.SignInException())

        whenever(authLocalDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignInException))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authLocalDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}