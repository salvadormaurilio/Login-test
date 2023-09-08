package mx.android.buabap.ui.auth

sealed class AuthAction {
    object SignUp : AuthAction()
    object SignIn : AuthAction()
}

