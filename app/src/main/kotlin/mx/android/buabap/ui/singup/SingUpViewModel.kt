package mx.android.buabap.ui.singup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.buabap.core.coroutines.CoroutinesDispatchers
import mx.android.buabap.domain.SignUpUseCase
import mx.android.buabap.ui.exception.AuthExceptionHandler
import mx.android.buabap.ui.singin.UserCredentialsUi
import mx.android.buabap.ui.singin.toUserCredentials
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
        private val authExceptionHandler: AuthExceptionHandler,
        private val signUpUseCase: SignUpUseCase,
        private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _signUpUiState = MutableStateFlow<SignUpUiState?>(null)

    val signUpUiState: StateFlow<SignUpUiState?>
        get() = _signUpUiState

    fun singUp(userCredentialsUi: UserCredentialsUi) = viewModelScope.launch(coroutinesDispatchers.io) {
        _signUpUiState.value = SignUpUiState.Loading

        val (areInvalidCredentials, exception) = authExceptionHandler.areInvalidSingUpCredentials(userCredentialsUi)
        if (areInvalidCredentials) return@launch emitSignInUiState(SignUpUiState.Error(exception))

        signUpUseCase.signUp(userCredentialsUi.toUserCredentials()).collect {
            signUpSuccess(it)
            signUpError(it)
        }
    }

    private fun signUpSuccess(result: Result<Boolean>) = result.onSuccess {
        emitSignInUiState(SignUpUiState.Success)
    }

    private fun signUpError(result: Result<Boolean>) = result.onFailure {
        it.printStackTrace()
        emitSignInUiState(SignUpUiState.Error(it))
    }

    private fun emitSignInUiState(signUpUiState: SignUpUiState) {
        _signUpUiState.value = signUpUiState
    }
}