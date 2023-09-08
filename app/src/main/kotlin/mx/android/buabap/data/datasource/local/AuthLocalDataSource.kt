package mx.android.buabap.data.datasource.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.android.buabap.data.datasource.exception.SignInException
import mx.android.buabap.data.datasource.exception.SignUpException
import mx.android.buabap.data.datasource.local.database.UserDao
import mx.android.buabap.data.datasource.local.database.UserEntity
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun signUp(userEntity: UserEntity): Flow<Result<Boolean>> = flow {
        val id = userDao.insert(userEntity)
        if (id > INVALID_ID) emit(Result.success(true)) else emit(Result.failure(SignUpException()))
    }

    fun signIn(email: String, password: String): Flow<Result<UserEntity>> = flow {
        val userEntity = userDao.get(email, password)
        if (userEntity != null) emit(Result.success(userEntity)) else emit(Result.failure(SignInException()))
    }

    companion object {
        private const val INVALID_ID = -1L
    }
}