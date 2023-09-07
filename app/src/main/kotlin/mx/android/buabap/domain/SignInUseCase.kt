package mx.android.buabap.domain

import mx.android.buabap.data.AuthRepository

class SignInUseCase(private val authRepository: AuthRepository) {

    fun signIn(email: String, password: String) = authRepository.signIn(email, password)
}