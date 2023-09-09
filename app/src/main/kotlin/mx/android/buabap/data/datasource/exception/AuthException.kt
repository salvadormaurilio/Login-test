package mx.android.buabap.data.datasource.exception

sealed class AuthException(message: String) : Exception(message) {
    data class SignUpException(override val message: String = "Invalid Credentials at Sing Up") : Exception(message)
    data class UserAlreadyExistException(override val message: String = "User al already exist in data base") : Exception(message)
    data class SignInException(override val message: String = "Some error happened with the Sign In") : Exception(message)
}

