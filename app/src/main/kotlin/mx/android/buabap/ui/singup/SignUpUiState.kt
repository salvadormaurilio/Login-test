package mx.android.buabap.ui.singup

sealed class SignUpUiState {
    object Loading : SignUpUiState()
    object Success : SignUpUiState()
    data class Error(val error: Throwable) : SignUpUiState()
}
