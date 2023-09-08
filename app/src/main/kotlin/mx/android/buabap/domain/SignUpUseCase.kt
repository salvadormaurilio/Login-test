package mx.android.buabap.domain

import mx.android.buabap.data.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun signUp(userCredentials: UserCredentials) = authRepository.signUp(userCredentials)
}