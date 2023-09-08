package mx.android.buabap.ui.singup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mx.android.buabap.domain.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState?>(null)

    val signUpUiState: StateFlow<SignUpUiState?>
        get() = _signUpUiState

}