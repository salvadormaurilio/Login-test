package mx.android.buabap.data.datasource.local

import kotlinx.coroutines.flow.first
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
import org.mockito.Mockito.mock
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

        whenever(userDao.insert(userEntity)).thenReturn(ANY_ID)

        val result = authLocalDataSource.signUp(userEntity).first()

        verify(userDao).insert(userEntity)
        assertThatEquals(result.getOrNull(), true)
    }

    @Test
    fun `Get SignUpException when signUp is called and insert UserEntity is failure`() = runTest {
        val userEntity = givenUserEntity()

        whenever(userDao.insert(userEntity)).thenReturn(ANY_INVALID_ID)

        val result = authLocalDataSource.signUp(userEntity).first()

        verify(userDao).insert(userEntity)
        assertThatIsInstanceOf<AuthException.SignUpException>(result.exceptionOrNull())
    }

    @Test
    fun `Get success when signIn is called and get UserEntity is success`() = runTest {
        val userEntity = givenUserEntity()

        whenever(userDao.get(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(userEntity)

        val result = authLocalDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD).first()

        verify(userDao).get(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result.getOrNull(), userEntity)
    }

    @Test
    fun `Get SignInException when signIn is called and get UserEntity is null`() = runTest {
        whenever(userDao.get(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(null)

        val result = authLocalDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD).first()

        verify(userDao).get(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result.exceptionOrNull())
    }
}