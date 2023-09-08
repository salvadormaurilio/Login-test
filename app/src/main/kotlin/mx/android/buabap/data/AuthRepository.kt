package mx.android.buabap.data

import kotlinx.coroutines.flow.map
import mx.android.buabap.data.datasource.local.AuthLocalDataSource
import mx.android.buabap.data.datasource.local.database.toResultUserData
import mx.android.buabap.domain.UserCredentials
import mx.android.buabap.domain.toUserEntity
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authLocalDataSource: AuthLocalDataSource) {

    fun signUp(userCredentials: UserCredentials) = authLocalDataSource.signUp(userCredentials.toUserEntity())

    fun signIn(email: String, password: String) = authLocalDataSource.signIn(email, password).map { it.toResultUserData() }
}