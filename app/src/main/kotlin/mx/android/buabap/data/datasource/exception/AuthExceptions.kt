package mx.android.buabap.data.datasource.exception

class SignInException(override val message: String = "Some error happened with the Sign In") : Exception(message)

class SignUpException(override val message: String = "Invalid Credentials at Sing In") : Exception(message)


