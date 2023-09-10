package mx.android.buabap.data.datasource.local.database

import kotlinx.coroutines.test.runTest
import mx.android.buabap.ANY_AUTO_ID
import mx.android.buabap.ANY_INVALID_PASSWORD
import mx.android.buabap.ANY_PASSWORD
import mx.android.buabap.ANY_USER_EMAIL
import mx.android.buabap.core.RoomRule
import mx.android.buabap.core.matcher.assertIsNull
import mx.android.buabap.core.matcher.assertThatEquals
import mx.android.buabap.givenUserEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDaoShould {

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
    fun getUserEntityFromUserDaoWhenCredentialsAreValid() = runTest {
        val userEntity = givenUserEntity()

        userDao.insert(userEntity)
        val userEntityResult = userDao.get(ANY_USER_EMAIL, ANY_PASSWORD)

        assertThatEquals(userEntityResult, userEntity)
    }

    @Test
    fun getNullFromUserDaoWhenCredentialsAreInvalid() = runTest {
        val userEntity = givenUserEntity()

        userDao.insert(userEntity)
        val userEntityResult = userDao.get(ANY_USER_EMAIL, ANY_INVALID_PASSWORD)

        assertIsNull(userEntityResult)
    }

    @Test
    fun getTrueFromUserDaoWhenUserAlreadyExist() = runTest {
        val userEntity = givenUserEntity()

        userDao.insert(userEntity)

        val result = userDao.exist(ANY_USER_EMAIL)

        assertThatEquals(result, true)
    }

    @Test
    fun getFalseFromUserDaoWhenUserNotExist() = runTest {
        val result = userDao.exist(ANY_USER_EMAIL)

        assertThatEquals(result, false)
    }
}