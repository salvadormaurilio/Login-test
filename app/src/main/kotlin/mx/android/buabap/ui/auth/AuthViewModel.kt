package mx.android.buabap.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp

class AuthViewModel : ViewModel() {

    private val _navigateToAuthAction = MutableSharedFlow<AuthAction?>()

    val navigateToAuthAction: SharedFlow<AuthAction?>
        get() = _navigateToAuthAction

    fun navigateToSingUp() = viewModelScope.launch {
        _navigateToAuthAction.emit(SignUp)
    }

    fun navigateToSingIn() = viewModelScope.launch {
        _navigateToAuthAction.emit(SignIn)
    }
}