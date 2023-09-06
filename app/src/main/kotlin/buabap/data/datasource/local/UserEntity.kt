package buabap.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_USER)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val password: String
)
