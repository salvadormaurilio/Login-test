package mx.android.buabap

import mx.android.buabap.data.datasource.local.database.UserEntity

const val ANY_ID = 1L
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


