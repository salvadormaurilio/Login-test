package mx.android.buabap.data.datasource.local.database

import androidx.annotation.VisibleForTesting
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity): Long

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    suspend fun get(email: String, password: String): UserEntity?

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email = :email)")
    fun exist(email: String): Boolean

    @VisibleForTesting
    @Query("DELETE FROM user")
    suspend fun delete(): Int
}
