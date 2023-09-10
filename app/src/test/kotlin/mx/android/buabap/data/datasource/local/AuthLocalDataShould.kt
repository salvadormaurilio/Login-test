package mx.android.buabap.data.datasource.local

import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import mx.android.buabap.ANY_ID
import mx.android.buabap.ANY_INVALID_ID
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.core.assertThatIsInstanceOf
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.data.datasource.local.database.UserDao
import mx.android.buabap.givenUserEntity
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthLocalDataShould {

    private val userDao = mock<UserDao>()

    private lateinit var authLocalDataSource: AuthLocalDataSource

    @Before
    fun setup() {
        authLocalDataSource = AuthLocalDataSource(userDao)
    }

    @Test
    fun `Get success when signUp is called and insert UserEntity is success`() = runTest {
        val userEntity = givenUserEntity()

        whenever(userDao.exist(ANY_USER_EMAIL)).thenReturn(false)
        whenever(userDao.insert(userEntity)).thenReturn(ANY_ID)

        val result = authLocalDataSource.signUp(userEntity).lastOrNull()

        verify(userDao).exist(ANY_USER_EMAIL)
        verify(userDao).insert(userEntity)
        assertThatEquals(result?.getOrNull(), true)
    }

    @Test
    fun `Get UserAlreadyExistException when signUp is called and user already exist in data base`() = runTest {
        val userEntity = givenUserEntity()

        whenever(userDao.exist(ANY_USER_EMAIL)).thenReturn(true)

        val result = authLocalDataSource.signUp(userEntity).lastOrNull()

        verify(userDao).exist(ANY_USER_EMAIL)
        verify(userDao, never()).insert(userEntity)
        assertThatIsInstanceOf<AuthException.UserAlreadyExistException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get SignUpException when signUp is called and insert UserEntity is failure`() = runTest {
        val userEntity = givenUserEntity()

        whenever(userDao.exist(ANY_USER_EMAIL)).thenReturn(false)
        whenever(userDao.insert(userEntity)).thenReturn(ANY_INVALID_ID)

        val result = authLocalDataSource.signUp(userEntity).lastOrNull()

        verify(userDao).exist(ANY_USER_EMAIL)
        verify(userDao).insert(userEntity)
        assertThatIsInstanceOf<AuthException.SignUpException>(result?.exceptionOrNull())
    }

    @Test
    fun `Get success when signIn is called and get UserEntity is success`() = runTest {
        val userEntity = givenUserEntity()

        whenever(userDao.get(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(userEntity)

        val result = authLocalDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(userDao).get(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), userEntity)
    }

    @Test
    fun `Get SignInException when signIn is called and get UserEntity is null`() = runTest {
        whenever(userDao.get(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(null)

        val result = authLocalDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(userDao).get(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}