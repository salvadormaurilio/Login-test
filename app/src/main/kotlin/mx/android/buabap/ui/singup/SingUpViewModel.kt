package mx.android.buabap.ui.singup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.android.buabap.domain.SignInUseCase
import mx.android.buabap.domain.SignUpUseCase
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp
import mx.android.buabap.ui.singin.SignInUiState

class SingUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState?>(null)

    val signUpUiState: StateFlow<SignUpUiState?>
        get() = _signUpUiState

}