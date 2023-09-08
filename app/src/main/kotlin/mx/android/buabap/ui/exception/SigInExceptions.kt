package mx.android.buabap.ui.exception

sealed class SignInException : Exception() {
    object Name : SignInException()
    object Email : SignInException()
    object Password : SignInException()
    object ConfirmPassword : SignInException()
    object DifferentPassword : SignInException()
    object NoValidationNeeded : SignInException()
}