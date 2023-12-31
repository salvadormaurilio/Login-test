package mx.android.buabap.ui.exception

import mx.android.buabap.core.ui.isValidEmail
import mx.android.buabap.core.ui.isValidPassword
import mx.android.buabap.ui.singin.UserCredentialsUi
import javax.inject.Inject

class AuthExceptionHandler @Inject constructor() {

    fun areInvalidSingUpCredentials(userCredentialsUi: UserCredentialsUi) = userCredentialsUi.run {
        when {
            name.isBlank() || name.length < MIN_CHARACTERS_NAMES -> Pair(true, AuthUiException.NameException)
            !email.isValidEmail() -> Pair(true, AuthUiException.EmailException)
            !password.isValidPassword() -> Pair(true, AuthUiException.PasswordException)
            !confirmPassword.isValidPassword() -> Pair(true, AuthUiException.ConfirmPasswordException)
            password != confirmPassword -> Pair(true, AuthUiException.DifferentPasswordException)
            else -> Pair(false, AuthUiException.NoValidationNeededException)
        }
    }

    fun areInvalidSingInCredentials(email: String, password: String) = when {
        !email.isValidEmail() -> Pair(true, AuthUiException.EmailException)
        !password.isValidPassword() -> Pair(true, AuthUiException.PasswordException)
        else -> Pair(false, AuthUiException.NoValidationNeededException)
    }


    companion object {
        private const val MIN_CHARACTERS_NAMES = 4
    }
}