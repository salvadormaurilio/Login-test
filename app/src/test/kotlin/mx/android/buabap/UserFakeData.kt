package mx.android.buabap

import mx.android.buabap.data.datasource.local.database.UserEntity
import mx.android.buabap.domain.UserCredentials
import mx.android.buabap.domain.UserData
import mx.android.buabap.ui.singin.UserCredentialsUi

const val ANY_ID = 0L
const val ANY_INVALID_ID = -1L
private const val ANY_NAME = "Salvador"
private const val ANY_INVALID_NAME = "Sal"
const val ANY_USER_EMAIL = "salvador@google.mx"
const val ANY_INVALID_USER_EMAIL = "salvadorbuapap.mx"
const val ANY_PASSWORD = "Admin123"
const val ANY_INVALID_PASSWORD = "Admi"
private const val ANY_OTHER_PASSWORD = "Admin987"

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
    email = ANY_USER_EMAIL
)

fun givenUserCredentialsUi() = UserCredentialsUi(
    name = ANY_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD
)

fun givenUserCredentialsUiWithInvalidName() = UserCredentialsUi(
    name = ANY_INVALID_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD
)

fun givenUserCredentialsUiWithInvalidEmail() = UserCredentialsUi(
    name = ANY_NAME,
    email = ANY_INVALID_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD
)

fun givenUserCredentialsUiWithInvalidPassword() = UserCredentialsUi(
    name = ANY_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_INVALID_PASSWORD,
    confirmPassword = ANY_PASSWORD
)

fun givenUserCredentialsUiWithInvalidConfirmPassword() = UserCredentialsUi(
    name = ANY_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_INVALID_PASSWORD
)

fun givenUserCredentialsUiWithDifferentPasswords() = UserCredentialsUi(
    name = ANY_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_OTHER_PASSWORD
)
