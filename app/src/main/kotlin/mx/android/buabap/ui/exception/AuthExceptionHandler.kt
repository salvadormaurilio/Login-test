package mx.android.buabap.ui.exception

import mx.android.buabap.core.ui.isValidEmail
import mx.android.buabap.ui.exception.SignInException.ConfirmPassword
import mx.android.buabap.ui.exception.SignInException.DifferentPassword
import mx.android.buabap.ui.exception.SignInException.Email
import mx.android.buabap.ui.exception.SignInException.Name
import mx.android.buabap.ui.exception.SignInException.NoValidationNeeded
import mx.android.buabap.ui.exception.SignInException.Password
import mx.android.buabap.ui.singin.UserCredentialsUi
import javax.inject.Inject

class AuthExceptionHandler @Inject constructor() {

    fun areInvalidSingUpCredentials(userCredentialsUi: UserCredentialsUi) = userCredentialsUi.run {
        when {
            name.isBlank() || name.length < MIN_CHARACTERS_NAMES -> Pair(true, Name)
            email.isValidEmail() -> Pair(true, Email)
            password.isBlank() || password.length < MIN_CHARACTERS_PASSWORD -> Pair(true, Password)
            confirmPassword.isBlank() || confirmPassword.length < MIN_CHARACTERS_PASSWORD -> Pair(true, ConfirmPassword)
            password != confirmPassword -> Pair(true, DifferentPassword)
            else -> Pair(false, NoValidationNeeded)
        }
    }

    fun areInvalidSingInCredentials(email: String, password: String) = when {
        email.isValidEmail() -> Pair(true, Email)
        password.isBlank() || password.length < MIN_CHARACTERS_PASSWORD -> Pair(true, Password)
        else -> Pair(false, NoValidationNeeded)
    }


    companion object {
        private const val MIN_CHARACTERS_NAMES = 3
        private const val MIN_CHARACTERS_PASSWORD = 5
    }
}