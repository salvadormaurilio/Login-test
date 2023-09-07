package mx.android.buabap.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = DATABASE_USER_VERSION,
    exportSchema = false
)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao


    companion object {

        @Volatile
        private var instance: UserRoomDatabase? = null

        fun getInstance(context: Context): UserRoomDatabase = instance ?: synchronized(this) {
            instance ?: build(context).also { instance = it }
        }

        private fun build(context: Context) =
            Room.databaseBuilder(context, UserRoomDatabase::class.java, DATABASE_USER_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
