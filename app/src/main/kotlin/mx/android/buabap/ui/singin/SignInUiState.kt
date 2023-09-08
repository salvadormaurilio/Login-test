package mx.android.buabap.ui.singin

import mx.android.buabap.domain.UserData

sealed class SignInUiState {
    object Loading : SignInUiState()
    data class Success(val userData: UserData) : SignInUiState()
    data class Error(val error: Throwable) : SignInUiState()
}
