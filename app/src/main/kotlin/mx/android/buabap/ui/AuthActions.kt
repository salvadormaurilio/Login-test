package mx.android.buabap.ui

sealed class AuthAction {
    object OpenSignUp : AuthAction()
    object OpenSignIn : AuthAction()
}

