package mx.android.buabap.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.android.buabap.ui.AuthAction.SignIn
import mx.android.buabap.ui.AuthAction.SignUp

class AuthViewModel : ViewModel() {

    private val _navigateToAuthAction = MutableStateFlow<AuthAction?>(null)

    val navigateToAuthAction: StateFlow<AuthAction?>
        get() = _navigateToAuthAction

    fun navigateToSingUp() {
        _navigateToAuthAction.value = SignUp
    }

    fun navigateToSingIn() {
        _navigateToAuthAction.value = SignIn
    }
}