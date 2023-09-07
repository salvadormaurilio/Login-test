package mx.android.buabap

import mx.android.buabap.data.datasource.local.database.UserEntity
import mx.android.buabap.domain.UserCredentials
import mx.android.buabap.domain.UserData

const val ANY_ID = 0L
const val ANY_INVALID_ID = -1L
const val ANY_USER_EMAIL = "salvador@buapap.mx"
private const val ANY_NAME = "Salvador"
const val ANY_PASSWORD = "Admin123"

fun givenUserEntity() = UserEntity(
    id = ANY_ID,
    name = ANY_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD
)

fun givenUserCredentials() = UserCredentials(
    name = ANY_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD
)

fun givenUserData() = UserData(
    name = ANY_NAME,
    email = ANY_USER_EMAIL)


