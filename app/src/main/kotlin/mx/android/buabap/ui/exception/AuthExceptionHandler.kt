package mx.android.buabap.ui.exception

import mx.android.buabap.core.ui.isValidEmail
import mx.android.buabap.ui.singin.UserCredentialsUi
import javax.inject.Inject

class AuthExceptionHandler @Inject constructor() {

    fun areInvalidSingUpCredentials(userCredentialsUi: UserCredentialsUi) = userCredentialsUi.run {
        when {
            name.isBlank() || name.length < MIN_CHARACTERS_NAMES -> Pair(true, AuthUiException.Name)
            !email.isValidEmail() -> Pair(true, AuthUiException.Email)
            password.isBlank() || password.length < MIN_CHARACTERS_PASSWORD -> Pair(true, AuthUiException.Password)
            confirmPassword.isBlank() || confirmPassword.length < MIN_CHARACTERS_PASSWORD -> Pair(true, AuthUiException.ConfirmPassword)
            password != confirmPassword -> Pair(true, AuthUiException.DifferentPassword)
            else -> Pair(false, AuthUiException.NoValidationNeeded)
        }
    }

    fun areInvalidSingInCredentials(email: String, password: String) = when {
        !email.isValidEmail() -> Pair(true, AuthUiException.Email)
        password.isBlank() || password.length < MIN_CHARACTERS_PASSWORD -> Pair(true, AuthUiException.Password)
        else -> Pair(false, AuthUiException.NoValidationNeeded)
    }


    companion object {
        private const val MIN_CHARACTERS_NAMES = 4
        private const val MIN_CHARACTERS_PASSWORD = 5
    }
}