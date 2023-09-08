package mx.android.buabap.ui.singin

import java.lang.Exception

sealed class SignInUiState{
    object Loading: SignInUiState()
    object Success: SignInUiState()
    data class Error(val exception: Exception): SignInUiState()
}
