package mx.android.buabap.ui.exception

sealed class AuthUiException : Exception() {
    object Name : AuthUiException()
    object Email : AuthUiException()
    object Password : AuthUiException()
    object ConfirmPassword : AuthUiException()
    object DifferentPassword : AuthUiException()
    object NoValidationNeeded : AuthUiException()
}