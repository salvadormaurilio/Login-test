package buabap.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity): Long

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    suspend fun get(email :String, password: String): UserEntity?
}
