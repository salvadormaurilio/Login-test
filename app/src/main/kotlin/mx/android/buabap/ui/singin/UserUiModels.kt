package mx.android.buabap.ui.singin

import mx.android.buabap.domain.UserCredentials

data class UserCredentialsUi(val name: String, val email: String, val password: String, val confirmPassword: String)

fun UserCredentialsUi.toUserCredentials() = UserCredentials(
    name = name,
    email = email,
    password = password
)
