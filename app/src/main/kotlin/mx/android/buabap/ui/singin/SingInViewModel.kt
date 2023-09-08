package mx.android.buabap.ui.singin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.android.buabap.domain.SignInUseCase
import mx.android.buabap.ui.auth.AuthAction.SignIn
import mx.android.buabap.ui.auth.AuthAction.SignUp

class SingInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {


}