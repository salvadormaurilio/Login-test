package mx.android.buabap.core

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RoomRule<out T : RoomDatabase>(private val roomClass: Class<T>) : TestRule {

    private lateinit var roomDatabase: RoomDatabase

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                roomDatabase = buildInMemoryDatabase()
                base.evaluate()
                roomDatabase.close()
            }
        }
    }

    private fun buildInMemoryDatabase() =
            Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, roomClass)
                    .allowMainThreadQueries().build()

    @Suppress("UNCHECKED_CAST")
    fun database() = roomDatabase as T

    companion object {

        inline fun <reified R : RoomDatabase> build() = RoomRule(R::class.java)
    }
}
