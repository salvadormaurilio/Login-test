package mx.android.buabap.data.datasource.local

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.android.buabap.data.datasource.exception.AuthException
import mx.android.buabap.data.datasource.local.database.UserDao
import mx.android.buabap.data.datasource.local.database.UserEntity
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun signUp(userEntity: UserEntity): Flow<Result<Boolean>> = flow {
        delay(DELAY_RESPONSE)

        if (userDao.exist(userEntity.email)) emit(Result.failure(AuthException.UserAlreadyExistException()))

        val id = userDao.insert(userEntity)
        if (id > INVALID_ID) emit(Result.success(true)) else emit(Result.failure(AuthException.SignUpException()))
    }

    fun signIn(email: String, password: String): Flow<Result<UserEntity>> = flow {
        delay(DELAY_RESPONSE)

        val userEntity = userDao.get(email, password)
        if (userEntity != null) emit(Result.success(userEntity)) else emit(Result.failure(AuthException.SignInException()))
    }

    companion object {
        private const val INVALID_ID = -1L
        private const val DELAY_RESPONSE = 500L
    }
}