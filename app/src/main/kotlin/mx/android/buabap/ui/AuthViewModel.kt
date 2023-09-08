package mx.android.buabap.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.android.buabap.ui.AuthAction.OpenSignIn
import mx.android.buabap.ui.AuthAction.OpenSignUp

class AuthViewModel : ViewModel() {

    private val _navigateToAuthAction = MutableStateFlow<AuthAction>(OpenSignUp)

    val navigateToAuthAction: StateFlow<AuthAction>
        get() = _navigateToAuthAction

    fun navigateToSingUp() {
        _navigateToAuthAction.value = OpenSignUp
    }

    fun navigateToSingIn() {
        _navigateToAuthAction.value = OpenSignIn

    }
}