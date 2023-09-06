package mx.android.buabap.datasource.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import mx.android.buabap.datasource.local.core.RoomRule
import org.junit.Before
import org.junit.Rule
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
}