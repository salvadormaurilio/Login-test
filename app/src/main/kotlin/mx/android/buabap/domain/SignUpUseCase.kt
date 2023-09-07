package mx.android.buabap.domain

import mx.android.buabap.data.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {

    fun signUp(userCredentials: UserCredentials) = authRepository.signUp(userCredentials)
}