package mx.android.buabap.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.android.buabap.domain.UserData

@Entity(tableName = TABLE_USER)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val password: String
)

fun Result<UserEntity>.toResultUserData() = map { it.toUserEntity() }

fun UserEntity.toUserEntity() = UserData(
    name = name,
    email = email
)