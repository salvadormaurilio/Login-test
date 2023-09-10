package mx.android.buabap.ui.singin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.android.buabap.core.coroutines.CoroutinesDispatchers
import mx.android.buabap.domain.SignInUseCase
import mx.android.buabap.domain.UserData
import mx.android.buabap.ui.exception.AuthExceptionHandler
import javax.inject.Inject

@HiltViewModel
class SingInViewModel @Inject constructor(
        private val authExceptionHandler: AuthExceptionHandler,
        private val signInUseCase: SignInUseCase,
        private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val _signInUiState = MutableStateFlow<SignInUiState?>(null)

    val signInUiState: StateFlow<SignInUiState?>
        get() = _signInUiState

    fun signIn(email: String, password: String) = viewModelScope.launch(coroutinesDispatchers.io) {
        emitSignInUiState(SignInUiState.Loading)

        val (areInvalidCredentials, exception) = authExceptionHandler.areInvalidSingInCredentials(email, password)
        if (areInvalidCredentials) return@launch emitSignInUiState(SignInUiState.Error(exception))

        signInUseCase.signIn(email, password).collect {
            signInSuccess(it)
            signInError(it)
        }
    }

    private fun signInSuccess(result: Result<UserData>) = result.onSuccess {
        emitSignInUiState(SignInUiState.Success(it))
    }

    private fun signInError(result: Result<UserData>) = result.onFailure {
        it.printStackTrace()
        emitSignInUiState(SignInUiState.Error(it))
    }

    private fun emitSignInUiState(signInUiState: SignInUiState) {
        _signInUiState.value = signInUiState
    }
}