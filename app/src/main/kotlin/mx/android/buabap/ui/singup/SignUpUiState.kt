package mx.android.buabap.ui.singup

import mx.android.buabap.domain.UserData
import java.lang.Exception

sealed class SignUpUiState{
    object Loading: SignUpUiState()
    data class Success(val userData: UserData): SignUpUiState()
    data class Error(val exception: Exception): SignUpUiState()
}
