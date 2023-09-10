package mx.android.buabap

import mx.android.buabap.data.datasource.local.database.UserEntity

const val ANY_AUTO_ID = 1L
const val ANY_NAME = "Salvador"
const val ANY_INVALID_NAME = "Sal"
const val ANY_USER_EMAIL = "salvador@google.mx"
const val ANY_INVALID_USER_EMAIL = "salvadorbuapap.mx"
const val ANY_PASSWORD = "Admin1234_1"
const val ANY_INVALID_PASSWORD = "Admi"
const val ANY_OTHER_PASSWORD = "Admin1234_12"

fun givenUserEntity() = UserEntity(
        id = ANY_AUTO_ID,
        name = ANY_NAME,
        email = ANY_USER_EMAIL,
        password = ANY_PASSWORD
)
