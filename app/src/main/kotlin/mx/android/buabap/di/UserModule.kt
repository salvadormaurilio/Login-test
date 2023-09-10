package mx.android.buabap.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.android.buabap.core.coroutines.CoroutinesDispatchers
import mx.android.buabap.data.datasource.local.database.UserRoomDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserRoomDatabase(application: Application) = UserRoomDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideUserDao(userRoomDatabase: UserRoomDatabase) = userRoomDatabase.userDao()

    @Provides
    @Singleton
    fun provideCoroutinesDispatchers() = CoroutinesDispatchers()
}