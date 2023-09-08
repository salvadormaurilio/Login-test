package mx.android.buabap.ui

sealed class AuthAction {
    object SignUp : AuthAction()
    object SignIn : AuthAction()
}

