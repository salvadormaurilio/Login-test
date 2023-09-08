package mx.android.buabap.ui.singin

sealed class SignInUiState {
    object Loading : SignInUiState()
    object Success : SignInUiState()
    data class Error(val throwable: Throwable) : SignInUiState()
}
