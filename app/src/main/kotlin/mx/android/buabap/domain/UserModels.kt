package mx.android.buabap.domain

import mx.android.buabap.data.datasource.local.database.UserEntity

data class UserCredentials(val name: String, val email: String, val password: String)

data class UserData(val name: String, val email: String)

fun UserCredentials.toUserEntity() = UserEntity(
    name = name,
    email = email,
    password = password
)