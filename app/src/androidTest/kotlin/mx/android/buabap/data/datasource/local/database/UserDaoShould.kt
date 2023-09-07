package mx.android.buabap.data.datasource.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runTest
import mx.android.buabap.core.RoomRule
import mx.android.buabap.core.assertIsNull
import mx.android.buabap.core.assertThatEquals
import mx.android.buabap.data.datasource.local.ANY_AUTO_ID
import mx.android.buabap.data.datasource.local.ANY_INVALID_PASSWORD
import mx.android.buabap.data.datasource.local.ANY_PASSWORD
import mx.android.buabap.data.datasource.local.ANY_USER_EMAIL
import mx.android.buabap.data.datasource.local.givenUserEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoShould {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val userRoomDatabase = RoomRule.build<UserRoomDatabase>()

    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        userDao = userRoomDatabase.database().userDao()
    }

    @Test
    fun insertUserEntityInUserDao() = runTest {
        val userEntity = givenUserEntity()

        val row = userDao.insert(userEntity)

        assertThatEquals(row, ANY_AUTO_ID)
    }

    @Test
    fun geeUserEntityFromUserDaoWhenCredentialsAreValid() = runTest {
        val userEntity = givenUserEntity()

        userDao.insert(userEntity)
        val userEntityResult = userDao.get(ANY_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(userEntityResult, userEntity)
    }

    @Test
    fun geeUserEntityFromUserDaoWhenCredentialsAreInvalid() = runTest {
        val userEntity = givenUserEntity()

        userDao.insert(userEntity)
        val userEntityResult = userDao.get(ANY_USER_EMAIL, ANY_INVALID_PASSWORD)

        assertIsNull(userEntityResult)
    }
}