package mx.android.buabap.data.datasource.local

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import mx.android.buabap.ANY_AUTO_ID
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.core.assertThatIsInstanceOf
import mx.android.buabap.data.datasource.exception.SignUpException
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
    fun `Get success when signUp is called and insert is success`() = runTest {
        val userEntity = givenUserEntity()
        whenever(userDao.insert(userEntity)).thenReturn(ANY_AUTO_ID)

        val result = authLocalDataSource.signUp(userEntity).first()

        verify(userDao).insert(userEntity)
        assertThatEquals(result.getOrNull(), true)
    }

    @Test
    fun `Get SignUpException when signUp is called and insert is failure`() = runTest {
        val userEntity = givenUserEntity()
        whenever(userDao.insert(userEntity)).thenReturn(-1L)

        val result = authLocalDataSource.signUp(userEntity).first()

        verify(userDao).insert(userEntity)
        assertThatIsInstanceOf<SignUpException>(result.exceptionOrNull())
    }
}