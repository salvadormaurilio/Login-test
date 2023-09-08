package mx.android.buabap.ui.singin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.buabap.domain.SignInUseCase

class SingInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInUiState?>(null)

    val signInUiState: StateFlow<SignInUiState?>
        get() = _signInUiState

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _signInUiState.value = SignInUiState.Loading

        signInUseCase.signIn(email, password).collect { it ->
            it.onSuccess {
                _signInUiState.value = SignInUiState.Success
            }
            it.onFailure {
                it.printStackTrace()
                _signInUiState.value = SignInUiState.Error(it)
            }
        }
    }
}