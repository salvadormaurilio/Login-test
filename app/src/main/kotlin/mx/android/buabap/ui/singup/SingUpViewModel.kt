package mx.android.buabap.ui.singup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.buabap.domain.SignUpUseCase
import mx.android.buabap.ui.singin.UserCredentialsUi
import mx.android.buabap.ui.singin.toUserCredentials
import mx.android.buabap.ui.singup.SignUpUiState.Error
import mx.android.buabap.ui.singup.SignUpUiState.Success
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState?>(null)

    val signUpUiState: StateFlow<SignUpUiState?>
        get() = _signUpUiState


    fun singUp(userCredentialsUi: UserCredentialsUi) = viewModelScope.launch {
        _signUpUiState.value = SignUpUiState.Loading
        signUpUseCase.signUp(userCredentialsUi.toUserCredentials()).collect {
            signUpSuccess(it)
            signUpError(it)
        }
    }

    private fun signUpSuccess(result: Result<Boolean>) = result.onSuccess {
        _signUpUiState.value = Success
    }

    private fun signUpError(it: Result<Boolean>) = it.onFailure {
        it.printStackTrace()
        _signUpUiState.value = Error(it)
    }
}