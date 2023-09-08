package mx.android.buabap.ui.exception

sealed class AuthUiException : Exception() {
    object NameException : AuthUiException()
    object EmailException : AuthUiException()
    object PasswordException : AuthUiException()
    object ConfirmPasswordException : AuthUiException()
    object DifferentPasswordException : AuthUiException()
    object NoValidationNeededException : AuthUiException()
}